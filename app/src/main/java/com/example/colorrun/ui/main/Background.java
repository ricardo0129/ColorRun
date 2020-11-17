package com.example.colorrun.ui.main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.colorrun.R;

public class Background {
    int x=0, y=0;
    Bitmap background;
    Background(int x, int y, Resources res){
        background = BitmapFactory.decodeResource(res, R.drawable.background);
        background = Bitmap.createScaledBitmap(background,x,y,false);
    }
}
