package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.view.Display;
import android.support.v4.view.GestureDetectorCompat;
import android.view.GestureDetector;
import android.view.InputDevice;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.SurfaceHolder;
import android.view.ViewStructure;
import android.widget.Button;

import java.util.ArrayList;

import static android.view.MotionEvent.AXIS_HSCROLL;


/**
 * Created by ethan on 2018-05-15.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public MainThread thread;
    public GameModel model;
    Display display;
    int fps = 60;
    boolean temp = true;
    public boolean drawbegin = true;
    public boolean drawend = false;
    public boolean drawconver = false;
    public boolean cageonelock = true;
    public boolean cagetwolock = true;

    public GameView(Context context, Display d, GameModel model){
        super(context);
        getHolder().addCallback(this);
        display = d;
        this.model=model;

        thread=new MainThread(getHolder(), this);
        model.thread = thread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }


    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        if (thread != null) {
            thread.setRunning(false);
           // thread = null;
        }
    }


    @Override
    public void draw(Canvas canvas){
        canvas.translate(model.trans_x,model.trans_y);
        super.draw(canvas);

        Point p = new Point ();
        display.getSize(p);
        //canvas.scale((float)((float)p.x/(float)1600), (float)((float)p.y/(float)1000));

        Rect r = new Rect();
        r.set(0,0,p.x, p.y);
        Paint paint = new Paint();
        paint.setColor(Color.TRANSPARENT);
        if(canvas!=null){


            if (drawbegin) {
                drawbegin = model.structures.get(model.cur_frame).drawBegin(canvas, p.x, p.y);
            }
            else if (drawend && GameModel.connectionSuccess == false) {
                drawend = model.structures.get(model.cur_frame - 1).drawEnd(canvas, p.x, p.y);
                if (drawend == false && model.cur_frame == 9) {
                    ((GameActivity) getContext()).backToSelection();
                }
            } else if (drawconver) {
                drawconver = model.structures.get(model.cur_frame).drawConver(canvas, p.x, p.y);
            } else {


                if (model.structures.size() > model.cur_frame) {
                    Frame temp = model.structures.get(model.cur_frame);
                    temp.draw(canvas,model);
                }
                for(Character c: model.characters){
                    if(c.char_frame == model.cur_frame) c.draw(canvas);
                }
                if(model.haveSelectedCharacter()){
                    paint = new Paint();
                    paint.setStyle(Paint.Style.FILL);
                    paint.setColor(Color.WHITE);
                    Path path = new Path();
                    path.moveTo(model.getCharacter().left + 25, model.getCharacter().top - 25);
                    path.lineTo(model.getCharacter().left + 45, model.getCharacter().top - 25);
                    path.lineTo(model.getCharacter().left + 35,model.getCharacter().top - 5);
                    path.lineTo(model.getCharacter().left + 25, model.getCharacter().top - 25);
                    canvas.drawPath(path, paint);
                    //canvas.drawCircle(model.getCharacter().left + 35, model.getCharacter().top - 15,10,paint);
                }

                if (model.connectionSuccess) {
                    if (model.pair_frame == model.cur_frame) {
                        System.out.println("draw pair");
                        model.pair.draw(canvas);
                    }
                }


                for(UI ui: model.uis){
                    ui.draw(canvas);
                }

                String s = "";
                if (model.drawwin) {
                    s = "YOU WIN!!!";
                    Paint p1 = new Paint();
                    p1.setTextSize(200);
                    p1.setStyle(Paint.Style.FILL);
                    p1.setColor(Color.WHITE);
                    canvas.drawText(s, p.x/4-model.trans_x, p.y/2, p1);
                } else if (model.drawlose) {
                    s = "YOU LOSE!!!";
                    Paint p1 = new Paint();
                    p1.setTextSize(200);
                    p1.setStyle(Paint.Style.FILL);
                    p1.setColor(Color.WHITE);
                    canvas.drawText(s, p.x / 4 - model.trans_x, p.y / 2, p1);
                }
            }
            //model.optionalDraw(0, canvas); draw ui
            //model.optionalDraw(1,canvas); draw characters

        }
    }

    boolean isScroll = false;
    float startX = 0;
    float scrollx = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int pointerIndex = 0;
        Rect hitBox = new Rect(0,0,0,0);
        if(model.haveSelectedCharacter()){
            hitBox=new Rect(model.getCharacter().left,
                    model.getCharacter().top,
                    model.getCharacter().left+model.getCharacter().width,
                    model.getCharacter().top+model.getCharacter().height);
        }

        switch (event.getAction() & MotionEvent.ACTION_MASK & event.getActionMasked()){
            case MotionEvent.ACTION_DOWN:
                isScroll = true;

                if (drawbegin || drawend || drawconver) {
                    isScroll = false;
                    if (drawend) {
                        model.structures.get(model.cur_frame-1).d.skipOne();
                    } else {
                        model.structures.get(model.cur_frame).d.skipOne();
                    }
                }

                else {
                    Boolean handled = false;
                    for (UI ui : model.uis) {
                        if (ui.hitTest(event.getX(), event.getY(), 30)) {
                            ui.setSelected(true);
                            handled = true;
                            isScroll = false;

                            if(model.haveSelectedCharacter()){
                                if (ui.name == "LeftButton") {
                                    System.out.println("left button clicked");
                                    if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LEFT) != HitType.NULL);
                                        //System.out.println("hit left");
                                    if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN);
                                        //System.out.println("hit floor");
                                    ui.setSelected(true);
                                    if ((model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LEFT) == HitType.NULL &&
                                            (model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN ||
                                             model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_DOWN) == HitType.LOG_DOWN)) ||
                                            model.structures.get(model.cur_frame).hitTools(hitBox) == HitType.LADDER) {
                                        model.left();

                                    } else {
                                        model.getCharacter().stopX();
                                    }
                                    return true;
                                } else if (ui.name == "RightButton") {
                                    System.out.println("right button clicked");
                                    ui.setSelected(true);
                                    if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.RIGHT) != HitType.NULL);
                                        //System.out.println("hit right");
                                    if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN);
                                        //System.out.println("hit floor");
                                    //check hit log first;
                                    if (model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_LEFT) != HitType.NULL) {
                                        model.getCharacter().pushinglog = true;
                                    } else if ((model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.RIGHT) == HitType.NULL &&
                                            (model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN || model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_DOWN) != HitType.NULL)) ||
                                            model.structures.get(model.cur_frame).hitTools(hitBox) == HitType.LADDER) {
                                        model.right();

                                    } else {
                                        model.getCharacter().stopX();
                                    }
                                    return true;
                                } else if (ui.name == "UpButton") {
                                    //System.out.println("up button clicked");
                                    ui.setSelected(true);
                                    if (model.structures.get(model.cur_frame).hitTools(hitBox) == HitType.LADDER) {
                                        //&&model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN)
                                        //model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.UP) == HitType.NULL

                                        model.up();
                                        model.getCharacter().climb = true;
                                    } else {
                                        //System.out.println("hit ceiling");
                                        model.getCharacter().stopY();
                                    }
                                    return true;
                                } else if (ui.name == "DownButton") {
                                    //System.out.println("down button clicked");
                                    ui.setSelected(true);
                                    if (model.structures.get(model.cur_frame).hitTools(hitBox) == HitType.LADDER) {
                                        //model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.NULL
                                        model.down();
                                        model.getCharacter().climb = true;
                                    } else {
                                        //System.out.println("hit floor");
                                        model.getCharacter().stopY();
                                    }

                                    return true;
                                } else if (ui.name == "JumpButton") {
                                    System.out.println("jump button clicked");
                                    ui.setSelected(true);
                                    if (model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN ||
                                            model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_DOWN) == HitType.LOG_DOWN) {
                                        model.jump();
                                        model.getCharacter().jump = true;
                                    }

                                    return true;
                                }
                            }
                            if (ui.name == "Backpack") {
                                //System.out.println("backpack clicked");
                                ui.setSelected(true);
                                model.inventory.animation();
                                return true;

                            } else if (ui.name == "Inventory") {
                                model.inventory.clicked(event.getX());
                                return true;
                            }

                        }
                    }

                    if (!handled) {
                        if (model.useBomb) {
                            model.structures.get(model.cur_frame).useBomb((int) event.getX() - model.trans_x, (int) event.getY());
                            model.useBomb = false;
                            model.bomb--;
                            isScroll = false;
                            break;
                        }
                        if (model.useMagnet) {
                            model.structures.get(model.cur_frame).useMagnet((int) event.getX() - model.trans_x, (int) event.getY());
                            model.useMagnet = false;
                            model.magnet--;
                            isScroll = false;
                            break;
                        }
                    }

                    for (int i = 0; i < model.characters.size(); i++) {
                        if (model.characters.get(i).hitTest(event.getX() - model.trans_x, event.getY() - model.trans_y, 0) == true &&
                                model.characters.get(i).char_frame == model.cur_frame) {
                            if (model.current_char.contains(i)) {
                                model.current_char.remove((Integer) i);
                            } else {
                                if(!model.haveSelectedCharacter()){
                                    model.current_char.add(i);
                                }
                                else if (model.close_enough(model.getCharacter(),model.characters.get(i))){
                                    model.current_char.add(i);
                                }
                            }
                            isScroll = false;
                            break;
                        }
                    }

                    if (isScroll = true){
                        startX = event.getX() - model.trans_x;
                        return true;
                    }

                }
                break;

            case MotionEvent.ACTION_POINTER_DOWN:
                pointerIndex ++;
            
                if(model.haveSelectedCharacter()){
                    for(UI ui: model.uis){
                        if(ui.hitTest(event.getX(pointerIndex), event.getY(pointerIndex),90)){
                            ui.setSelected(true);
                            if(ui.name=="JumpButton"){
                                System.out.println("jump button clicked in multi touch");
                                ui.setSelected(true);
                                if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN ||
                                        model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_DOWN) == HitType.LOG_DOWN) {
                                    model.jump();
                                    model.getCharacter().jump=true;
                                }

                                return true;
                            }
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_POINTER_UP:
                //System.out.println("multi touch release");
                if(model.haveSelectedCharacter()){
                    for(UI ui: model.uis) {
                        if (ui.selected) {
                            if (ui.name == "JumpButton") {
                                System.out.println("jump button released");
                                ui.setSelected(false);
                                model.getCharacter().jump = false;
                                return true;
                            }
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                System.out.println("action up");
                for(UI ui: model.uis) {
                    if (ui.selected) {
                        if(model.haveSelectedCharacter()){
                            if (ui.name == "LeftButton") {
                                System.out.println("left button released");
                                ui.setSelected(false);
                                // if character in air, dont stop
                                System.out.println("stop from 4");
                                model.getCharacter().stopX();
                                //model.characters.get(0).state=0;
                                model.getCharacter().pushinglog = false;
                                return true;
                            } else if (ui.name == "RightButton") {
                                System.out.println("right button released");
                                ui.setSelected(false);
                                //model.right_release();
                                System.out.println("stop from 3");
                                model.getCharacter().stopX();
                                // stop pushing log
                                model.getCharacter().pushinglog = false;
                                //model.characters.get(0).state=0;
                                return true;
                            } else if (ui.name == "UpButton") {
                                System.out.println("up button released");
                                ui.setSelected(false);
                                model.getCharacter().stopY();
                                model.getCharacter().climb = false;
                                //model.characters.get(0).state=0;
                                return true;
                            } else if (ui.name == "DownButton") {
                                System.out.println("down button released");
                                ui.setSelected(false);
                                model.getCharacter().stopY();
                                model.getCharacter().climb = false;
                                //model.characters.get(0).state=0;
                                return true;
                            } else if (ui.name == "JumpButton") {
                                System.out.println("jump button released");
                                ui.setSelected(false);
                                model.getCharacter().jump = false;

                                return true;
                            }
                        }
                        if(ui.name == "Backpack"){
                            System.out.println("backpack released");
                            ui.setSelected(false);
                            // do something
                            return true;
                        }
                    }
                    if(isScroll){
                        scrollx = event.getX() - model.trans_x - startX;
                        if(scrollx > 50) model.trans_x += 300;
                        else if (scrollx < -50) model.trans_x += -300;
                        if (model.trans_x < -model.point.x *2) model.trans_x = -model.point.x*2;
                        else if (model.trans_x > 0) model.trans_x = 0;
                    }
                }
                return true;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }


    public void update(){

        Boolean spikesensor = false;
        Boolean sensor = false;
        Boolean lever = false;
        int spikesensorOffset = 1;
        int sensorOffset = 1;
        int leverOffset = 1;

        //check if bluetooth mode is on
        if(GameModel.connectionSuccess && model.bluetoothConnection != null) {
            //send data
            if (model.arrived == false) {
                String s = model.cur_frame + " " + model.getCharacter().left + " " + model.getCharacter().top;
                byte [] temp = s.getBytes();
                model.bluetoothConnection.write(temp);
            }
            else {
                String s = "arrived";
                byte [] temp = s.getBytes();
                model.bluetoothConnection.write(temp);
            }

            //get data
            String r = model.bluetoothConnection.incomingMessage;
            String [] l = r.split(" ");
            if (l.length == 3) {
                model.pair_frame = Integer.valueOf(l[0]);
                model.pair_x = Integer.valueOf(l[1]);
                model.pair_y = Integer.valueOf(l[2]);
                model.pair.left = model.pair_x;
                model.pair.top = model.pair_y;
            }else  if  (l.length > 0 && l[0].equals("arrived")){
                model.pair_arrive = true;
            }
        }

        if (model.haveSelectedCharacter()){
            //model.gravitySwitch(true);
            Rect hitBox=new Rect(model.getCharacter().left,
                    model.getCharacter().top,
                    model.getCharacter().left+model.getCharacter().width,
                    model.getCharacter().top+model.getCharacter().height);

            if(model.getCharacter().top==model.point.y - model.getCharacter().height){
                model.characterReborn(100,50, true,true);
            }

            HitType hittool  = model.structures.get(model.cur_frame).hitTools(hitBox);
            if (hittool == HitType.MAGNET) {
                model.magnet ++;
            }
            if (hittool == HitType.KEY) {
                model.key ++;
            }
            if (hittool == HitType.BOMB) {
                model.bomb ++;
            }
            if (hittool == HitType.SPIKE || hittool == HitType.WRAITH) {
                model.characterReborn(100, 50, true,true);
            }

            if (hittool == HitType.CAGE) {
                if (model.cur_frame == 4 && cageonelock == true ) {
                    if (model.key > 0) {
                        model.key--;
                        model.unlockCharacter(1);
                        cageonelock = false;
                    }
                }
                else if(model.cur_frame == 9 && cagetwolock == true){
                    if (model.key > 0) {
                        model.key--;
                        model.unlockCharacter(2);
                        cagetwolock = false;
                    }
                }
            }

            if (hittool == HitType.DOOR) {
                if ((GameModel.connectionSuccess || model.pair_arrive) && model.cur_frame == 3) {
                    if (model.key > 0) {
                        model.key--;
                        if (model.pair_arrive && !model.drawwin) {
                            model.drawlose = true;
                            model.pair_arrive = false;

                        } else {
                            model.arrived = true;
                            model.drawwin = true;
                        }
                    }
                }
                else {
                    if (model.key > 0) {
                        model.curlevel++;
                        model.key--;

                        if (model.cur_frame < 9) {
                            model.setFrame(model.cur_frame + 1);
                            model.characterReborn(model.structures.get(model.cur_frame).startx, model.structures.get(model.cur_frame).starty, true,false);
                        } else {
                           model.characterReborn(model.structures.get(model.cur_frame).startx, model.structures.get(model.cur_frame).starty, true,false);
                        }
                        this.drawend = true;
                    }
                }
            }

            if (hittool == HitType.FALLING_SPIKE) {
                model.characterReborn(100, 50, true,true);
            }

            spikesensor = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.SPIKESENSOR) == HitType.SPIKESENSOR;
            lever = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LEVER) == HitType.LEVER;
            sensor = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.SENSOR) == HitType.SENSOR;
            if(!spikesensor) spikesensorOffset++;
            if(!sensor) sensorOffset++;
            if(!lever) leverOffset++;
            boolean ladder = model.structures.get(model.cur_frame).hitTools(hitBox)==HitType.LADDER;
            boolean down = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN;
            boolean up = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.UP) == HitType.UP;
            boolean logdown = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_DOWN) == HitType.LOG_DOWN;
            boolean logleft = model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LOG_LEFT) == HitType.LOG_LEFT;

            if (model.haveSelectedCharacter()){
                // down or on log, not jump, not ladder
                if ((down || logdown) && model.getCharacter().jump && model.getCharacter().velocityY > 0) {
                    model.getCharacter().jump = false;
                    model.gravitySwitch(false);
                    model.getCharacter().stopY();
                }

                if((down || logdown) && !model.getCharacter().jump && !ladder) {
                    model.gravitySwitch(false);
                    model.getCharacter().stopY();
                    if (down) model.getCharacter().setY(model.structures.get(model.cur_frame).floorHeight - model.getCharacter().height + 5);


                }else if (!ladder){
                    model.gravitySwitch(true);
                }

                if(ladder){
                    model.gravitySwitch(false);
                    if(!model.getCharacter().climb) model.getCharacter().stopY();
                }


                // ceiling
                if(up && !ladder){
                    if (model.getCharacter().velocityY < 0 )model.getCharacter().stopY();
                    System.out.println("hit ceiling");
                    model.gravitySwitch(true);
                }

                if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LEFT)==HitType.LEFT
                        &&model.getCharacter().state==MoveType.LEFT){
                    model.getCharacter().stopX();
                    System.out.println("hit left");
                }

                if (logleft && model.getCharacter().velocityX > 0) {
                    model.getCharacter().pushinglog = true;
                }

                if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.RIGHT)==HitType.RIGHT
                        &&model.getCharacter().state==MoveType.RIGHT){
                    model.getCharacter().stopX();
                    System.out.println("hit right");
                }
            }

        }

        ArrayList<Character> unselectedChar = model.getUnselectedChar();
        for(Character c : unselectedChar){
            Rect hitBox=new Rect(c.left,c.top,c.left+c.width,c.top+c.height+20);
            if(lever == false) {
                if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LEVER) == HitType.LEVER){
                    lever = true;
                    for(int  i = 1; i < leverOffset; i++){
                        model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.LEVER);
                    }
                }
            }
            if (spikesensor == false){
                if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.SPIKESENSOR) == HitType.SPIKESENSOR){
                    spikesensor = true;
                    for(int i = 1; i < spikesensorOffset; i++){
                        model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.SPIKESENSOR);
                    }
                }
            }
            if (sensor == false){
                if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.SENSOR) == HitType.SENSOR){
                    sensor = true;
                    for (int i = 1; i < sensorOffset; i++){
                        model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.SENSOR);
                    }
                }
            }
            if(!spikesensor) spikesensorOffset++;
            if(!sensor) sensorOffset++;
            if(!lever) leverOffset++;
            model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN);
            if(model.structures.get(model.cur_frame).hitFloor(hitBox, HitType.DOWN) == HitType.DOWN){
                c.setY(model.structures.get(model.cur_frame).floorHeight - c.height + 5);
            }
        }
        model.update();
    }
}
