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
    int count_down = -1;
    int state = 0; // 0 == before collection,  1 == in use, 2 == bomb

    public Tool(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int top, int left) {
        super(context, background, src, dest);
        this.top = top;
        this.left = left;
    }

    abstract void collect (Canvas c);



    @Override
    public HitType hitModel (Rect rect) {
        for (int i = 0; i < dest.size(); i++) {
            if (Rect.intersects(dest.get(i), rect)) {
                return this.type;
            }
        }
        return HitType.NULL;
    }

    @Override
    public HitType hitModel (Rect rect, HitType type) {
        return HitType.NULL;
    }


    @Override
    public void draw(Canvas canvas) {
        if (count_down != 0 && state != 2) {
            super.draw(canvas);
            canvas.drawBitmap(background, src.get(0), dest.get(0),  null);
            if (count_down > 0) count_down--;
        }

        else if (state == 2 && this instanceof bomb && count_down > 0) {
            super.draw(canvas);
            if (count_down > 0) --count_down;
            System.out.println("got to effect");
            canvas.drawBitmap(((bomb)this).bomb_effect.get(count_down/10), src.get(1), dest.get(0), null);

        }
        else {
            super.draw(canvas);
            canvas.drawBitmap(background, src.get(0), dest.get(0),  null);
        }

    }

    public void set_count_down (int i) {
        count_down = i;
    }

    public void set_state (int i) {
        state = i;
    }

}
