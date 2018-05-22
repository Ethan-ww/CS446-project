package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;


/**
 * Created by ethan on 2018-05-16.
 */

abstract public class StaticObject extends PhysicalModel {

    public ArrayList<Point> points;


    public StaticObject(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest) {
        super(context, background, src, dest);
    }


    // random numbers
    static Random rand = new Random();

    int random(int min, int max) {
        return rand.nextInt(max - min + 1) + min;
    }

    void addPoints(Integer x, Integer y) {

        points.add(new Point(x, y));

    }

}
