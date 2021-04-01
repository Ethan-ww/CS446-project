package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by yuqinghe on 2018/6/27.
 */

public class Sensor extends StaticObject {

    MovingFloor f;
    public Sensor(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, MovingFloor f) {
        super(context, background, src, dest);
        this.f = f;
        type = HitType.SENSOR;
    }

    @Override
    public HitType hitModel(Rect rect) {
        return HitType.NULL;
    }

    @Override
    public HitType hitModel(Rect rect, HitType type) {
        if (Rect.intersects(rect, dest.get(0)) || (dest.size() > 1 && Rect.intersects(rect, dest.get(1)))) {
            f.move();
            return type;
        } else {
            if (f.inPosition == false) { f.moveBack(); }
            return HitType.NULL;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        Paint p = new Paint();
        p.setColor(Color.GRAY);
        p.setStyle(Paint.Style.FILL);
        canvas.drawRect(dest.get(0), p);
        if (dest.size() > 1) {
            canvas.drawRect(dest.get(1), p);
        }
    }
}
