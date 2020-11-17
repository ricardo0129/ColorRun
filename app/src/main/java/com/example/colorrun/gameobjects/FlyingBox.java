package com.example.colorrun.gameobjects;

import java.util.Random;

public class FlyingBox extends MovingObject {
    private float startY;
    private double angle = 0.0;
    public FlyingBox(float x, float y, float l, float w, float s){
        super(x,y,l,w);
        this.velocity.x = s;
        startY = y;
        angle = new Random().nextFloat()*Math.PI;
    }
    @Override
    public void update() {
        this.pos.y = (float)startY+200*(float)Math.sin(angle);
        pos.x += velocity.x;
        hit.update(pos);
        angle+=0.05;
    }
}
