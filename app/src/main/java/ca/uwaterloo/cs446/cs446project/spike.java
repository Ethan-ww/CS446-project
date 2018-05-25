package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-21.
 */

public class spike extends StaticObject {
    public spike(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest) {
        super(context, background, src, dest);
        this.type = HitType.SPIKE;
    }

    @Override
    public HitType hitModel(Rect rect) {
        for (int i = 0; i < dest.size(); i++) {
            if (Rect.intersects(rect,dest.get(i))) {
                return type;
            }
        }
        return HitType.NULL;
    }

    @Override
    public HitType hitModel(Rect rect, HitType type) {
        return HitType.NULL;
    }

    @Override
    public void draw (Canvas c) {
        super.draw(c);

        for (int i = 0; i < src.size(); i++) {
            c.drawBitmap(background, src.get(i), dest.get(i), null);

        }
    }

}
