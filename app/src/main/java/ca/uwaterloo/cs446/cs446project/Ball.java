package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-06-03.
 */


public class Ball extends DynamicObject {

    int x;
    int y;
    public boolean falling = false;
    int bottom;


    public Ball(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int moving_velocity, int x, int y, int bottom) {
        super(context, background, src, dest, moving_velocity);
        this.x = x;
        this.y = y;
        this.bottom = bottom;
    }

    @Override
    public void move() {

    }

    @Override
    public void draw (Canvas c) {
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        if (falling) {
            fall();
        }
        c.drawCircle(x,y,75,p);

    }

    public void fall () {

        if (y < bottom) {
            y+=moving_velocity;
        }
        else {
            falling = false;
        }

    }
}
