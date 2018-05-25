package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;


import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-20.
 */

public class bomb extends Tool {


    int collect_position_left = 300;
    int collect_position_top = 10;
    int bomb_size = 100;

    public bomb(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int top, int left) {
        super(context, background, src, dest, top, left);
        type = HitType.BOMB;
        System.out.println("got here1");

    }

    @Override
    void collect(Canvas canvas) {

        double slope = (collect_position_top - top ) / (collect_position_left - left);
        if (top <= collect_position_top) return;

            top -= (20 * Math.abs(slope)) + 1;

                dest.get(0).offset(-20 , (int) - (20 * Math.abs(slope)) -1 );




    }

    @Override
    void use() {

    }
}
