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
    private static Beep beepFrag;
    private static Flash flashFrag;
    private static Shake shakeFrag;

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

    private void swipedLeft() {

        beepFrag = (Beep)manager.findFragmentByTag("beepFrag");
        flashFrag = (Flash)manager.findFragmentByTag("flashFrag");
        shakeFrag = (Shake)manager.findFragmentByTag("shakeFrag");

        FragmentTransaction transaction = manager.beginTransaction();
        if (beepFrag != null){
            flashFrag = new Flash();
            transaction.replace(R.id.main_container, flashFrag, "flashFrag");
        }else if (flashFrag != null){
            shakeFrag = new Shake();
            transaction.replace(R.id.main_container, shakeFrag, "shakeFrag");
        }else if (shakeFrag != null){
            beepFrag = new Beep();
            transaction.replace(R.id.main_container, beepFrag, "beepFrag");
        }
        transaction.commit();

    }

    private void swipedRight() {
        beepFrag = (Beep)manager.findFragmentByTag("beepFrag");
        flashFrag = (Flash)manager.findFragmentByTag("flashFrag");
        shakeFrag = (Shake)manager.findFragmentByTag("shakeFrag");

        FragmentTransaction transaction = manager.beginTransaction();
        if (beepFrag != null){
            flashFrag = new Flash();
            transaction.replace(R.id.main_container, flashFrag, "flashFrag");
        }else if (flashFrag != null){
            shakeFrag = new Shake();
            transaction.replace(R.id.main_container, shakeFrag, "shakeFrag");
        }else if (shakeFrag != null){
            beepFrag = new Beep();
            transaction.replace(R.id.main_container, beepFrag, "beepFrag");
        }
        transaction.commit();
    }
}
