package ca.uwaterloo.cs446.cs446project;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

/**
 * Created by yuqinghe on 2018/6/29.
 */

public class dialogue {

    public ArrayList <String> start;
    public ArrayList <String> end;
    public ArrayList <String> conver1;
    public ArrayList <String> conver2;
    public int cur = 0;
    public int count = 0;

    public dialogue (int i) {
        start = new ArrayList<>();
        end = new ArrayList <> ();
        conver1 = new ArrayList<>();
        conver2 = new ArrayList<>();
        if (i == 0) {

            start.add("If fate is a lonely river, who will be the best ferry man of your soul?");
            start.add("Jack: Where am I?");
            start.add("Jack: Where are my parents?");
            start.add("Jack: Where is this place?");
            start.add("FerryMan: Hi Jack, welcome to the WasteLand");
            start.add("Jack: Who are you? I remember the car crash, am I dead?");
            start.add("FerryMan: I am nobody, I exist because you need me");
            start.add("FerryMan: Your soul is stuck here, cross the WasteLand to find you destiny");
            start.add("Jack: Wait! Where are my parents?");

            end.add("FerryMan: Congratulations Jack. But life is a journey that never ends");
        }

        else if (i == 1) {


            conver1.add("Jack: Mom, you are here!");
            conver1.add("Jack: How can I get you out of here?");
            conver1.add("Mom: OH Jack, you made it here");
            conver1.add("Mom: Go find the key to save me");

            end.add("Jack: Mom, where is Dad?");
            end.add("Mom: We should keep on going");

        }

        else if (i == 2) {
            conver1.add("Wait for me, I will find the key");


            end.add("Jack: Did I make it? Dad, Mom, where are you?");
            end.add("FerryMan: Unfortunately, our journey with you end here");
            end.add("Jack: Mom, Dad, is that you? Are you my FerryMan all along?");
            end.add("Jack: Are we dead?");
            end.add("Mom: I am afraid you are the only one that survived");
            end.add("Mom: Go on. Continue your journal. Go back to the real world");
            end.add("Dad: We will always be with you in your heart");
            
        }
    }

    public boolean draw(Canvas c, int x, int y, int type) {

        ArrayList<String> s;
        if (type == 0) {
            s = start;
        } else if (type == 1) {
            s = end;
        } else {
            s = conver1;
        }
        if (cur == s.size()) {
            cur = 0;
            count = 0;
            return false;
        }
        else if (count == 300) {
            count = 0;
            cur++;

        } else {
            count++;
        }
        Paint p = new Paint();
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.BLACK);
        p.setTextSize(50);

        c.drawRect(new Rect(0, 0, x, y), p);
        p.setColor(Color.WHITE);
        c.drawText(s.get(cur), 100, y/2, p);
        return true;
    }

    public void skipOne () {
        cur++;
        count= 0;
    }
}
