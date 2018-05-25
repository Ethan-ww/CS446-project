package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;

import java.util.ArrayList;
/**
 * Created by julialiu on 2018-05-16.
 */

public class Floor extends StaticObject {
    int curground = -1;


    public Floor(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest) {
        super(context, background, src, dest);
        type = HitType.FLOOR;
        }

        @Override
        public void draw (Canvas c) {
                super.draw(c);
                Paint p = new Paint();
                p.setStyle(Paint.Style.FILL);
                p.setColor(Color.BLACK);


               for (int i = 0; i < src.size(); i++) {
                   Rect temp = new Rect (dest.get(i).left,
                           dest.get(i).top - background.getHeight(), dest.get(i).right, dest.get(i).top);

                   c.drawBitmap(background, src.get(i), temp, null);

                   c.drawRect(dest.get(i), p);

                }




        }

    @Override
    public HitType hitModel(Rect rect, HitType type) {


        for (int i = 0; i < dest.size(); i++) {
            Rect curDest = dest.get(i);
            if (Rect.intersects(rect, curDest)) {
                if (type == HitType.UP && rect.bottom > curDest.bottom)
                    return type;
                if (type == HitType.DOWN && rect.bottom >= curDest.top) {
                    if (curground != -1 && dest.get(curground).top > curDest.top && Rect.intersects(dest.get(curground), rect)) {}
                    else {curground = i; }
                    return type;
                }
                if (type == HitType.LEFT) {
                    if (dest.get(i).left >= rect.left || curground == i) {continue;}
                    if (rect.left <= curDest.right)
                        return type;
                }
                if (type == HitType.RIGHT) {
                    if (dest.get(i).right <= rect.right || curground == i) {continue;}
                    if (rect.right >= curDest.left)
                        return type;

                }
            }
        }

        return HitType.NULL;
    }

    public int getFloorHeight() {
        return dest.get(curground).top;
    }

    @Override
    public HitType hitModel(Rect rect) {
        return HitType.NULL;
    }
}
