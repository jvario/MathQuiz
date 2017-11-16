package com.example.ioann_000.cainstructionquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;


public class ChoiceActivity extends AppCompatActivity {
    private static final String TAG = "TEST";
    private boolean phoneDevice = true;













    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Log.i(TAG, "onCreate: ");


        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE )
            phoneDevice = false;

        if (phoneDevice)
            setRequestedOrientation(
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);












        final Button add_button= (Button) findViewById(R.id.add_button);
        Log.i(TAG, "onCreate: ");
        add_button.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View V) {
                Intent i = new Intent(getApplicationContext(),Main_PlayActivity.class);
                String mystring= "+";
                i.putExtra("key",mystring);
                startActivity(i);
                Log.i(TAG, "onClick: ");
            }
        });
        final Button sub_button= (Button) findViewById(R.id.sub_button);
        Log.i(TAG, "onCreate: ");
        sub_button.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View V) {
                Intent i = new Intent(getApplicationContext(),Main_PlayActivity.class);
                String mystring= "-";
                i.putExtra("key",mystring);
                startActivity(i);
                Log.i(TAG, "onClick: ");
            }
        });
        final Button multi_button= (Button) findViewById(R.id.multi_button);
        Log.i(TAG, "onCreate: ");
        multi_button.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View V) {
                Intent i = new Intent(getApplicationContext(),Main_PlayActivity.class);
                String mystring= "X";
                i.putExtra("key",mystring);
                startActivity(i);
                Log.i(TAG, "onClick: ");
            }
        });
        final Button div_button= (Button) findViewById(R.id.div_button);
        Log.i(TAG, "onCreate: ");
       div_button.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View V) {
                Intent i = new Intent(getApplicationContext(),Main_PlayActivity.class);
                String mystring= "/";
                i.putExtra("key",mystring);
                startActivity(i);
                Log.i(TAG, "onClick: ");
            }
        });


    }
    @Override
    protected void onStart(){
        super.onStart();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Quiz Μαθηματικών");
        setSupportActionBar(toolbar);
        Intent i=getIntent();
        Log.i(TAG, "onStart: ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Display display = ((WindowManager)
                getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        Point screenSize = new Point();
        Log.i(TAG, "onCreateOptionsMenu: ");
        display.getRealSize(screenSize);
        if (screenSize.x < screenSize.y) // x είναι το πλάτος,  y είναι το ύψος
        {
            getMenuInflater().inflate(R.menu.main_menu, menu); // διογκώνει το μενού
            return true;
        } else
            return false;

    }



    private void open(){
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

        alertDialogBuilder.setNegativeButton(R.string.NoAlert,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void exitdialog(){
        open();
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_settings:
                return true;
            case R.id.about_menu:
                Log.i(TAG, "onOptionsItemSelected: ");
                Intent h=new Intent(this,AboutActivity.class);
                startActivity(h);
                return true;
            case R.id.exit:
                exitdialog();
                return true;




            default:
                return super.onOptionsItemSelected(item);


        }
    }

}


