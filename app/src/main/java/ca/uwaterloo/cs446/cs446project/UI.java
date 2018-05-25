package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Point;

/**
 * Created by ethan on 2018-05-16.
 */

public class UI {
    public String name;

    public boolean selected;

    private int x;
    private int y;
    private int width;
    private int height;
    private Bitmap OnImage;
    private Bitmap OffImage;

    public UI(String name, Bitmap OnImage, Bitmap OffImage, int x, int y, int width, int height){
        this.name=name;
        this.OnImage=Bitmap.createScaledBitmap(OnImage,width,height,false);
        this.OffImage=Bitmap.createScaledBitmap(OffImage,width,height,false);
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;

        this.selected=false;
    }

    public void setSelected(boolean selected){
        this.selected=selected;
    }

    public void draw(Canvas canvas){
        if(selected){
            canvas.drawBitmap(OnImage, x,y,null);
        }else{
            canvas.drawBitmap(OffImage, x,y,null);
        }
    }

    public boolean hitTest(float x, float y){
        return x<=this.x+width && x>=this.x
                && y<=this.y+height && y>=this.y;
    }

}
