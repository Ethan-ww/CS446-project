package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;


/**
 * Created by ethan on 2018-05-15.
 */

public class Character {
    private Context context;
    protected GameModel model;

    // Bounding box for physical collision
    public int top=700;
    public int left=50;
    public int width;
    public int height;

    // Movement
    double scale;
    double thrust;
    double gravity;
    double cur_gravity;
    double velocityX;
    double velocityY;
    double maxVelocity;
    boolean jump;



    public int state=0;
    // 0: idle
    // 1: run
    // 2: run left
    // 3: jump

    // drawable

    public Character(Context context, GameModel model, int width, int height){
        this.context=context;
        this.model=model;
        this.width=width;
        this.height=height;

        this.scale = 180 / model.fps;
        this.thrust = 10 * scale;
        this.gravity = 2 * scale;
        this.cur_gravity = gravity;
        this.velocityX = 0;
        this.velocityY = 0;
        this.maxVelocity = 15;
        this.jump = false;

    }

    // factory method
    public Character CharacterFactory(){

        return null;
    }

    // movement method
    public void jump(){
        velocityY -= thrust * 2.5;
        jump = true;
    }

    public void thrustUp() {
        velocityY -= thrust;
        if(velocityY > maxVelocity) velocityY = maxVelocity;
        if(velocityY < -maxVelocity) velocityY = -maxVelocity;
    }

    public void thrustDown() {
        velocityY += thrust;
        if(velocityY > maxVelocity) velocityY = maxVelocity;
        if(velocityY < -maxVelocity) velocityY = -maxVelocity;
    }

    public void thrustLeft() {
        velocityX -= thrust;
        if(velocityX > maxVelocity) velocityX = maxVelocity;
        if(velocityX < -maxVelocity) velocityX = -maxVelocity;
    }

    public void thrustRight() {
        velocityX += thrust;
        if(velocityX > maxVelocity) velocityX = maxVelocity;
        if(velocityX < -maxVelocity) velocityX = -maxVelocity;
    }

    public void stopX(){
        velocityX = 0;
    }

    public void stopY(){
        velocityY = 0;
    }

    public void stopGravity(){ cur_gravity = 0;}

    public void startGravity(){ cur_gravity = gravity;}

    public void update(){

        // apply gravity
        velocityY += cur_gravity;

        // update position
        top = top + (int) velocityY;
        left = left + (int) velocityX;

        // if character is standing on the bottom of the screen
        if(top >= model.point.y - height) {
            stopY();
            top = model.point.y - height;
        }

        // if character is jumping too high
        if(top < 0){
            top = 0;
            stopY();
        }

        // TO DO: if character is too far away from center of the screen, do transformation
        if (left > model.point.x /5 * 4) {
            left = model.point.x /5 * 4;
            stopX();
        }
        if (left < model.point.x /5){
            left = model.point.x/5;
            stopX();
        }


    }


    public void draw(Canvas canvas){
    }
}
