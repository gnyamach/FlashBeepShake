 package com.vogella.android.flashbeepshake;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static FragmentManager manager;
    private static float xStart, xEnd, changeX;
    private float minDistance = 300;
    private  Beep beepFrag;
    private  Flash flashFrag;
    private  Shake shakeFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        beepFrag = new Beep();
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Log.i(TAG, "Changing the container");
        transaction.add(R.id.main_container, beepFrag, "beepFrag");
        transaction.commit();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                xStart = event.getX();
                break;
            case MotionEvent.ACTION_UP:
                xEnd = event.getX();
                changeX = xEnd - xStart;

                if(((Math.abs(changeX))> minDistance) &&(xEnd > xStart)) {
                    swipedRight();
                }else if (((Math.abs(changeX))> minDistance) &&(xEnd <= xStart)){
                    swipedLeft();
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void swipedRight() {
        Log.i(TAG, "Swiping Right");
        beepFrag = (Beep)manager.findFragmentByTag("beepFrag");
        flashFrag = (Flash)manager.findFragmentByTag("flashFrag");
        shakeFrag = (Shake)manager.findFragmentByTag("shakeFrag");
        FragmentTransaction transaction = manager.beginTransaction();

        if (beepFrag != null){
            Log.i(TAG, "Beep to Flash");
            flashFrag = new Flash();
            transaction.replace(R.id.main_container, flashFrag, "flashFrag");
        }else if (flashFrag != null){
            Log.i(TAG, "Flash to Shake");
            shakeFrag = new Shake();
            transaction.replace(R.id.main_container, shakeFrag, "shakeFrag");
        }else if (shakeFrag != null){
            Log.i(TAG, "Shake to Beep");
            beepFrag = new Beep();
            transaction.replace(R.id.main_container, beepFrag, "beepFrag");
        }
        transaction.commit();
    }

    private void swipedLeft() {
        Log.i(TAG, "Swiping Left");
        beepFrag = (Beep)manager.findFragmentByTag("beepFrag");
        flashFrag = (Flash)manager.findFragmentByTag("flashFrag");
        shakeFrag = (Shake)manager.findFragmentByTag("shakeFrag");

        FragmentTransaction transaction = manager.beginTransaction();
        if (beepFrag != null){
            Log.i(TAG, "Beep to Shake");
            shakeFrag = new Shake();
            transaction.replace(R.id.main_container, shakeFrag, "shakeFrag");
        }else if (flashFrag != null){
            Log.i(TAG, "Flash to Beep");
            beepFrag = new Beep();
            transaction.replace(R.id.main_container, beepFrag, "beepFrag");
        }else if (shakeFrag != null){
            Log.i(TAG, "Shake to Flash");
            flashFrag = new Flash();
            transaction.replace(R.id.main_container, flashFrag, "flashFrag");
        }
        transaction.commit();
    }
}
