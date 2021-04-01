package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-21.
 */

public class magnet extends Tool {
    public magnet(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int top, int left) {
        super(context, background, src, dest, top, left);
        type = HitType.MAGNET;
    }

    @Override
    void collect(Canvas c) {

    }

}
