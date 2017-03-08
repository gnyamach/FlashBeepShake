package com.vogella.android.flashbeepshake;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Beep extends Fragment {
    private static final String TAG = Beep.class.getSimpleName();
    private static int firstOp, secOp;
    private static TextView tv_firstOp, tv_secOp;
    private static GenerateRandomInts generateRandomInts;
    private static ImageButton ib_result;
    private static EditText et_addResult;

   // private OnFragmentInteractionListener mListener;

    public Beep() {
        firstOp = 0;
        secOp = 0;
        generateRandomInts = new GenerateRandomInts();

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =   inflater.inflate(R.layout.fragment_beep, container, false);

        tv_firstOp = (TextView) view.findViewById(R.id.tv_firstOperand);
        tv_secOp = (TextView) view.findViewById(R.id.tv_secondOperand);
        ib_result =(ImageButton) view.findViewById(R.id.imageButton_Result);
        et_addResult =(EditText)view.findViewById(R.id.et_userInputAdd);

        Log.e(TAG, "Loading the frag");
        load();

        return view;
    }

    private void load() {
        Log.e(TAG, "starting to load");
        firstOp = generateRandomInts.getAdditionInt();
        Log.e(TAG, "1st Op " + firstOp);

        secOp = generateRandomInts.getAdditionInt();
        Log.e(TAG, "2nd Op " + secOp);
        tv_firstOp.setText(String.valueOf(firstOp));
        tv_secOp.setText(String.valueOf(secOp));
    }
    private void clickToAdd(View view){
        int userEntered = Integer.parseInt(et_addResult.getText().toString());
        if (userEntered == (firstOp + secOp)){
            Log.i(TAG, "Correct");
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
    } */
}
