package com.example.oop_lab3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;

public class MazeView extends View {
    private Manager manager;
    public MazeView(Context context, Manager manager) {
        super(context);
        this.manager=manager;
        manager.setView(this);
    }

    protected void onDraw(Canvas canvas, Rect rect) {
        manager.draw(canvas,rect);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
         super.onSizeChanged(w, h, oldw, oldh);
    }
}
