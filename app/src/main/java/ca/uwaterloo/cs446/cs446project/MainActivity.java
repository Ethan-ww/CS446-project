package ca.uwaterloo.cs446.cs446project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import java.util.Observable;
import java.util.Observer;

public class MainActivity extends Activity implements Observer{

    Button startButton;
    Button creditButton;
    Button optionButton;
    Button scoreButton;
    Button bluetoothButton;

    GameModel model;

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MAIN ACTIVITY IS RESUMED");
    }

    @Override
    protected void onDestroy() {
        System.out.println("MAIN ACTIVITY IS DESTROYED");

        super.onDestroy();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        System.out.println("MAIN ACTIVITY IS CREATED");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);


        startButton=(Button)findViewById(R.id.start);
        optionButton=(Button)findViewById(R.id.option);
        creditButton=(Button)findViewById(R.id.credit);

        bluetoothButton=(Button)findViewById(R.id.bluetooth);


        //model= GameModel.getInstance();
        GameModel.setInstance(this, getWindowManager().getDefaultDisplay(), 60, false);

        startButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SelectionActivity.class));
                finish();
            }
        });

        optionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, OptionActivity.class));
                finish();
            }
        });

        bluetoothButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BluetoothActivity.class));
                finish();
            }
        });

        creditButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Credit.class));
            }
        });



    }

    @Override
    public void update(Observable observable, Object o) {

    }
}
