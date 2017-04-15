 package com.vogella.android.flashbeepshake;

import android.app.Activity;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

 public class MainActivity extends Activity {
     private static final String TAG = MainActivity.class.getSimpleName();
     private static ToneGenerator toneGenerator;

     @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
    //Handling the sound
    @OnClick (R.id.imageButton_beep)
     public void clickToAcceptBeep(View view){
        Log.d(TAG, "image button beep pressed");
        int streamType = AudioManager.STREAM_ALARM;
        int volume = 100;
        toneGenerator = new ToneGenerator(streamType, volume);
        toneGenerator.startTone(ToneGenerator.TONE_CDMA_HIGH_PBX_L);
    }
    @OnClick (R.id.imageButton_beep_stop)
     public void clickToStopBeep(View view){
        toneGenerator.stopTone();
    }

    //Handling the vibration
     @OnClick(R.id.imageButton_shake)
     public void clickToStartVibrate(View view){
         
     }

}
