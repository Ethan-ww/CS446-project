package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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
    int n = 3; // total number of space
    Bitmap bomb;
    Bitmap key;
    Bitmap magnet;
    GameModel model;

    public Inventory(String name, Bitmap OnImage, Bitmap OffImage, int x, int y, int width, int height, int fps, Context context, GameModel model) {
        super(name, OnImage, OffImage, x, y, width, height);
        this.fps = fps;
        bomb = BitmapFactory.decodeResource(context.getResources(), R.drawable.bomb);
        bomb = Bitmap.createScaledBitmap(bomb, width/2/n, height/2, false);
        key = BitmapFactory.decodeResource(context.getResources(), R.drawable.key);
        key = Bitmap.createScaledBitmap(key, width/2/n, height/2, false);
        magnet = BitmapFactory.decodeResource(context.getResources(), R.drawable.magnet);
        magnet = Bitmap.createScaledBitmap(magnet, width/2/n, height/2, false);
        this.model = model;
    }

    public void draw(Canvas canvas) {
        if (display) {

            if(!animation) {
                canvas.drawBitmap(OnImage, x + trans_x, y + trans_y, null);
                Paint p = new Paint();
                int maroon = Color.parseColor("#800000");
                p.setColor(maroon);

                Paint p1 = new Paint();
                p1.setColor(maroon);
                p1.setTextSize(50);

                for(int i = 1; i <= n;i++) {
                    int temp_x = x + trans_x + width / n * (i - 1) + width / n / 4;
                    int temp_y = y + trans_y + height / 4;
                    if (i == 1) {
                        canvas.drawText("+"+Integer.toString(model.bomb), temp_x+58, temp_y+20, p1);
                        canvas.drawBitmap(bomb,temp_x,temp_y,null);
                    }
                    if (i == 2) {
                        canvas.drawText("+"+Integer.toString(model.key), temp_x+58, temp_y+20, p1);
                        canvas.drawBitmap(key,temp_x,temp_y,null);
                    }
                    if (i == 3) {
                        canvas.drawText("+"+Integer.toString(model.magnet), temp_x+58, temp_y+20, p1);
                        canvas.drawBitmap(magnet,temp_x,temp_y,null);
                    }
                }

                p.setStrokeWidth(10);
                p.setStyle(Paint.Style.STROKE);
                if (model.useBomb) {
                    int startx = x + trans_x + 5;
                    int starty = y + trans_y + 5;
                    int endx = x + trans_x + width/3 + 8;
                    int endy = y + trans_y + height;
                    canvas.drawRect(startx, starty, endx, endy, p);
                } else if (model.useMagnet) {
                    int startx = x + trans_x + width/3*2 + 6;
                    int starty = y + trans_y + 5;
                    int endx = x + trans_x + width;
                    int endy = y + trans_y + height;
                    canvas.drawRect(startx, starty, endx, endy, p);
                }

            }
            // turn on
            else if (animation && OpenClose){
                Bitmap b=Bitmap.createBitmap(OnImage, 0,0,width/fps * index, height);
                canvas.drawBitmap(b,x+trans_x+width-width/fps*index,y+trans_y,null);
                for(int i = 1; i <= n;i++) {
                    int temp_x = x + trans_x + width - width / fps * index + + width/n*(i-1) + width/n/4;
                    int temp_y = y + trans_y + + height/4;
                    if (i < (float)index/(float)fps * (float)n && i == 1) canvas.drawBitmap(bomb,temp_x,temp_y,null);
                    if (i < (float)index/(float)fps * (float)n && i == 2) canvas.drawBitmap(key,temp_x,temp_y,null);
                    if (i < (float)index/(float)fps * (float)n && i == 3) canvas.drawBitmap(magnet,temp_x,temp_y,null);
                }
            }
            // turn off
            else if(animation && !OpenClose){
                Bitmap b=Bitmap.createBitmap(OnImage, 0,0,width - width/fps * index, height);
                canvas.drawBitmap(b,x+trans_x+width/fps*index,y+trans_y,null);
                for(int i = 1; i <= n;i++) {
                    int temp_x = x + trans_x + width / fps * index + width/n*(i-1) + width/n/4;
                    int temp_y = y + trans_y + height/4;
                    if (i < (float)n - (float)index/(float)fps*(float)n && i == 1) canvas.drawBitmap(bomb,temp_x,temp_y,null);
                    if (i < (float)n - (float)index/(float)fps*(float)n && i == 2) canvas.drawBitmap(key,temp_x,temp_y,null);
                    if (i < (float)n - (float)index/(float)fps*(float)n && i == 3) canvas.drawBitmap(magnet,temp_x,temp_y,null);
                }
            }

        }
    }

    public void clicked(float x){
        if(display && !animation) {
            for (int i = 1; i <= n; i++) {
                if (x < this.x + i * this.width / n) {
                    if (i == 1 && model.bomb >= 0) {
                        model.useBomb = !model.useBomb;
                        model.useMagnet = false;
                    }  else if (i == 3 && model.magnet > 0) {
                        model.useMagnet = !model.useMagnet;
                        model.useBomb = false;
                    }
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
            index += 5;
        }
    }

    @Override
    public boolean hitTest(float x, float y, float tolerance){
        if(display == false) return false;
        return x<=this.x+width+tolerance && x>=this.x-tolerance
                && y<=this.y+height+tolerance && y>=this.y-tolerance;
    }


}
