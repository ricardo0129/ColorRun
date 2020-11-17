package com.example.colorrun.gameobjects;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.PointF;

import com.example.colorrun.ui.main.AttachedImage;

public class MovingObject {
    protected PointF pos = new PointF(0f,0f);
    protected PointF velocity = new PointF(0f,0f);
    protected PointF acceleration = new PointF(0f,0f);
    protected int mass=80;
    public Hitbox hit;
    protected int color = Color.BLACK;
    protected AttachedImage model;

    public MovingObject(float x, float y, float l, float w){
        pos = new PointF(x,y);
        hit = new Hitbox(l,w,pos);
    }
    public int getColor() { return color;}

    public PointF getPos() {
        return pos;
    }
    public void setImage(Resources res, int img){
        this.model = new AttachedImage(100,200,res, img);
    }
    public AttachedImage getImage(){ return model;}

    public void update(){
        velocity.x += acceleration.x; velocity.y += acceleration.y;
        pos.x += velocity.x; pos.y += velocity.y;
        hit.update(pos);
    }

    public boolean collides(MovingObject obj){
        return hit.overlap(obj.hit);
    }
    public void reset() {pos.x=800;}
    public PointF getBL() {return new PointF(pos.x+hit.X1,pos.y+hit.Y1);}
    public PointF getBR() {return new PointF(pos.x+hit.X2,pos.y+hit.Y1);}
    public PointF getTL() {return new PointF(pos.x+hit.X1,pos.y+hit.Y2);}
    public PointF getTR() {return new PointF(pos.x+hit.X2,pos.y+hit.Y2);}

}
