package com.example.oop_lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class Manager extends GestureDetector.SimpleOnGestureListener {
    private Player player;
    private Maze maze;
    private View view;
    private Exit exit;
    private Rect rect = new Rect();
    private int screenSize=0;

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        int diffX, diffY;
        diffX=Math.round(e2.getX()-e1.getX());
        diffY=Math.round(e2.getY()-e1.getY());
        if (Math.abs(diffX)>Math.abs(diffY)){
            diffX = diffX> 0 ? 1: -1;
            diffY=0;
        } else {
            diffY = diffY> 0 ? 1: -1;
            diffX=0;
        }
        int stepX=player.getX();
        int stepY= player.getY();
        while (maze.canPlayerGoTo(stepX+diffX,stepY+diffY)){
            stepX+=diffX;
            stepY+=diffY;
            if (diffX!=0){
                if (maze.canPlayerGoTo(stepX,stepY+1)
                    || maze.canPlayerGoTo(stepX,stepY-1)){
                    break;
                }
            }
            if (diffY!=0){
                if (maze.canPlayerGoTo(stepX+1,stepY)
                        || maze.canPlayerGoTo(stepX-1,stepY)){
                    break;
                }
            }
        }
        player.goTo(stepX,stepY);
        if (exit.getPoint().equals(player.getPoint())){
            create(maze.getSize()+2);
        }
        view.invalidate();
        return super.onFling(e1,e2,velocityX,velocityY);
    }

    public Manager(){
        create(5);
    }
    private void create(int size){
        maze = new Maze(size);
        exit = new Exit(maze.getEnd(),size);
        player = new Player(maze.getStart(), size);

    }
    public void draw(Canvas canvas){
        maze.draw(canvas,rect);
        exit.draw(canvas,rect);
        player.draw(canvas,rect);
    }
    public void setScreenSize(int width, int height){
        screenSize=Math.min(width,height);
        rect.set((width-screenSize)/2,
                 (height-screenSize)/2,
                (width+screenSize)/2,
              (height+screenSize)/2);
    }
}
