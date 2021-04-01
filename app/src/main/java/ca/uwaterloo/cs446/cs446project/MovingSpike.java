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

public class MovingSpike extends spike {
    int type; // 0: moves left 1: moves right 2: moves up 3:moves down
    int limitOne;
    int limitTwo;
    boolean inPosition;
    int velocity;

    public MovingSpike(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int type,
                       int limitone, int limittwo, int velocity) {
        super(context, background, src, dest);
        this.type = type;
        limitOne = limitone;
        limitTwo = limittwo;
        inPosition = true;
        this.velocity = velocity;
    }

    public void move() {
        Rect destRect = dest.get(0);
        if (type == 0) {
            if (destRect.left > limitOne) {
                destRect.offset(-velocity, 0);
            } else {
                inPosition = false;
            }
        } else if (type == 1) {
            if (destRect.left < limitTwo) {
                destRect.offset(velocity, 0);
            } else {
                inPosition = false;
            }
        } else if (type == 2) {
            if (destRect.top > limitOne) {
                destRect.offset(0, -velocity);
            } else {
                inPosition = false;
            }
        } else {
            if (destRect.top < limitTwo) {
                destRect.offset(0, velocity);
            } else {
                inPosition = false;
            }
        }
    }

    public void moveBack() {
        Rect destRect = dest.get(0);
        if (type == 0) {
            if (destRect.left < limitTwo) {
                destRect.offset(velocity, 0);
            } else {
                inPosition = true;
            }
        } else if (type == 1) {
            if (destRect.left > limitOne) {
                destRect.offset(-velocity, 0);
            } else {
                inPosition = true;
            }
        } else if (type == 3) {
            if (destRect.top > limitOne) {
                destRect.offset(0, -velocity);
            } else {
                inPosition = true;
            }
        } else {
            if (destRect.top < limitTwo) {
                destRect.offset(0, velocity);
            } else {
                inPosition = true;
            }
        }
    }

    @Override
    public void draw(Canvas c) {
        super.draw(c);
        if (inPosition) {move();}
        else {moveBack();}

        Paint p = new Paint();
        p.setColor(Color.BLACK);
        p.setStyle(Paint.Style.FILL);

        for (int i = 0; i < src.size(); i++) {
            Rect desti = dest.get(i);
            c.drawBitmap(background, src.get(i), desti, null);
            if (type == 0) {
                c.drawRect(new Rect(desti.right, desti.top, desti.right+20, desti.bottom), p);
            } else if (type == 1) {
                c.drawRect(new Rect(desti.left-20, desti.top, desti.left, desti.bottom), p);
            } else if (type == 2) {
                c.drawRect(new Rect(desti.left, desti.bottom, desti.right, desti.bottom+20), p);
            } else {
                c.drawRect(new Rect(desti.left, desti.top+20, desti.right, desti.top), p);
            }
        }
    }
}
