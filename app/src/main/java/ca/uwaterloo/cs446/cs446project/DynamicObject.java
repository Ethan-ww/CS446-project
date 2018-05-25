package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by ethan on 2018-05-16.
 */

abstract public class DynamicObject extends PhysicalModel {
    int moving_velocity;

    public DynamicObject(Context context, Bitmap background, ArrayList<Rect> src, ArrayList<Rect> dest, int moving_velocity){
        super(context,background, src, dest);
        this.moving_velocity = moving_velocity;
    }

    abstract public void move();

    @Override
    public HitType hitModel(Rect rect) {
        for (int i = 0; i < dest.size(); i++) {
            if (rect.intersect(dest.get(i))) {
                return this.type;
            }
        }
        return HitType.NULL;
    }

    @Override
    public HitType hitModel (Rect rect, HitType type) {
        return type.NULL;
    }


}
