package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


import java.util.ArrayList;

/**
 * Created by julialiu on 2018-05-20.
 */

public class bomb extends Tool {


    int collect_position_left = 300;
    int collect_position_top = 10;
    ArrayList <Bitmap> bomb_effect;

    public bomb(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int top, int left) {
        super(context, background, src, dest, top, left);
        type = HitType.BOMB;
        bomb_effect = new ArrayList<>();
        bomb_effect.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_effect4));
        bomb_effect.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_effect3));
        bomb_effect.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_effect2));
        bomb_effect.add(BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb_effect1));

        this.src.add(new Rect (0, 0, bomb_effect.get(0).getWidth(), bomb_effect.get(0).getHeight()));

    }

    @Override
    void collect(Canvas canvas) {

        double slope = (collect_position_top - top ) / (collect_position_left - left);
        if (top <= collect_position_top) return;

            top -= (20 * Math.abs(slope)) + 1;

                dest.get(0).offset(-20 , (int) - (20 * Math.abs(slope)) -1 );




    }

    void use(int x, int y, Context context, Canvas canvas) {

        Bitmap bomb = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb);

        Rect src = new Rect (0,0, bomb.getWidth(), bomb.getHeight());
        Rect dest = new Rect (x-50, y-50, x+50, y+50);
        canvas.drawBitmap(bomb, src, dest, null);




    }
}
