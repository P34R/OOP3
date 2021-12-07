package com.example.oop_lab3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player extends Dot{

    public Player(Point start, int size){
        super(start,getPaint(),size);
    }
    private static Paint getPaint(){
        Paint paint= new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
        return paint;
    }

    public void goTo(int x, int y){
        point.x=x;
        point.y=y;
    }

    public int getX(){
        return point.x;
    }
    public int getY(){
        return point.y;
    }

}
