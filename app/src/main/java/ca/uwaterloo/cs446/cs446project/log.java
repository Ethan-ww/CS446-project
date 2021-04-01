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
        max_right = right + (right - left)/2;


    }
    @Override
    public void draw(Canvas c) {
        super.draw(c);

        c.drawBitmap(this.background, src.get(0), dest.get(0), null);


    }

    @Override
    public void move () {


        if (canMove()) {
            right += moving_velocity;
            left += moving_velocity;
            dest.get(0).offset(moving_velocity,0);
        }

    }

    public boolean canMove() {
        return right < max_right;
    }

    @Override
    public HitType hitModel (Rect rect, HitType type) {
        Rect curDest = dest.get(0);
        if (Rect.intersects(rect, curDest)) {
            if (type == HitType.LOG_DOWN  && rect.bottom >= curDest.top && rect.bottom < curDest.bottom && rect.top < curDest.top && rect.left > curDest.left && rect.right < curDest.right) {
                return type;
            }
            if (type == HitType.LOG_LEFT && rect.right >= curDest.left && hitModel(rect, HitType.LOG_DOWN) != HitType.LOG_DOWN) {
                return type;
            }
        }
        return HitType.NULL;
    }


    public int getMovingVelocity () {
        return moving_velocity;
    }

}
