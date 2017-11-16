
package com.example.ioann_000.cainstructionquiz;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class PlayActivity extends Activity implements View.OnClickListener {


    private static final String TAG = "TEST";
    private boolean phoneDevice = true;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        Button exitBtn = (Button) findViewById(R.id.exit_btn);
        exitBtn.setOnClickListener(this);
        Button playBtn = (Button) findViewById(R.id.Start_btn);
        playBtn.setOnClickListener(this);


    int screenSize = getResources().getConfiguration().screenLayout &
            Configuration.SCREENLAYOUT_SIZE_MASK;

    if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
    screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE )
    phoneDevice = false;

    if (phoneDevice)
    setRequestedOrientation(
            ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); }



    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.Start_btn:
                Log.i(TAG, "onClick: ");
                Intent i = new Intent(getApplicationContext(), ChoiceActivity.class);
                startActivity(i);
                break;

            case R.id.exit_btn:
                open();
                break;


            default:
                break;
        }

    }

    private void open() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.Qalert);
        alertDialogBuilder.setPositiveButton(R.string.YesAlert,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                        System.exit(0);
                    }
                });

        alertDialogBuilder.setNegativeButton(R.string.NoAlert, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void exitdialog() {
        open();
    }
}













