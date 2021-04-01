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

public class ladder extends DynamicObject {

    int top;
    int bottom;
    int max_bottom;


    public ladder(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int move_velocity, int top, int bottom) {
        super(context,  background,src,dest, move_velocity);
        type = HitType.LADDER;
        this.top = top;
        this.bottom = bottom;
        max_bottom = bottom + 300;

    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        c.drawBitmap(background, src.get(0), dest.get(0),  null);

    }
    @Override
    public void move () {

        if (bottom < max_bottom) {
            bottom += moving_velocity;
            top += moving_velocity;
            dest.get(0).offset(0, moving_velocity);
        }

    }

    public boolean hitLadder(int left) {

        int cx = left + 50;
        return cx - 200 <= dest.get(0).centerX() &&  dest.get(0).centerX() <= cx+200;


    }
}
