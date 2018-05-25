package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-17.
 */

public class log extends DynamicObject {


    int right;
    int left;
    int max_right;

    public log (Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int left, int right, int moving_velocity) {
        super(context, background, src, dest, moving_velocity);
        type = HitType.LOG;
        this.left = left;
        this.right = right;
        max_right = right + (right - left)/4 ;


    }
    @Override
    public void draw(Canvas c) {
        super.draw(c);

        c.drawBitmap(this.background, src.get(0), dest.get(0), null);


    }

    @Override
    public void move () {


        if (right < max_right) {
            right += moving_velocity;
            left += moving_velocity;
            dest.get(0).offset(moving_velocity,0);

        }

    }

    public int getMovingVelocity () {
        return moving_velocity;
    }


}
