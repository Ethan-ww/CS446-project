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
    public boolean pushinglog = false;

    // Movement
    double scale;
    double thrust;
    double gravity;
    double cur_gravity;
    double velocityX;
    double velocityY;
    double maxVelocity;
    boolean jump;
    boolean climb;

    public MoveType state=MoveType.RIGHT;

    public int char_frame;

    public Character(Context context, GameModel model, int width, int height){
        this.context=context;
        this.model=model;
        this.width=width;
        this.height=height;

        this.scale = 60 / model.fps;
        this.thrust = 10 * scale;
        this.gravity = 2 * scale;
        this.cur_gravity = gravity;
        this.velocityX = 0;
        this.velocityY = 0;
        this.maxVelocity = 15;
        this.jump = false;
        this.climb = false;

        this.char_frame = model.cur_frame;

    }

    // factory method
    public Character CharacterFactory(){

        return null;
    }

    // movement method
    public void jump(){
        velocityY -= thrust * 3;
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

    public void setY(int floorHeight){
        top=floorHeight;
    }

    public void stopGravity(){ cur_gravity = 0;}

    public void startGravity(){ cur_gravity = gravity;}

    public boolean hitTest(float x, float y, float tolerance){
        return x<=this.left+width+tolerance && x>=this.left-tolerance
                && y<=this.top+height+tolerance && y>=this.top-tolerance;
    }


    public void update(){
        if (model.haveSelectedCharacter()){
            //check if character is pushing a log
            if (pushinglog) {
                int pushv = model.structures.get(model.cur_frame).pushLog();
                if (pushv == -1) {
                    stopX();
                    pushinglog = false;
                }
                else {
                    velocityX = pushv;
                }
            }

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


            // list of characters following

            for(int i = 1; i < model.current_char.size(); i++){
                model.characters.get(model.current_char.get(i)).state = model.getCharacter().state;
                if(model.getCharacter().state==MoveType.RIGHT){
                    model.characters.get(model.current_char.get(i)).top=model.getCharacter().top;
                    model.characters.get(model.current_char.get(i)).left=model.getCharacter().left - 50 * i;
                }
                else if (model.getCharacter().state==MoveType.LEFT){
                    model.characters.get(model.current_char.get(i)).top=model.getCharacter().top;
                    model.characters.get(model.current_char.get(i)).left=model.getCharacter().left + 50 * i;
                }
                else if (model.getCharacter().state==MoveType.UP){
                    model.characters.get(model.current_char.get(i)).top=model.getCharacter().top + 30 * i;
                    model.characters.get(model.current_char.get(i)).left=model.getCharacter().left;
                }
                else if (model.getCharacter().state==MoveType.DOWN){
                    model.characters.get(model.current_char.get(i)).top=model.getCharacter().top - 30 * i;
                    model.characters.get(model.current_char.get(i)).left=model.getCharacter().left;
                }

            }

            // if character is too far away from center of the screen, do transformation
            if (left > model.structures.get(model.cur_frame).length - 100) {
                if (model.cur_frame < 9 && model.cur_frame != 3 && model.cur_frame != 6 && model.cur_frame != 9) {
                    model.setFrame(model.cur_frame + 1);
                    model.characterReborn(model.structures.get(model.cur_frame).startx, model.structures.get(model.cur_frame).starty, true,false);
                } else {
                    stopX();
                }
            } else if (left < 100 && velocityX < 0) {
                if (model.cur_frame > 0 && model.cur_frame != 4 && model.cur_frame != 7) {
                    model.setFrame(model.cur_frame - 1);
                    model.characterReborn(model.structures.get(model.cur_frame).endx, model.structures.get(model.cur_frame).endy, false,false);
                } else {
                    stopX();
                }
            } else if (left > model.point.x /3 * 2 - model.trans_x) {

                if (left >= model.structures.get(model.cur_frame).length - model.point.x /3 && velocityX > 0){}
                else if (velocityX < 0) {}
                else {
                    model.trans_x -= velocityX;
                }
            } else if (left < model.point.x /3 - model.trans_x) {

                if (left <=  model.point.x /3 && velocityX < 0){}
                else if (velocityX < 0) {
                    model.trans_x -= velocityX;
                }
            }
        }
    }


    public void draw(Canvas canvas){
    }

}
