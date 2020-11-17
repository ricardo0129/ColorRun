package com.example.colorrun.ui.main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.colorrun.R;

public class AttachedImage {
    int x=0, y=0;
    Bitmap image;
    public AttachedImage(int x, int y, Resources res, int img){
        image = BitmapFactory.decodeResource(res, img);
        image = Bitmap.createScaledBitmap(image,x,y,false);
    }
}
