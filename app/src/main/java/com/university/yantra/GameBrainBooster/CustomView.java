package com.university.yantra.GameBrainBooster;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.university.yantra.Activities.AnalysisActivity;
import com.university.yantra.R;

/**
 * Created by rahul on 13/3/18.
 */

public class CustomView extends View {

    Paint p;
    public int life;
    public int level;
    Typeface t;
    Context context;

    Bitmap hint;

    private int hintflag=0;

    Intent i, i1;

    static boolean analysis;

    String[] hints;

    //////////////////////////// SCORE ///////////////////////
    public static int game8_CorrectlyAnswered=0;
    //////////////////////////////////////////////////////////

    public CustomView(Context context) {
        super(context);

        this.context=context;
        p = new Paint();

        life = 3;
        hints = new String[13];


        i = new Intent(getContext(), AnalysisActivity.class);
        i.putExtra("state","1");
        i1 = new Intent(getContext(), GameOver.class);

    }


    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        p = new Paint();

        life = 3;
        hints = new String[13];

        i = new Intent(getContext(), AnalysisActivity.class);
        i.putExtra("state","1");
        i1 = new Intent(getContext(), GameOver.class);



    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context=context;
        p = new Paint();

        life = 3;
        hints = new String[13];


        i = new Intent(getContext(), AnalysisActivity.class);

        i.putExtra("state","1");
        i1 = new Intent(getContext(), GameOver.class);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        hints[0]="Focus on \"you take away 4\"";
        hints[1]="Light other candles";
        hints[2]="Look your feet Closely!";
        hints[3]="Look at first assumption";
        hints[4]="It's not a science question";
        hints[5]="Use Gravity";
        hints[6]="Look at sign";
        hints[7]="Think about how you wear a shirt";
        hints[8]="It has nothing to do with the buttons";
        hints[9]="Use B.O.D.M.A.S. rule";
        hints[10]="Shake the tree";
        hints[11]="Your answer is in question";
        hints[12]="Imagine that the owl is on your right";



        t = Typeface.createFromAsset(context.getAssets(), "raleway.ttf");

        p.setTypeface(t);

        p.setColor(Color.BLACK);
        p.setAlpha(99);
        p.setTextAlign(Paint.Align.LEFT);
        canvas.drawRect(0, 0, getWidth(), getWidth() / 8, p);
        p.setColor(Color.WHITE);
        p.setTextSize(getWidth() / 20);
        canvas.drawText("Level: " + level, getWidth() / 2 - getWidth() / 10, getHeight() / 18, p);


        Bitmap a = BitmapFactory.decodeResource(getResources(), R.drawable.hiddenobject_hint);
        hint = Bitmap.createScaledBitmap(a, getWidth() / 10, getWidth() / 10, false);


        //Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.brain_skip);
        //skip = Bitmap.createScaledBitmap(b, getWidth() / 7, getWidth() / 7, false);

        canvas.drawBitmap(hint, getWidth() - hint.getWidth() - getWidth() / 60, getHeight() / 100, null);

        if (life <= 0) {
            if (analysis) {
                i.putExtra("flag","false");
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getContext().getApplicationContext().startActivity(i);

            }
            else {

                i1.putExtra("flag","true");
                i1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                getContext().getApplicationContext().startActivity(i);


            }
        }

        canvas.drawText("Life: " + life, getWidth() / 60, getHeight() / 18, p);


        if (hintflag == 1) {
            p.setColor(Color.BLACK);
            p.setAlpha(99);

            ///canvas.drawRect(0,getHeight()-getHeight()/12, getWidth(), getHeight(), p);
            canvas.drawRect(0,getWidth()/8, getWidth(), getWidth()/5, p);


            p.setColor(Color.WHITE);
            p.setTextAlign(Paint.Align.CENTER);
            p.setTextSize(getWidth()/20);
            canvas.drawText(hints[level-1], getWidth()/2,getWidth()/8+getHeight()/32, p );

        }

        /*else {
            canvas.drawBitmap(skip, getWidth()/2 - skip.getWidth()/2, getHeight()-skip.getHeight()-getHeight()/80, null);
        }*/
    }

    public boolean onTouchEvent(MotionEvent event) {


        int x = (int) event.getX(), y = (int) event.getY();


        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if ((x>getWidth()-hint.getWidth()-getWidth()/60)&& y<getHeight()/100+hint.getHeight()) {

                    if (hintflag == 0) {
                        hintflag=1;

                    }

                    else {
                        hintflag=0;
                    }
                    invalidate();
                }

                /*if (hintflag==0) {
                    if (x>getWidth()/2 - skip.getWidth()/2 && x<getWidth()/2 - skip.getWidth()/2+skip.getWidth() && y>getHeight()-skip.getHeight()-getHeight()/80) {
                        callNextActivity();
                    }
                }*/


        }



        return true;
    }


    /*public void callNextActivity() {

        if (level==1)
            i2 = new Intent(getContext(), Level2Activity.class);
        if (level==2)
            i2 = new Intent(getContext(), Level3Activity.class);
        if (level==3)
            i2 = new Intent(getContext(), Level4Activity.class);
        if (level==4)
            i2 = new Intent(getContext(), Level5Activity.class);
        if (level==5)
            i2 = new Intent(getContext(), Level6Activity.class);
        if (level==6)
            i2 = new Intent(getContext(), Level7Activity.class);
        if (level==7)
            i2 = new Intent(getContext(), Level8Activity.class);
        if (level==8){
            i2 = new Intent(getContext(), EndActivity.class);
        }



        i2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //i2.putExtra("flag","false");
        getContext().getApplicationContext().startActivity(i2);
    }*/

}
