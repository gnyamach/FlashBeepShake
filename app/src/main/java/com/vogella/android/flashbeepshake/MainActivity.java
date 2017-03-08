package com.vogella.android.flashbeepshake;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    FragmentManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Beep beepFragment = new Beep();
        manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        Log.i(TAG, "Changing the container");
        transaction.add(R.id.main_container, beepFragment, "beepFrag");
        transaction.commit();
    }
}
