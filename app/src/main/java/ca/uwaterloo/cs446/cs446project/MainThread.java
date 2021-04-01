package ca.uwaterloo.cs446.cs446project;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

/**
 * Created by ethan on 2018-05-15.
 */

public class MainThread extends Thread {
    private SurfaceHolder surfaceHolder; // contain the canvas
    private GameView gameView; // thing to put it all together

    private boolean running=false; // this indicates whether the thread is running or not
    public Canvas canvas;

    private int targetFPS;
    private int averageFPS=0; // show the actual FPS

    public MainThread(SurfaceHolder surfaceHolder, GameView gameView){
        super();
        this.surfaceHolder=surfaceHolder;
        this.gameView=gameView;
        this.targetFPS = gameView.fps;
    }

    @Override
    public void run(){
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime=0;
        int frameCount=0;
        long targetTime=1000/targetFPS;

        while (running){
            startTime=System.nanoTime();
            canvas=null;

            try{
                    canvas = this.surfaceHolder.lockCanvas();
                    // lock canvas freeze it and allow us to draw on it

                    synchronized (surfaceHolder) {
                        this.gameView.update();
                        this.gameView.draw(canvas);

                    }
            }catch (Exception e){
                // need exception handler
            }finally {
                if(canvas!=null){
                    try{
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            timeMillis= (System.nanoTime()-startTime)/1000000;
            waitTime=targetTime-timeMillis;

            try {
                this.sleep(waitTime);
            }catch (Exception e){
                //System.err.println("Failed to sleep MainThread");
            }

            totalTime+=(System.nanoTime()-startTime);
            ++frameCount;

            if(frameCount==targetFPS){
                averageFPS=(int)(1000/((totalTime/frameCount)/1000000));
                frameCount=0;
                totalTime=0;
                //System.out.println("Average FPS: "+ averageFPS);
            }

        }
    }

    public void setRunning(boolean running){
        // true: turn on this thread
        // false: off
        this.running=running;
    }
}
