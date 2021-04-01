package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by yuqinghe on 2018/6/28.
 */

public class SpikeSensor extends StaticObject {

    ArrayList <spike> s;
    public SpikeSensor(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, ArrayList<spike> s) {
        super(context, background, src, dest);
        this.s = s;
    }

    @Override
    public HitType hitModel(Rect rect) {
        return HitType.NULL;
    }

    @Override
    public HitType hitModel(Rect rect, HitType type) {
        if (Rect.intersects(rect, dest.get(0)) || (dest.size() > 1 && Rect.intersects(rect, dest.get(1)))) {
            for (int i = 0; i < s.size(); i++) {
                s.get(i).needToDraw = false;
            }
            return type;
        } else {
            for (int i = 0; i < s.size(); i++) {
                s.get(i).needToDraw = true;
            }
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
