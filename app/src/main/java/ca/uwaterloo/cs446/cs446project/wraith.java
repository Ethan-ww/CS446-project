package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-21.
 */

public class wraith extends DynamicObject {

    ArrayList <Bitmap> fly;
    int position = 0;
    int positiony;
    int boundary_left;
    int boundary_right;
    int direction = 0; // 0: moving right, 1: moving left;
    int curpos;
    int start_pos = 0;
    int end_pos = 9;

    public wraith(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int moving_velocity,
                  ArrayList<Bitmap> fly, int x, int y) {
        super(context, background, src, dest, moving_velocity);
        this.type = HitType.WRAITH;
        this.fly = fly;
        curpos = x;
        positiony = y;
        boundary_left = x - 300;
        boundary_right = x + 300;
    }

    @Override
    public void move() {

    }


    @Override
    public void draw(Canvas c) {
        super.draw(c);
            int offsetx = 2;
            if (direction == 0) {
                curpos += offsetx;
                dest.get(0).offset(offsetx, 0);
                if (curpos >= boundary_right) {
                    direction = 1;
                }
            } else {
                curpos -= offsetx;
                dest.get(0).offset(-offsetx, 0);
                if (curpos <= boundary_left) {
                    direction = 0;
                }
            }
            c.drawBitmap(fly.get(position), src.get(0), dest.get(0), null);

        if (position == end_pos) {

                this.position = start_pos;

        } else {
            this.position ++;
        }


    }

    public boolean hitWidth(int x, int y) {
        return curpos < x + 50 + 300 && curpos > x + 50 -300 && positiony < y + 50 + 300 && positiony > y + 50 - 300;
    }
}
