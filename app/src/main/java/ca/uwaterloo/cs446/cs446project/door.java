package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-22.
 */

public class door extends StaticObject {
    public door(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest) {
        super(context, background, src, dest);
        type = HitType.DOOR;
    }

    @Override
    public HitType hitModel(Rect rect) {
        for (int i = 0; i < dest.size(); i++) {
            if (Rect.intersects(dest.get(i),rect)) {
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

            c.drawBitmap(background, src.get(0), dest.get(0), null);


    }

}
