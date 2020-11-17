package com.example.colorrun.gameobjects;

import android.graphics.PointF;

public class Box extends MovingObject {

    public Box(float x, float y, float l, float w, float s){
        super(x,y,l,w);
        this.velocity.x = s;
    }

}
