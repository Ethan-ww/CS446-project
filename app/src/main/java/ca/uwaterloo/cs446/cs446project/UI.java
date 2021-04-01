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

    int x;
    int y;
    int width;
    int height;
    Bitmap OnImage;
    Bitmap OffImage;
    int trans_x = 0;
    int trans_y = 0;

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

    public void draw(Canvas canvas) {
        if (selected) {
            canvas.drawBitmap(OnImage, x + trans_x, y + trans_y, null);
        } else {
            canvas.drawBitmap(OffImage, x + trans_x, y + trans_y, null);
        }
    }

    public boolean hitTest(float x, float y, float tolerance){
        return x<=this.x+width+tolerance && x>=this.x-tolerance
                && y<=this.y+height+tolerance && y>=this.y-tolerance;
    }

    public  void translate(int trans_x,int trans_y){
//        if (this.name == "LeftButton" ||
//                this.name == "RightButton" ||
//                this.name == "UpButton" ||
//                this.name == "DownButton" ||
//                this.name == "JumpButton"){
//            this.trans_x =  -trans_x;
//            this.trans_y = -trans_y;
//        }
        this.trans_x =  -trans_x;
        this.trans_y = -trans_y;

    }

}
