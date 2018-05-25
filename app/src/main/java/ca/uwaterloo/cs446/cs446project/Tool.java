package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Created by julialiu on 2018-05-21.
 */

abstract public class Tool extends PhysicalModel {

    int top;
    int left;
    boolean needToDraw = true;
    public Tool(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int top, int left) {
        super(context, background, src, dest);
        this.top = top;
        this.left = left;
    }

    abstract void collect (Canvas c);
    abstract void use ();


    @Override
    public HitType hitModel (Rect rect) {
        for (int i = 0; i < dest.size(); i++) {
            if (Rect.intersects(dest.get(i), rect)) {
                disappear();
                return this.type;
            }
        }
        return HitType.NULL;
    }

    @Override
    public HitType hitModel (Rect rect, HitType type) {
        return HitType.NULL;
    }

    void disappear () {
        this.needToDraw = false;
    }

    @Override
    public void draw(Canvas canvas) {
        if (needToDraw) {
            super.draw(canvas);
            canvas.drawBitmap(background, src.get(0), dest.get(0),  null);
        }
    }
}
