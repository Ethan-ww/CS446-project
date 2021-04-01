package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by yuqinghe on 2018/6/15.
 */

public class imageSource {
    public Bitmap key;
    public Bitmap background;
    public Bitmap bomb;
    public Bitmap magnet;
    public Bitmap cage;
    public Bitmap water;
    public Bitmap fallingSpike;
    public Bitmap branch;
    public Bitmap wraith1;
    public Bitmap wraith2;
    public Bitmap wraith3;
    public Bitmap wraith4;
    public Bitmap wraith5;
    public Bitmap wraith6;
    public Bitmap wraith7;
    public Bitmap wraith8;
    public Bitmap wraith9;
    public Bitmap wraith10;
    public Bitmap wraith11;
    public Bitmap wraith12;
    public Bitmap wraith15;
    public Bitmap wraith16;
    public Bitmap wraith17;
    public Bitmap wraith18;
    public Bitmap wraith19;
    public Bitmap wraith20;
    public ArrayList<Bitmap> wraith;
    public Bitmap spike_up;
    public Bitmap spike_left;
    public Bitmap spike_down;
    public Bitmap door;
    public Bitmap ground;
    public Bitmap log;
    public Bitmap ladder;
    public Bitmap frame1background;
    public Bitmap frame2background;
    public Bitmap frame3background;
    public Bitmap spike_right;


    static imageSource ourInstance = null;
    static imageSource getInstance(Context context, int length, int height)
    {

        if (ourInstance == null) {
            ourInstance = new imageSource (context, length, height);
            return ourInstance;
        }

        else return ourInstance;
    }


    imageSource(Context context, int length, int height) {

        bomb = compress(context,R.drawable.bomb, 10);
        magnet = compress(context,R.drawable.magnet, 10);
        cage = compress(context,R.drawable.cage, 1);
        water = compress(context,R.drawable.water, 10);
        fallingSpike = compress(context,R.drawable.fallingspike, 1);
        branch = compress(context,R.drawable.treebranch, 1);

        wraith1 = compress(context,R.drawable.wraith1, 1);
        wraith2 = compress(context,R.drawable.wraith2, 1);
        wraith3 = compress(context,R.drawable.wraith3, 1);
        wraith4 = compress(context,R.drawable.wraith4,1);
        wraith5 = compress(context,R.drawable.wraith5,1);
        wraith6 = compress(context,R.drawable.wraith6,1);
        wraith7 = compress(context,R.drawable.wraith7,1);
        wraith8 = compress(context,R.drawable.wraith8,1);
        wraith9 = compress(context,R.drawable.wraith9,1);
        wraith10 = compress(context,R.drawable.wraith10,1);
        wraith11 = compress(context,R.drawable.wraith11,1);
        wraith12 = compress(context,R.drawable.wraith12,1);
        wraith15 = compress(context,R.drawable.wraith15,1);
        wraith16 = compress(context,R.drawable.wraith16,1);
        wraith17 = compress(context,R.drawable.wraith17,1);
        wraith18 = compress(context,R.drawable.wraith18,1);
        wraith19 = compress(context,R.drawable.wraith19,1);
        wraith20 = compress(context,R.drawable.wraith20,1);

        wraith = new ArrayList<>();
        wraith.add(wraith1);
        wraith.add(wraith2);
        wraith.add(wraith3);
        wraith.add(wraith4);
        wraith.add(wraith5);
        wraith.add(wraith6);
        wraith.add(wraith7);
        wraith.add(wraith8);
        wraith.add(wraith9);
        wraith.add(wraith10);
        wraith.add(wraith11);
        wraith.add(wraith12);
        wraith.add(wraith15);
        wraith.add(wraith16);
        wraith.add(wraith17);
        wraith.add(wraith18);
        wraith.add(wraith19);
        wraith.add(wraith20);

        //spike
        spike_up = BitmapFactory.decodeResource(context.getResources(),R.drawable.spike_up);
        spike_left = BitmapFactory.decodeResource(context.getResources(),R.drawable.spike_left);
        spike_down = BitmapFactory.decodeResource(context.getResources(),R.drawable.spike_down);
        spike_right = BitmapFactory.decodeResource(context.getResources(), R.drawable.spike_right);
        door = compress(context,R.drawable.door, 1);
        key = compress(context,R.drawable.key, 1);


        ground = BitmapFactory.decodeResource(context.getResources(),R.drawable.ground);
        log = compress(context,R.drawable.log, 1);
        ladder = compress(context,R.drawable.ladder, 10);
        bomb = compress(context,R.drawable.bomb, 10);
        magnet = compress(context,R.drawable.magnet, 10);
        water = compress(context,R.drawable.water, 1);
        fallingSpike = compress(context,R.drawable.fallingspike, 1);
        branch = compress(context,R.drawable.treebranch, 1);
        frame1background = compress(context,R.drawable.backgrond001, 1);
        frame1background=Bitmap.createScaledBitmap(frame1background, length, height, false);
        frame2background = compress(context,R.drawable.background00001, 1);
        frame2background=Bitmap.createScaledBitmap(frame2background, length, height, false);
        frame3background = compress(context,R.drawable.background00004, 1);
        frame3background=Bitmap.createScaledBitmap(frame3background, length, height, false);

    }


    public Bitmap compress(Context context, int image, int compressRate){

        InputStream is = context.getResources().openRawResource(+ image);
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inJustDecodeBounds = false;
        opt.inSampleSize = compressRate;
        return BitmapFactory.decodeStream(is,null,opt);
    }
}
