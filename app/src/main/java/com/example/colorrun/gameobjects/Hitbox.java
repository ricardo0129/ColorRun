package com.example.colorrun.gameobjects;

import android.graphics.PointF;

public class Hitbox {
    public float X1,Y1,X2,Y2;
    private PointF center;
    public Hitbox(float l, float w, PointF cent){
        X1 = -l/2f; Y1 = w/2f;
        X2 = l/2f; Y2 = -w/2f;
        center = cent;
    }
    public PointF getBotLeft(){
        return new PointF(center.x+X1,center.y+Y1);
    }
    public PointF getTopRight(){
        return new PointF(center.x+X2,center.y+Y2);
    }
    public void update(PointF pos){
        center = pos;
    }
    public boolean overlap(Hitbox B){
        float x1 = center.x+X1, x2 = center.x+X2, y1 = center.y+Y1, y2 = center.y+Y2;
        float bx1 = B.center.x+B.X1, bx2 = B.center.x+B.X2, by1 = B.center.y+B.Y1, by2 = B.center.y+B.Y2;
        if (x1 > bx2 || x2 < bx1 || y1 < by2 || y2 > by1) return false;
        return true;
        //System.out.println("BOX 1 x1 "+x1+" y1 "+y1+" x2 "+x2+" y2 "+y2);
        //System.out.println("BOX 2 x1 "+bx1+" y1 "+by1+" x2 "+bx2+" y2 "+by2);
    }
}
