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

public class Lever extends StaticObject {

   public MovingFloor left;
   public MovingFloor right;
   boolean someOneOn;

    public Lever(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest) {
        super(context, background, src, dest);

        ArrayList <Rect> src1 = new ArrayList<>();
        ArrayList <Rect> dest1 = new ArrayList<>();
        ArrayList <Rect> dest2 = new ArrayList<>();
        src1.add(new Rect(0, 0, 200, background.getHeight()));
        dest1.add(new Rect (dest.get(0).left, dest.get(0).top + 150, dest.get(0).left +200, dest.get(0).top+200));
        dest2.add(new Rect (dest.get(0).right - 200, dest.get(0).bottom - 50, dest.get(0).right , dest.get(0).bottom));

        left = new MovingFloor(context,background, src1, dest1, 3, dest.get(0).top + 150, dest.get(0).bottom -50 );
        right = new MovingFloor(context,background, src1, dest2, 2, dest.get(0).top +150, dest.get(0).bottom -50 );
        someOneOn = false;

    }


    @Override
    public HitType hitModel(Rect rect) {
        return HitType.NULL;
    }

    @Override
    public HitType hitModel(Rect rect, HitType type) {
        if (left.hitModel(rect, HitType.DOWN) != HitType.NULL) {
            left.move();
            right.move();
            someOneOn = true;
            return type;
        } else if (left.hitModel(rect, HitType.DOWN) == HitType.NULL) {
            left.moveBack();
            right.moveBack();
            someOneOn = false;
        }
        return HitType.NULL;
    }



    @Override
    public void draw (Canvas c) {
        super.draw(c);
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        Rect d = dest.get(0);

        c.drawCircle(d.left+100, d.top+20, 20, p);
        c.drawCircle(d.right-100, d.top+20, 20, p);
        Rect r = new Rect (d.left+100, d.top, d.right-100, d.top +10);
        c.drawRect(r, p);

        c.drawRect(new Rect (d.left+100, d.top+20, d.left+110, left.dest.get(0).top), p);
        c.drawRect(new Rect (d.right-100, d.top+20, d.right-90, right.dest.get(0).top), p);

        left.draw(c);
        right.draw(c);

    }
}
