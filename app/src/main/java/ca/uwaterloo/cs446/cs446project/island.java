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

public class island extends DynamicObject {

    boolean hit = false;
    int up_limit;
    int down_limit;

    public island(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int moving_velocity, int y) {
        super(context, background, src, dest, moving_velocity);
        type = HitType.ISLAND;
        up_limit = y ;
        down_limit = y + 200;

    }

    @Override
    public void move() {

        //moving down
        if (hit && dest.get(0).top < down_limit) {
            dest.get(0).offset(0, moving_velocity);
        }
        //floating up
        else if (!hit && dest.get(0).top > up_limit){
            dest.get(0).offset(0, -moving_velocity);

        }


    }

    @Override
    public void draw(Canvas c) {

        move();
        super.draw(c);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        Rect temp = new Rect(dest.get(0).left,
                dest.get(0).top - background.getHeight(), dest.get(0).right, dest.get(0).top);

        c.drawBitmap(background, src.get(0), temp, null);

        c.drawRect(dest.get(0), p);

    }

}
