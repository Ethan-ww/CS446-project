package ca.uwaterloo.cs446.cs446project;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import java.util.Observable;
import java.util.Observer;

public class GameActivity extends Activity implements Observer{

    GameModel model;
    Button quit;
    Button save;
    FrameLayout game;
    GameView gameView;

    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class), Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        save=new Button(this);
        quit=new Button(this);

        //quit.setBackgroundResource(R.drawable.more_rough_wood_8_meitu_1);
        //save.setBackgroundResource(R.drawable.more_rough_wood_8_meitu_1);

        //quit.setFontFeatureSettings("casual");
        //save.setFontFeatureSettings("casual");


        quit.setLeft(10);
        quit.setWidth(40);
        save.setLeft(70);
        save.setWidth(40);

        quit.setTop(20);
        save.setTop(20);

        quit.setHeight(25);
        save.setHeight(25);

        quit.setText("QUIT");
        save.setText("SAVE");

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GameModel.saveModel();
            }
        });



        quit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (GameModel.connectionSuccess) {
                    startActivity(new Intent(GameActivity.this, MainActivity.class));
                    GameModel.connectionSuccess = false;
                    model.drawwin = false;
                    model.drawlose = false;

                    // release memory
                    imageSource.ourInstance=null;
                    model.ResetModel();
                    finish();
                } else {
                    System.out.println("inside onclick *******************");
                    if (model.thread != null) {
                        model.thread.setRunning(false);
                      //  model.thread = null;
                    }
                    model.ResetModel();
                    game.removeView(gameView);
                    gameView = null;
                    startActivity(new Intent(GameActivity.this, SelectionActivity.class));
                    finish();

                }
            }
        });

        System.out.println("inside game activity*********************");
        GameModel.setInstance(this, getWindowManager().getDefaultDisplay(), 60, true);
        model= GameModel.getInstance();

        game=new FrameLayout(this);
        gameView=new GameView(this, getWindowManager().getDefaultDisplay(), model);
        LinearLayout gameWidgets=new LinearLayout(this);

        gameWidgets.addView(quit);
        gameWidgets.addView(save);

        game.addView(gameView);
        game.addView(gameWidgets);

        setContentView(game);



            // bind music service to activity
            doBindService();
        if(model.musicOn) {
            // start service
            Intent music = new Intent();
            music.setClass(this, MusicService.class);
            startService(music);
        }


    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        doUnbindService();
    }

    public void backToSelection() {
        System.out.println("inside backtoSelection***********************");
        quit.callOnClick();
    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
