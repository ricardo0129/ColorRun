package com.example.colorrun.gameobjects;

public class Coin extends MovingObject {
    Coin(float x, float y, float l, float w, float s){
        super(x,y,l,w);
        this.velocity.x = s;
    }

}
