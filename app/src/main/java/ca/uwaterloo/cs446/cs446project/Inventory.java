package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

import java.util.ArrayList;

/**
 * Created by natalieho on 18/6/4.
 */

public class Inventory extends UI{
    boolean display = false;
    boolean animation = false;
    int fps;
    int index = 0;
    boolean OpenClose = true; // true = open, false = close
    int n = 6; // total number of space
    Bitmap bomb;

    public Inventory(String name, Bitmap OnImage, Bitmap OffImage, int x, int y, int width, int height, int fps, Context context) {
        super(name, OnImage, OffImage, x, y, width, height);
        this.fps = fps;
        bomb = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb);
        bomb = Bitmap.createScaledBitmap(bomb, width/2/n, height/2, false);
    }

    public void draw(Canvas canvas) {
        if (display) {

            if(!animation) {
                canvas.drawBitmap(OnImage, x + trans_x, y + trans_y, null);
                for(int i = 1; i <= n;i++) {
                    int temp_x = x + trans_x + width / n * (i - 1) + width / n / 4;
                    int temp_y = y + trans_y + height / 4;
                    canvas.drawBitmap(bomb,temp_x,temp_y,null);
                }

            }
            // turn on
            else if (animation && OpenClose){
                Bitmap b=Bitmap.createBitmap(OnImage, 0,0,width/fps * index, height);
                canvas.drawBitmap(b,x+trans_x+width-width/fps*index,y+trans_y,null);
                for(int i = 1; i <= n;i++) {
                    int temp_x = x + trans_x + width - width / fps * index + + width/n*(i-1) + width/n/4;
                    int temp_y = y + trans_y + + height/4;
                    if (i < (float)index/(float)fps * (float)n) canvas.drawBitmap(bomb,temp_x,temp_y,null);
                }
            }
            // turn off
            else if(animation && !OpenClose){
                Bitmap b=Bitmap.createBitmap(OnImage, 0,0,width - width/fps * index, height);
                canvas.drawBitmap(b,x+trans_x+width/fps*index,y+trans_y,null);
                for(int i = 1; i <= n;i++) {
                    int temp_x = x + trans_x + width / fps * index + width/n*(i-1) + width/n/4;
                    int temp_y = y + trans_y + height/4;
                    if (i < (float)n - (float)index/(float)fps*(float)n) canvas.drawBitmap(bomb,temp_x,temp_y,null);
                }
            }

        }

    }

    public void clicked(float x){
        if(display && !animation) {
            for (int i = 1; i <= n; i++) {
                if (x < this.x + i * this.width / n) {
                    System.out.println("item number " + i + " clicked");
                    // do something when the item clicked
                    break;
                }
            }
        }
    }

    // when backpack is clicked, turn inventory on and off, do animation
    public void animation(){
        animation = true;
        index = 0;
        if(display){
            // need to turn off inventory, and then turn off display
            OpenClose = false;
        }
        else{
            // need to turn on display, and then play turn on inventory animation
            display = true;
            OpenClose = true;
        }

    }

    // called by model when animation is true
    public void update(){
        if(index == fps){
            animation = false;
            if (OpenClose == false) display = false; // close
        }
        else if(index < fps){
            index += 3;
        }
    }


}