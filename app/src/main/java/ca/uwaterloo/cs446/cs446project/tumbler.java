package ca.uwaterloo.cs446.cs446project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-21.
 */

public class tumbler extends DynamicObject {

    ArrayList <Bitmap> wabble;
    int tumbler_position = 0;
    boolean falling = true;
    int temp = 5;
    int x;
    int y;


    public tumbler(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int moving_velocity,
                   ArrayList<Bitmap> wabble, int x, int y) {
        super(context, background, src, dest, moving_velocity);
        this.wabble = wabble;
        this.type = HitType.TUMBLER;
        this.x = x;
        this.y = y;


    }


    @Override
    public void draw(Canvas c) {
        super.draw(c);
        /*
        c.drawBitmap(wabble.get(tumbler_position), src.get(0), dest.get(0),  null);
        if (tumbler_position == wabble.size() - 1) {
            this.tumbler_position = 0;
        } else {
            this.tumbler_position ++;
        }

        */
        if (falling) {
           // c.save();
            //c.rotate(-temp);

            //c.drawBitmap(background, src.get(0), dest.get(0),  null);
            //c.restore();
            Matrix translate = new Matrix();
           Matrix m = new Matrix();
           // m.postRotate(-temp);

                m.preRotate(-temp);
                m.preScale((float) 0.1, (float)0.18);


                Bitmap rotate_rock = Bitmap.createBitmap(background, 0, 0, background.getHeight(), background.getHeight(), m, true);

           // c.drawBitmap(rotate_rock, src.get(0), dest.get(0), null);
            c.drawBitmap(rotate_rock, x, y, null);


            if (temp < 45) {
                temp += 5;
            }
         //   @SuppressLint("ResourceType") ImageView temp = (ImageView) findViewById(R.drawable.rock);
         //   RotateAnimation rotate = new RotateAnimation(0,-90);
          //  rotate.setDuration(2000);
          //  temp.startAnimation(rotate);



        }
        else {
            c.drawBitmap(background, src.get(0), dest.get(0), null);
        }
    }

    @Override
    public void move() {
        falling = true;
    }

}
