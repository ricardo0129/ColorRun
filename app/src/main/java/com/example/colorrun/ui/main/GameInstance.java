package com.example.colorrun.ui.main;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;

import com.example.colorrun.R;
import com.example.colorrun.gameobjects.Box;
import com.example.colorrun.gameobjects.FlyingBox;
import com.example.colorrun.gameobjects.MovingObject;
import com.example.colorrun.gameobjects.Player;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameInstance extends SurfaceView implements Runnable {
    private Paint paint = new Paint();
    private Thread thread;
    private int score = 0 ;
    private Random rand = new Random(System.currentTimeMillis());
    private Long starTime = System.currentTimeMillis();
    int x = 1080, y=2064;
    private Background b1 = new Background(x,y,getResources());
    private Background b2 = new Background(x,y,getResources());
    private Player player = new Player(100f,1500f);
    private ArrayList<MovingObject> obstacles = new ArrayList<>();
    private boolean isRunning=true;
    private Button b;
    SurfaceHolder surfaceHolder = getHolder();


    public GameInstance(Context context) {
        super(context);
        b2.x = 1080;
        b1.x = 0;
        init(null,0);
    }
    public GameInstance(Context context, AttributeSet attr){
        super(context,attr);
        b2.x = 1080;
        b1.x = 0;
        player.setImage(getResources(),R.drawable.monkey);
        init(attr,0);
    }
    public GameInstance(Context context, AttributeSet attr, int defStyle){
        super(context,attr,defStyle);
        b2.x = 1080;
        b1.x = 0;
        init(attr,defStyle);
    }

    private void init(AttributeSet attrs, int defStyle){
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setTextSize(200);
        paint.setTypeface(Typeface.create("sans-serif",Typeface.ITALIC));

    }

    private void showScore(Canvas canvas){
        canvas.drawText(toString().valueOf(score),200f,200f,paint);
    }
    private void drawPlayer(Canvas canvas){
        canvas.drawBitmap(player.getImage().image,player.getX()-50,player.getY()-100,paint);
    }

    private void drawBox(Canvas canvas){
        for(MovingObject obj: obstacles) {
            //paint.setColor(obj.getColor());
            //Paint p2 = new Paint();
            //ColorFilter filter = new LightingColorFilter(Color.RED, 0);
            //p2.setColorFilter(filter);
            canvas.drawBitmap(obj.getImage().image,(int)obj.getTL().x,(int)obj.getTL().y,paint);
        }
    }
    public void prepare(){
        isRunning = true;
        run();
    }
    public void endGame(){
        isRunning = false;
    }
    public void resetGame(){
        isRunning = true;
        score = 0;
        run();
    }
    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
    }

    @Override
    public void run() {
        Long x1 = System.currentTimeMillis();
        int frames=0;
        while(isRunning){
            update();
            draw();
            frames++;
            System.out.println("asd");
            if(System.currentTimeMillis()-x1>=1000) {
                System.out.print("FPS:"+frames);
                frames=0;
                x1 = System.currentTimeMillis();
            }
        }
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        float touchX = motionEvent.getX();
        float touchY = motionEvent.getY();
        switch(motionEvent.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!player.inAir())
                    player.jump();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        invalidate();
        return true;
    }
    public void update(){
        b1.x-=10;
        b2.x-=10;
        player.update();

        if(System.currentTimeMillis()-starTime>=100 && obstacles.size()<=3){
            starTime = System.currentTimeMillis();
            MovingObject b;
            score++;
            if(rand.nextInt(2)==0) {
                b = new Box(1000f, 1500f, 100f, 200f, -20f - rand.nextFloat() * 10);
                b.setImage(getResources(), R.drawable.box);
            }else{
                b = new FlyingBox(1000f, 1000f,100f,200f,-20f - rand.nextFloat()*10);
                b.setImage(getResources(), R.drawable.crow);
            }
            obstacles.add(b);
        }
        for(Iterator<MovingObject> iter = obstacles.iterator(); iter.hasNext();){
            MovingObject obj = iter.next();
            obj.update();
            if(player.collides(obj))
                System.out.print("asd");
                //endGame();
            if(obj.getPos().x<0) obj.reset();
                //iter.remove();
        }

        if(b1.x+b1.background.getWidth() <=0) b1.x = 1080;
        if(b2.x+b2.background.getWidth() <=0) b2.x = 1080;
    }
    public void draw(){
        if(getHolder().getSurface().isValid()){
            Canvas canvas = getHolder().lockCanvas();
            canvas.drawBitmap(b1.background, b1.x, b1.y, paint);
            canvas.drawBitmap(b2.background, b2.x, b2.y, paint);
            showScore(canvas);
            drawPlayer(canvas);
            drawBox(canvas);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void sleep(){
        try {
            thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void pause() {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void resume(){
        thread = new Thread(this);
        thread.start();
    }


}
