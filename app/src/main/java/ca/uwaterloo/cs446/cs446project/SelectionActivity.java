package ca.uwaterloo.cs446.cs446project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class SelectionActivity extends Activity implements Observer{

    ArrayList<Button> levels;
    Button backButton;

    GameModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(null);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_selection);
        //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        System.out.println("making a new selection activity ------------------------------");



        levels=new ArrayList<>();

        backButton=(Button)findViewById(R.id.back);

        levels.add((Button)findViewById(R.id.level1));
        levels.add((Button)findViewById(R.id.level2));
        levels.add((Button)findViewById(R.id.level3));

       // GameModel.readModel();
        model= GameModel.getInstance();

       // GameModel.setInstance(this, getWindowManager().getDefaultDisplay(), 60, false);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectionActivity.this, MainActivity.class));
            }
        });

            for (int i = 0; i < levels.size(); i++) {
                levels.get(i).setEnabled(true);
            }

        levels.get(0).setBackground(getResources().getDrawable(R.drawable.level1_screenshot));
        levels.get(1).setBackground(getResources().getDrawable(R.drawable.level2_screenshot));
        levels.get(2).setBackground(getResources().getDrawable(R.drawable.leve3_screenshot));
        model.max_frame = 8;
        if (model.max_frame < 7) {
            levels.get(2).setEnabled(false);
            levels.get(2).setBackground(null);
            levels.get(2).setText("III");
        }
        if (model.max_frame < 4) {
            levels.get(1).setEnabled(false);
            levels.get(1).setBackground(null);
            levels.get(1).setText("II");
        }

        levels.get(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectionActivity.this, GameActivity.class));
                   model.cur_frame=0;
                  finish();
            }
        });

        levels.get(1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("inside selection page 2*********************");
                model.cur_frame=4;
                startActivity(new Intent(SelectionActivity.this, GameActivity.class));

               finish();
            }
        });

        levels.get(2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.cur_frame=9;
                System.out.println("inside selection page 3*********************");
                startActivity(new Intent(SelectionActivity.this, GameActivity.class));

               finish();
            }
        });


    }


    @Override
    public void update(Observable observable, Object o) {

    }
}
