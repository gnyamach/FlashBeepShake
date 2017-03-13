package com.vogella.android.flashbeepshake;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.CameraAccessException;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.Context.CAMERA_SERVICE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {Flash.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class Flash extends Fragment {
    private static final String TAG = Flash.class.getSimpleName();
    private Camera camera;
    private CameraManager cameraManager;
    private String cameraID;
    private static boolean isLightOn, hasFlash;
    private static ImageButton imagebtnSwitch;
    //private OnFragmentInteractionListener mListener;

    public Flash() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =   inflater.inflate(R.layout.fragment_flash, container, false);
        ButterKnife.bind(this, view);
        isLightOn = false;

        cameraManager = (CameraManager)getActivity().getSystemService(Context.CAMERA_SERVICE);
        imagebtnSwitch = (ImageButton)view.findViewById(R.id.btnSwitch);

        return view;
    }

    @OnClick (R.id.btnSwitch)
    public void lightSwitchClicked(View view){
        Log.e(TAG, "clicked the light switch icon");
        hasFlash = getActivity().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
        if(!hasFlash){
            AlertDialog alert = new AlertDialog.Builder(getActivity()).create();
            alert.setTitle("Error");
            alert.setMessage("Sorry, No Flash Light Support");
            alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener(){

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alert.show();
            return;
        }else {
            Log.d(TAG, "Has Flash Light. Getting the camera ID");
            try{
                cameraID = cameraManager.getCameraIdList()[0];
                Log.d(TAG, "Camera ID = " +cameraID);
                Log.d(TAG, "isLightOn: " + isLightOn);
            }catch (CameraAccessException e){
                e.printStackTrace();
            }

            try {
                if (isLightOn) {
                    Log.d(TAG, "Turning off Light");
                    turnOffLight();
                } else {
                    Log.d(TAG, "Turning on Light");
                    turnOnLight();
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    private void turnOnLight() {
        try {
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)&& (cameraID != null)) {
                Log.d(TAG, "Light is being Turned On");
                cameraManager.setTorchMode(cameraID, true);
                imagebtnSwitch.setImageResource(R.drawable.flashlight_off);
                isLightOn = true;
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }

    }

    private void turnOffLight() {
        try {
            if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && (cameraID != null)){
                cameraManager.setTorchMode(cameraID, false);
                imagebtnSwitch.setImageResource(R.drawable.flashlight_on);
                isLightOn = false;
            }
        }catch (CameraAccessException e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStop() {
        super.onStop();
        if(isLightOn){
            turnOffLight();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(isLightOn){
            turnOffLight();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(!isLightOn){
            turnOnLight();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }     */
}
