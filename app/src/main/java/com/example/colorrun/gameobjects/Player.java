package com.example.colorrun.gameobjects;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PointF;

import com.example.colorrun.R;
import com.example.colorrun.ui.main.AttachedImage;

import static java.lang.Math.min;

public class Player extends MovingObject{
    private PointF pos;
    private float acceleration = 8.8f;
    private float velocity = 0.0f;


    public Player(float x, float y){
        super(x,y,100f,200f);
        pos = new PointF(x,y);
        color = Color.RED;
    }
    public void setImage(Resources res, int img){
        this.model = new AttachedImage(100,200,res, img);
    }
    public AttachedImage getImage(){ return model;}
    public boolean inAir(){
        return (acceleration>0.0f);
    }
    @Override
    public void update(){
        velocity += acceleration;
        if(pos.y+velocity < 1500.0f)
            pos.y += velocity;
        else{
            pos.y = 1500.0f;
            velocity = 0.0f;
        }
        hit.update(pos);
    }
    public void jump(){
        velocity = -110f;
    }
    public float getX(){ return pos.x;}
    public float getY(){ return pos.y;}
}
