package ca.uwaterloo.cs446.cs446project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by julialiu on 2018-05-20.
 */

public class Frame {

    ArrayList <PhysicalModel> floors = new ArrayList<>();
    Point point;
    public static ArrayList <Bitmap> backgrounds = new ArrayList<>();
    public int frame_num;
    int floorHeight;

    public Frame (int num, Point point, Context context) {

        frame_num = num;
        this.point = point;
        ArrayList <Rect> src = new ArrayList<>();
        ArrayList <Rect> dest = new ArrayList<>();
        Bitmap ground = BitmapFactory.decodeResource(context.getResources(),R.drawable.ground);
        Bitmap log = BitmapFactory.decodeResource(context.getResources(),R.drawable.log);
        Bitmap ladder = BitmapFactory.decodeResource(context.getResources(),R.drawable.ladder);
        Bitmap bomb = BitmapFactory.decodeResource(context.getResources(),R.drawable.bomb);
        Bitmap magnet = BitmapFactory.decodeResource(context.getResources(),R.drawable.magnet);
        //tumbler
        Bitmap tumbler = BitmapFactory.decodeResource(context.getResources(),R.drawable.tumbler);
        Bitmap tumbler_left10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tumbler_left10);
        Bitmap tumbler_right10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tumbler_right10);
        Bitmap tumbler_left5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tumbler_left5);
        Bitmap tumbler_right5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.tumbler_right5);

        ArrayList <Bitmap> wabble = new ArrayList<>();
        wabble.add(tumbler);
        wabble.add(tumbler_right5);
        wabble.add(tumbler_right10);
        wabble.add(tumbler_right5);
        wabble.add(tumbler);
        wabble.add(tumbler_left5);
        wabble.add(tumbler_left10);
        wabble.add(tumbler_left5);

        //wraith
        Bitmap wraith1 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith1);
        Bitmap wraith2 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith2);
        Bitmap wraith3 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith3);
        Bitmap wraith4 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith4);
        Bitmap wraith5 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith5);
        Bitmap wraith6 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith6);
        Bitmap wraith7 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith7);
        Bitmap wraith8 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith8);
        Bitmap wraith9 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith9);
        Bitmap wraith10 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith10);
        Bitmap wraith11 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith11);
        Bitmap wraith12 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith12);
        Bitmap wraith15 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith15);
        Bitmap wraith16 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith16);
        Bitmap wraith17 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith17);
        Bitmap wraith18 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith18);
        Bitmap wraith19 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith19);
        Bitmap wraith20 = BitmapFactory.decodeResource(context.getResources(),R.drawable.wraith20);

        ArrayList <Bitmap> wraith = new ArrayList<>();
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

        //rock
        Bitmap rock = BitmapFactory.decodeResource(context.getResources(),R.drawable.rock);

        //spike
        Bitmap spike_up = BitmapFactory.decodeResource(context.getResources(),R.drawable.spike_up);
        Bitmap spike_left = BitmapFactory.decodeResource(context.getResources(),R.drawable.spike_left);
        Bitmap spike_down = BitmapFactory.decodeResource(context.getResources(),R.drawable.spike_down);


        //door
        Bitmap door = BitmapFactory.decodeResource(context.getResources(),R.drawable.door);

        //key
        Bitmap key = BitmapFactory.decodeResource(context.getResources(),R.drawable.key);


        if (num == 0) {
            //first frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, point.x, ground.getHeight()));
            dest.add( new Rect (0, point.y - 200, point.x, point.y));
            floors.add(new Floor(context, ground, src, dest));



        }
        else if (num == 1) {
            //second frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, 300, ground.getHeight()));
            dest.add( new Rect (0, point.y-200, 250, point.y));

            src.add(new Rect (0, 0, 200, ground.getHeight()));
            dest.add(new Rect (350, point.y - 350, 550, point.y - 250));

            src.add(new Rect (300, 0, 750, ground.getHeight()));
            dest.add(new Rect (600, point.y-200, 1050, point.y));

            src.add(new Rect (400, 0, 800, ground.getHeight()));
            dest.add(new Rect (1300, point.y-450, point.x, point.y));

            floors.add(new Floor(context, ground, src, dest));



        }
        else if (num == 2) {
            //third frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, 800, ground.getHeight()));
            dest.add( new Rect (0, point.y - 370, 750, point.y));

            src.add(new Rect (200, 0, 1000, ground.getHeight()));
            dest.add(new Rect (1200, point.y - 200, point.x, point.y));

            ArrayList<Rect> log_src = new ArrayList<>();
            ArrayList<Rect> log_dest = new ArrayList<>();
            log_src.add(new Rect(0, 0, log.getWidth(), log.getHeight()));
            log_dest.add(new Rect(250, point.y/11*6, 300+log.getWidth()/2,
                    point.y/11*6+log.getHeight()/3));
            floors.add(new log(context, log, log_src, log_dest, 250, 300+log.getWidth()/2, 3));
            floors.add(new Floor(context, ground, src, dest));


        }

        else if (num == 3) {
            //forth frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, 400, ground.getHeight()));
            dest.add( new Rect (0, point.y-250, 400, point.y));

            src.add(new Rect (800, 0, 950, ground.getHeight()));
            dest.add( new Rect (475, point.y-350, 625, point.y));

            src.add(new Rect (500, 0, 800, ground.getHeight()));
            dest.add( new Rect (675, point.y - 350, 975, point.y - 300));

            src.add(new Rect (0, 0, 250, ground.getHeight()));
            dest.add( new Rect (440, point.y - 550, 690, point.y - 500));

            src.add(new Rect (0, 0, 300, ground.getHeight()));
            dest.add( new Rect (50, point.y - 700, 350, point.y -650));

            src.add(new Rect (100, 0, 670, ground.getHeight()));
            dest.add( new Rect (400, point.y - 850, 970, point.y -800));

            src.add(new Rect (0, 0, point.x - 625, ground.getHeight()));
            dest.add( new Rect (625, point.y - 200, point.x, point.y ));

            src.add(new Rect (0, 0, 75, ground.getHeight()));
            dest.add( new Rect (1150, point.y - 400, 1225, point.y ));

            src.add(new Rect (1400, 0, point.x, ground.getHeight()));
            dest.add( new Rect (1400, point.y - 500, point.x, point.y - 450 ));

            ArrayList<Rect> spike_src = new ArrayList<>();
            ArrayList<Rect> spike_dest = new ArrayList<>();
            spike_src.add(new Rect(0, 0, spike_up.getWidth(), spike_up.getHeight()));
            spike_dest.add(new Rect(500, point.y - 950, 700,point.y-850));
            spike_src.add(new Rect(0, 0, spike_up.getWidth(), spike_up.getHeight()));
            spike_dest.add(new Rect(1225, point.y - 280, 1600,point.y-130));

            ArrayList<Rect> spike_src_left = new ArrayList<>();
            ArrayList<Rect> spike_dest_left = new ArrayList<>();
            spike_src_left.add(new Rect(0, 0, spike_left.getWidth(), spike_left.getHeight()));
            spike_dest_left.add(new Rect(1100, point.y - 400, 1170,point.y - 100));


            floors.add(new spike(context, spike_up, spike_src, spike_dest));
            floors.add(new spike(context, spike_left, spike_src_left, spike_dest_left));
            floors.add(new Floor(context, ground, src, dest));





        }

        else if (num == 4) {
            //fifth frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, 300, ground.getHeight()));
            dest.add( new Rect (0, point.y - 200, 300, point.y));

            src.add(new Rect (500, 0, 1030, ground.getHeight()));
            dest.add( new Rect (430, point.y - 270, 900, point.y));

            src.add(new Rect (1000, 0, 1400, ground.getHeight()));
            dest.add( new Rect (1000, point.y - 200, 1400, point.y));

            src.add(new Rect (1000, 0, 1400, ground.getHeight()));
            dest.add( new Rect (1300, point.y - 350, point.x, point.y));

            src.add(new Rect (200, 0, 400, ground.getHeight()));
            dest.add( new Rect (200, point.y - 650, 400, point.y-600));

            src.add(new Rect (550, 0, 700, ground.getHeight()));
            dest.add( new Rect (550, point.y - 750, 700, point.y-700));

            src.add(new Rect (850, 0, 1200, ground.getHeight()));
            dest.add( new Rect (850, point.y - 800, 1200, point.y-750));

            src.add(new Rect (200, 0, 400, ground.getHeight()));
            dest.add( new Rect (1350, point.y - 650, 1550, point.y-600));

            //ladder
            ArrayList<Rect> ladder_src = new ArrayList<>();
            ArrayList<Rect> ladder_dest = new ArrayList<>();
            ladder_src.add(new Rect (0, 0, ladder.getWidth(), ladder.getHeight() ));
            ladder_dest.add(new Rect (1400, point.y-950, 1500, point.y - 600 ));

            floors.add (new ladder(context, ladder, ladder_src, ladder_dest, 10, 0, point.y-550));
            floors.add(new Floor(context, ground, src, dest));
        }

        else if (num == 5) {
            //sixth frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, 500, ground.getHeight()));
            dest.add( new Rect (0, point.y-200, 300, point.y));

            src.add(new Rect (0, 0, 700, ground.getHeight()));
            dest.add( new Rect (400, point.y - 300, 850, point.y));

            src.add(new Rect (0, 0, 600, ground.getHeight()));
            dest.add( new Rect (1000, point.y - 220, point.x, point.y));

            src.add(new Rect (150, 0, 400, ground.getHeight()));
            dest.add( new Rect (150, point.y - 650, 400, point.y-600));

            src.add(new Rect (500, 0, 600, ground.getHeight()));
            dest.add( new Rect (500, point.y - 700, 600, point.y-650));

            src.add(new Rect (300, 0, 700, ground.getHeight()));
            dest.add( new Rect (800, point.y - 750, 1100, point.y-700));

            src.add(new Rect (0, 0, point.x-1350, ground.getHeight()));
            dest.add( new Rect (1200, point.y - 600, point.x, point.y-550));


            //ladder
            ArrayList<Rect> ladder_src = new ArrayList<>();
            ArrayList<Rect> ladder_dest = new ArrayList<>();
            ladder_src.add(new Rect (0, 0, ladder.getWidth(), ladder.getHeight() ));
            ladder_dest.add(new Rect (1250, point.y - 950, 1350, point.y - 550 ));

            //wraith
            ArrayList<Rect> wraith_src = new ArrayList<>();
            ArrayList<Rect> wraith_dest = new ArrayList<>();
            wraith_src.add(new Rect (0, 0, wraith1.getWidth(), wraith1.getHeight() ));
            wraith_dest.add(new Rect (1300, point.y - 450, 1500, point.y - 200 ));

            floors.add(new wraith(context, wraith1, wraith_src, wraith_dest, 20, wraith, 1300));
            floors.add (new ladder(context, ladder, ladder_src, ladder_dest, 10, 0, point.y-550));
            floors.add(new Floor(context, ground, src, dest));
        }

        else if (num == 6) {
            //forth frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, point.x, ground.getHeight()));
            dest.add( new Rect (point.x-800, point.y - 150, point.x, point.y));

            src.add(new Rect (0, 0, point.x, ground.getHeight()));
            dest.add( new Rect (0, point.y - 420, point.x, point.y-350));

            //magnet
            ArrayList<Rect> magnet_src = new ArrayList<>();
            ArrayList<Rect> magnet_dest = new ArrayList<>();
            magnet_src.add(new Rect (0, 0, magnet.getWidth(), magnet.getHeight() ));
            magnet_dest.add(new Rect (point.x-700, point.y-350 ,  point.x - 500, point.y - 150 ));

            //tumbler
            ArrayList<Rect> tumbler_src = new ArrayList<>();
            ArrayList<Rect> tumbler_dest = new ArrayList<>();
            tumbler_src.add(new Rect (0, 0, rock.getHeight()+500, rock.getHeight()+300));
            tumbler_dest.add(new Rect (550, point.y-750 ,  900, point.y - 370 ));



            floors.add(new Floor(context, ground, src, dest));
            floors.add(new magnet(context, magnet, magnet_src, magnet_dest, point.y-200, point.x-500));
            floors.add(new tumbler(context, rock, tumbler_src, tumbler_dest, 15, wabble, 550, point.y - 750));




        }



        else if (num == 7){
            //fifth frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, 300, ground.getHeight()));
            dest.add( new Rect (0, point.y - 400, 300, point.y-350));

            src.add(new Rect (0, 0, point.x, ground.getHeight()));
            dest.add( new Rect (0, point.y - 150, point.x, point.y));

            src.add(new Rect (0,0, 800, ground.getHeight()));
            dest.add( new Rect (point.x - 800, point.y - 650, point.x, point.y-600));

            src.add(new Rect (0, 0, 400, ground.getHeight()));
            dest.add( new Rect (point.x - 500, point.y-850, point.x, point.y-800));

            //ladder
            ArrayList<Rect> ladder_src = new ArrayList<>();
            ArrayList<Rect> ladder_dest = new ArrayList<>();
            ladder_src.add(new Rect (0, 0, ladder.getWidth(), ladder.getHeight() ));
            ladder_dest.add(new Rect (point.x-750, 0, point.x - 600, point.y - 550 ));

            //bomb
            ArrayList<Rect> bomb_src = new ArrayList<>();
            ArrayList<Rect> bomb_dest = new ArrayList<>();
            bomb_src.add(new Rect (0, 0, bomb.getWidth(), bomb.getHeight() ));
            bomb_dest.add(new Rect (point.x-300, 120, point.x-200, 220 ));



            floors.add(new Floor(context, ground, src, dest));
            floors.add (new ladder(context, ladder, ladder_src, ladder_dest, 10, 0, point.y-550));
            floors.add(new bomb(context, bomb, bomb_src, bomb_dest, 120, point.x-300));


        }

        else if (num == 8) {
            //sixth frame
            backgrounds.add(ground);
            src.add(new Rect (0, 0, point.x, ground.getHeight()));
            dest.add( new Rect (0, point.y - 150, point.x, point.y));

            ArrayList<Rect> wraith_src = new ArrayList<>();
            ArrayList<Rect> wraith_dest = new ArrayList<>();
            wraith_src.add(new Rect (0, 0, wraith1.getWidth(), wraith1.getHeight() ));
            wraith_dest.add(new Rect (point.x/2, point.y - 400, point.x/2 + 200, point.y - 150 ));

            floors.add(new Floor(context, ground, src, dest));
            floors.add(new wraith(context, wraith1, wraith_src, wraith_dest, 20, wraith, point.x/2));



        }

        else if (num == 9) {

            backgrounds.add(ground);
            src.add(new Rect (0, 0, 700, ground.getHeight()));
            dest.add( new Rect (0, point.y - 550, 200, point.y));

            src.add(new Rect (150, 0, 600, ground.getHeight()));
            dest.add( new Rect (350, point.y - 400, 500, point.y));

            src.add(new Rect (0, 0, 150, ground.getHeight()));
            dest.add( new Rect (500, point.y - 300, 650, point.y));

            src.add(new Rect (300, 0, 550, ground.getHeight()));
            dest.add( new Rect (800, point.y - 300, 1050, point.y));

            src.add(new Rect (0, 0, 600, ground.getHeight()));
            dest.add( new Rect (1050, point.y - 400, 1250, point.y));

            src.add(new Rect (300, 0, 550, ground.getHeight()));
            dest.add( new Rect (1250, point.y - 200, 1400, point.y));

            src.add(new Rect (0, 0, 400, ground.getHeight()));
            dest.add( new Rect (250, point.y - 650, 650, point.y-600));

            src.add(new Rect (300, 0, 500, ground.getHeight()));
            dest.add( new Rect (750, point.y - 770,950,  point.y - 700));

            src.add(new Rect (0, 0, point.x - 1050, ground.getHeight()));
            dest.add( new Rect (1050, point.y - 830, point.x, point.y-750));

            ArrayList<Rect> door_src = new ArrayList<>();
            ArrayList<Rect> door_dest = new ArrayList<>();
            door_src.add(new Rect (0, 0, door.getWidth(), door.getHeight() ));
            door_dest.add(new Rect (point.x - 370, point.y - 1050, point.x - 250, point.y - 800 ));

            ArrayList<Rect> key_src = new ArrayList<>();
            ArrayList<Rect> key_dest = new ArrayList<>();
            key_src.add(new Rect (0, 0, key.getWidth(), key.getHeight() ));
            key_dest.add(new Rect (1300, point.y - 370, 1370, point.y - 250 ));

            ArrayList<Rect> spike_src = new ArrayList<>();
            ArrayList<Rect> spike_dest = new ArrayList<>();
            spike_src.add(new Rect (0, 0, spike_down.getWidth(), spike_down.getHeight() ));
            spike_dest.add(new Rect (450, point.y - 610, 650, point.y - 540 ));

            floors.add(new key(context, key, key_src, key_dest, 1300, point.y - 470));
            floors.add(new spike(context, spike_down, spike_src, spike_dest));
            floors.add(new door(context, door, door_src, door_dest));
            floors.add(new Floor(context, ground, src, dest));




        }


    }

    public void draw (Canvas canvas) {

        for (PhysicalModel p : floors) {
            p.draw(canvas);
        }

    }

    //for floor hit test, return LEFT, RIGHT, UP, DOWN
    public HitType hitFloor(Rect rect, HitType type) {
        for (int i = 0; i < floors.size(); i++) {
            if (floors.get(i).hitModel(rect, type) != HitType.NULL) {
                if (type == HitType.DOWN) {
                    floorHeight = ((Floor) floors.get(i)).getFloorHeight();
                }
                return type;
            }
        }
        return HitType.NULL;
    }

    //for dynamic model or tools hit test (ladder, bomb, magnet, wraith, etc), returns the type of hit item
    public HitType hitTools(Rect rect) {
        for (int i = 0; i < floors.size(); i++) {
            HitType result = floors.get(i).hitModel(rect);
            if (result != HitType.NULL) {
                return result;
            }
        }
        return HitType.NULL;
    }
}
