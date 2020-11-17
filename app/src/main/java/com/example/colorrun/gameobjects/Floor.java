package com.example.colorrun.gameobjects;

import android.graphics.PointF;

public class Floor extends MovingObject{
        Floor(float x, float y, float l, float w, float s){
        super(x,y,l,w);
        this.velocity.x = s;
    }
    @Override
    public boolean collides(MovingObject obj){
            obj.acceleration = new PointF(0.0f,0.0f);
            obj.velocity = new PointF(0.0f,0.0f);
        return hit.overlap(obj.hit);
    }
}
