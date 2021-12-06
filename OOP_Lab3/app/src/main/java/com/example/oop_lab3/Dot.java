package com.example.oop_lab3;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Dot implements Drawable{


    protected Point point;
    protected Paint paint;
    private int size;
    public Point getPoint() {
        return point;
    }
    public Dot(Point point, Paint paint, int size) {
        this.point = point;
        this.paint = paint;
        this.size = size;
    }

    @Override
    public void draw(Canvas canvas, Rect rect) {
        float cellSize=(float)(rect.right-rect.left)/size;
        canvas.drawRect(rect.left+point.x*cellSize,rect.top+point.y*cellSize,rect.left+point.x*cellSize+cellSize,rect.top+point.y*cellSize+cellSize,paint);
    }
}
