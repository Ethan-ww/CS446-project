package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import 	android.graphics.Matrix;

import java.util.ArrayList;

/**
 * Created by ethan on 2018-05-16.
 */

public class Protagonist extends Character {
    ArrayList<Bitmap> idle;
    ArrayList<Bitmap> runRight;
    ArrayList<Bitmap> runLeft;
    ArrayList<Bitmap> climb;


    private int drawIndex;
    private int changeRate=0; // change image per changeRate FPSs

    public Protagonist(Context context, GameModel model, int width, int height) {
        super(context, model, width, height);
        idle=new ArrayList<Bitmap>();
        runRight=new ArrayList<Bitmap>();
        runLeft=new ArrayList<Bitmap>();
        climb=new ArrayList<>();

        idle.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.idle0),width,height,false));
        idle.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.idle1),width,height,false));

        runRight.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.run00001),width,height,false));
        runRight.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.run00002),width,height,false));
        runRight.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.run00003),width,height,false));
        runRight.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.run00004),width,height,false));
        runRight.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.run00005),width,height,false));
        runRight.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.run00006),width,height,false));

        runLeft.add(FlipBitmap(runRight.get(0)));
        runLeft.add(FlipBitmap(runRight.get(1)));
        runLeft.add(FlipBitmap(runRight.get(2)));
        runLeft.add(FlipBitmap(runRight.get(3)));
        runLeft.add(FlipBitmap(runRight.get(4)));
        runLeft.add(FlipBitmap(runRight.get(5)));

        climb.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.climb00001),width,height,false));
        climb.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.climb00002),width,height,false));
        climb.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.climb00003),width,height,false));
        climb.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.climb00004),width,height,false));
        climb.add(Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.climb00005),width,height,false));


    }

    public static Bitmap FlipBitmap(Bitmap source) {
        Matrix matrix = new Matrix();
        matrix.postScale(-1, 1, source.getWidth()/2f, source.getHeight()/2f);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix, true);
    }


    public void draw(Canvas canvas){
        //this.left++;

        super.draw(canvas);

        switch (this.state){
            case 0: // idle
                canvas.drawBitmap(idle.get(drawIndex),left,top, null);
            if(changeRate==0) {
                if (drawIndex >= idle.size() - 1) {
                    drawIndex = 0;
                } else {
                    drawIndex++;
                }
                changeRate=10;
            }else{
                changeRate--;
            }
                break;

            case 1: // runRight

                canvas.drawBitmap(runRight.get(drawIndex),left,top, null);
                if(changeRate==0) {
                    if (drawIndex >= runRight.size() - 1) {
                        drawIndex = 0;
                    } else {
                        drawIndex++;
                    }
                    changeRate=6;
                }else{
                    changeRate--;
                }

                break;

            case 2: //run left
                canvas.drawBitmap(runLeft.get(drawIndex),left,top,null);
                if(changeRate==0) {
                    if (drawIndex >= runLeft.size() - 1) {
                        drawIndex = 0;
                    } else {
                        drawIndex++;
                    }
                    changeRate=15;
                }else{
                    changeRate--;
                }

                break;

            case 3: // jump
                break;

            case 4: //up
                canvas.drawBitmap(climb.get(drawIndex),left,top,null);
                if(changeRate==0) {
                    if (drawIndex >= climb.size() - 1) {
                        drawIndex = 0;
                    } else {
                        drawIndex++;
                    }
                    changeRate=15;
                }else{
                    changeRate--;
                }

                break;

            case 5: //down
                canvas.drawBitmap(climb.get(drawIndex),left,top,null);
                if(changeRate==0) {
                    if (drawIndex >= climb.size() - 1) {
                        drawIndex = 0;
                    } else {
                        drawIndex++;
                    }
                    changeRate=15;
                }else{
                    changeRate--;
                }

                break;

            default:
                break;
        }
    }
}
