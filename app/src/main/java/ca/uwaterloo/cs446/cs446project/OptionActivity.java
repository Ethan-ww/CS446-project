package ca.uwaterloo.cs446.cs446project;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Observable;
import java.util.Observer;

public class OptionActivity extends Activity implements Observer {
    Button backButton;
    GameModel model;
    Switch musicSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_option);

        model=GameModel.getInstance();

        musicSwitch=findViewById(R.id.musicSwitch);
        musicSwitch.setChecked(model.musicOn);
        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                model.musicOn=isChecked;
                if(model.musicOn==true){
                    System.out.println("Background Music On");
                }else if(model.musicOn==false){
                    System.out.println("Background Music Off");
                }
            }
        });

        backButton=findViewById(R.id.optionBack);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OptionActivity.this, MainActivity.class));
                finish();
            }
        });
    }



    @Override
    public void update(Observable observable, Object o) {

    }
}
