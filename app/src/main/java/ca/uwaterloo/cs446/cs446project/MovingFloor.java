package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by yuqinghe on 2018/6/27.
 */

public class MovingFloor extends Floor {

    int type; // 0: moves left 1: moves right 2: moves up 3:moves down
    int limitOne;
    int limitTwo;
    boolean inPosition;

    public MovingFloor(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int type,
                       int limitOne, int limitTwo) {
        super(context, background, src, dest);
        this.type = type;
        this.limitOne = limitOne;
        this.limitTwo = limitTwo;
        inPosition = true;
    }

    public void move() {
        Rect destRect = dest.get(0);
        if (type == 0) {
            if (destRect.left > limitOne) {
                destRect.offset(-3, 0);
                inPosition = false;
            }
        } else if (type == 1) {
            if (destRect.left < limitTwo) {
                destRect.offset(3, 0);
                inPosition = false;
            }
        } else if (type == 2) {
            if (destRect.top > limitOne) {
                destRect.offset(0, -3);
                inPosition = false;
            }
        } else {
            if (destRect.top < limitTwo) {
                destRect.offset(0, 3);
                inPosition = false;
            }
        }
    }

    public void moveBack() {
        Rect destRect = dest.get(0);
        if (type == 0) {
            if (destRect.left < limitTwo) {
                destRect.offset(3, 0);
            } else {
                inPosition = true;
            }
        } else if (type == 1) {
            if (destRect.left > limitOne) {
                destRect.offset(-3, 0);
            } else {
                inPosition = true;
            }
        } else if (type == 3) {
            if (destRect.top > limitOne) {
                destRect.offset(0, -3);
            } else {
                inPosition = true;
            }
        } else {
            if (destRect.top < limitTwo) {
                destRect.offset(0, 3);
            } else {
                inPosition = true;
            }
        }
    }
}
