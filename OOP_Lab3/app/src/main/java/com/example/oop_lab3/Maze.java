package com.example.oop_lab3;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class Maze implements Drawable{
    private Paint wallPaint;
    private final boolean[][] mazeArray;


    private final int size;
    private final Point end=new Point(1,1);
    private int bestScore = 0;

    public Point getEnd() {
        return end;
    }

    public Point getStart() {
        return start;
    }

    private Point start;
    public Maze(int size){
        wallPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        wallPaint.setColor(Color.MAGENTA);
        this.size=size;
        mazeArray = new boolean[size][size];
        generateMaze();
    }

    public int getSize() {
        return size;
    }
    private void generateMaze(){
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                mazeArray[i][j]=i%2!=0 && j%2!=0
                                && i<size-1 && j<size-1;
            }
        }
        Random random = new Random();
        Stack<Point>  route=new Stack<>();
        route.push(end);
        while (route.size()>1){
            Point current=route.peek();
            List<Point> unuseNeighbours=new LinkedList<>();
            // left
            if (current.x>2){
                if (!isUsedCell(current.x-2,current.y)){
                    unuseNeighbours.add(new Point(current.x-2,current.y));
                }
            }// top
            if (current.y>2){
                if (!isUsedCell(current.x,current.y-2)){
                    unuseNeighbours.add(new Point(current.x,current.y-2));
                }
            }
            // right
            if (current.x<size-2){
                if (!isUsedCell(current.x+2,current.y)){
                    unuseNeighbours.add(new Point(current.x+2,current.y));
                }
            }
            //
            if (current.y<size-2){
                if (!isUsedCell(current.x,current.y+2)){
                    unuseNeighbours.add(new Point(current.x,current.y+2));
                }
            }
            if (unuseNeighbours.size()>0){
                int rnd=random.nextInt(unuseNeighbours.size());
                Point direction=unuseNeighbours.get(rnd);
                int diffX=(direction.x-current.x)/2;
                int diffY=(direction.y-current.y)/2;
                mazeArray[current.y+diffY][current.x+diffX]=true;
                route.push(direction);
            }else {
                if (route.size()>bestScore){
                    bestScore=route.size();
                    start=current;
                }
                route.pop();
            }
        }

    }
    private boolean isUsedCell(int x,int y){
        if (x<0 || y<0 || x>=size-1 || y>=size-1){
            return true;
        }
        return     mazeArray[y-1][x] //left
                || mazeArray[y][x-1] //top
                || mazeArray[y+1][x] //right
                || mazeArray[y][x+1];//bottom

    }
    public boolean canPlayerGoTo(int x,int y){
        return mazeArray[y][x];
    }
    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize=(float)(rect.right-rect.left) / size;
        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                if (!mazeArray[i][j]){
                    float left=j*cellSize+rect.left;
                    float top = j*cellSize+rect.top;
                    canvas.drawRect(left,top,left+cellSize,top+cellSize, wallPaint);
                }
            }
        }
    }
}
