package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-06-03.
 */

public class cage extends DynamicObject {

    int top;
    int bottom;
    int max_bottom;

    public cage(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int moving_velocity, int top, int bottom) {
        super(context, background, src, dest, moving_velocity);
        type = HitType.CAGE;
        this.top = top;
        this.bottom = bottom;
        max_bottom = bottom + 350;
    }

    @Override
    public void move() {

        if (bottom < max_bottom) {
            bottom += moving_velocity;
            top += moving_velocity;
            dest.get(0).offset(0, moving_velocity);
        }

    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        c.drawBitmap(background, src.get(0), dest.get(0),  null);
    }
}
