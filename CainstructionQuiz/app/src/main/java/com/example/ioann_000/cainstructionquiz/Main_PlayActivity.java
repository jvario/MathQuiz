package com.example.ioann_000.cainstructionquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.lang.Math;
import java.security.SecureRandom;
import java.text.DecimalFormat;


public class Main_PlayActivity extends AppCompatActivity {
    private static int counter=1;
    private static final String TAG = "TEST";
    private static final int MATH_PROCS = 10;
    private String correctAnswer;
    private int correctAnswers;
    private SecureRandom random;
    private Animation shakeAnimation;
    private TextView questionNumberTextView;
    private TextView num1TxtView;
    private TextView num2TxtView;
    private TextView opTxtView,qtxt;
    private EditText Write_EditText;
    private Handler handler;
    private Button check_btn;
    private boolean preferencesChanged = true;
    private boolean phoneDevice = true;
    private static int tries=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__play);
        loadActivity();
    }


        private  void loadActivity() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        random = new SecureRandom();
        handler = new Handler();

        shakeAnimation = AnimationUtils.loadAnimation(this,
               R.anim.incorrect_shake);
        shakeAnimation.setRepeatCount(3);
        Log.i(TAG, "onCreate: ");

        questionNumberTextView = (TextView) findViewById(R.id.questionNumberTextView);
        qtxt = (TextView) findViewById(R.id.qtxt);
        num1TxtView = (TextView) findViewById(R.id.num1TxtView);
        num2TxtView = (TextView) findViewById(R.id.num2TxtView);
        opTxtView = (TextView) findViewById(R.id.opTxtView);
        Write_EditText = (EditText) findViewById(R.id.Write_EditText);
        check_btn = (Button) findViewById(R.id.check_btn);


        int[] numbers = getResources().getIntArray(R.array.mathematicnum);
        int size = 10;
        Log.i(TAG, "onCreate: ");
        int randomIndex = random.nextInt(size);
        final int num1 = numbers[randomIndex];
        randomIndex = random.nextInt(size);
        final int num2 = numbers[randomIndex];
        if (num2!=0){        //case of : num1/0

        num1TxtView.setText(String.valueOf(num1));
        num2TxtView.setText(String.valueOf(num2));
        Intent i = getIntent();
        final String q_number = i.getStringExtra("number");
        final String operator = i.getStringExtra("key");
        opTxtView.setText(operator);
        questionNumberTextView.setText("Ερώτηση 1 από 10");
        if (counter>1) {

            questionNumberTextView.setText(q_number);
        }



            if(Write_EditText.getText().toString().trim().length() == 0) { Toast toast = Toast.makeText(getApplicationContext(), "Empty Space!!!", Toast.LENGTH_SHORT);
                Log.i(TAG, "loadActivity: ");}

       check_btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {


             final Double  answer = Double.parseDouble(Write_EditText.getText().toString());



                Log.i(TAG, "onClick: ");


                       switch (operator) {


                           case "+":

                               if (answer == addition(num1, num2)) {
                                   Toast toast = Toast.makeText(getApplicationContext(), "Μπράβο!!!", Toast.LENGTH_SHORT);
                                   toast.show();
                                   handler.postDelayed(
                                           new Runnable() {
                                               @Override
                                               public void run() {
                                                   loadnext();

                                               }
                                           }, 1000);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               }

                               else if (Write_EditText.getText().toString().length()==0)
                               {
                                   Write_EditText.setError("Req");
                                   break;
                               }



                               else {
                                   check_btn.startAnimation(shakeAnimation);
                                   Toast toast = Toast.makeText(getApplicationContext(), "Προσπάθησε ξανά", Toast.LENGTH_SHORT);
                                   toast.show();
                                   tries++;
                                   Intent i = new Intent(getApplicationContext(), Main_PlayActivity.class);
                                   i.putExtra("tries", tries);
                                   Log.i(TAG, "onClick: ");
                                   break;


                           }
                           case "-":


                               if (answer == sub(num1, num2)) {
                                   Toast toast = Toast.makeText(getApplicationContext(), "Μπράβο!!!", Toast.LENGTH_SHORT);
                                   toast.show();
                                   handler.postDelayed(
                                           new Runnable() {
                                               @Override
                                               public void run() {

                                                   loadnext();
                                               }
                                           }, 1000);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               } else {
                                   check_btn.startAnimation(shakeAnimation);
                                   Toast toast = Toast.makeText(getApplicationContext(), "Προσπάθησε ξανά ,βάλε την απόλυτη τιμή του αποτελέσματος...", Toast.LENGTH_SHORT);
                                   toast.show();
                                   tries++;
                                   Intent i = new Intent(getApplicationContext(), Main_PlayActivity.class);
                                   i.putExtra("tries", tries);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               }

                           case "/":

                               if (answer == div(num1, num2)) {
                                   Toast toast = Toast.makeText(getApplicationContext(), "Μπράβο!!!", Toast.LENGTH_SHORT);
                                   toast.show();
                                   handler.postDelayed(
                                           new Runnable() {
                                               @Override
                                               public void run() {
                                                   loadnext();
                                               }
                                           }, 1000);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               } else {
                                   check_btn.startAnimation(shakeAnimation);
                                   Toast toast = Toast.makeText(getApplicationContext(), "Προσπάθησε ξανά", Toast.LENGTH_SHORT);
                                   toast.show();
                                   tries++;
                                   Intent i = new Intent(getApplicationContext(), Main_PlayActivity.class);
                                   i.putExtra("tries", tries);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               }

                           case "X":
                               if (answer == multi(num1, num2)) {
                                   Toast toast = Toast.makeText(getApplicationContext(), "Μπράβο!!!", Toast.LENGTH_SHORT);
                                   toast.show();
                                   handler.postDelayed(
                                           new Runnable() {
                                               @Override
                                               public void run() {
                                                   loadnext();
                                               }
                                           }, 1000);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               } else {
                                   check_btn.startAnimation(shakeAnimation);
                                   Toast toast = Toast.makeText(getApplicationContext(), "Προσπάθησε ξανά", Toast.LENGTH_SHORT);
                                   toast.show();
                                   tries++;
                                   Intent i = new Intent(getApplicationContext(), Main_PlayActivity.class);
                                   i.putExtra("tries", tries);
                                   Log.i(TAG, "onClick: ");
                                   break;
                               }


                       }




            }
        });
        int screenSize = getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        if (screenSize == Configuration.SCREENLAYOUT_SIZE_LARGE ||
                screenSize == Configuration.SCREENLAYOUT_SIZE_XLARGE )
            phoneDevice = false;

        if (phoneDevice)
            setRequestedOrientation(
                    ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

}
    else {
            Toast toast = Toast.makeText(getApplicationContext(), "Γίνετε Επανεκκίνηση του quiz...", Toast.LENGTH_SHORT);
            toast.show();
            loadActivity();
        }}


    public double addition(int num1, int  num2) {

        double number1 = num1;
        double number2 =num2;
        double sum = number1 + number2;
        return sum;



    }


    public double sub(int  num1, int  num2) {


        double number1 = num1;
        double number2 =num2;
        double sub = Math.abs(number1 - number2);
        return sub;
    }

    public double multi(int  num1, int  num2) {

        double number1 = num1;
        double number2 =num2;
        double multi = number1 * number2;
        return multi;
    }

    public double div(int  num1,int  num2) {

        double number1 = num1;
        double number2 =num2;
        double div = Math.round(number1 / number2);
        return div;

    }

    private void loadnext() {
        if (counter < 10) {
            counter++;
            String a="Ερώτηση "+counter+" από 10";
            Intent i = new Intent(getApplicationContext(), Main_PlayActivity.class);
            String operator = opTxtView.getText().toString();
            i.putExtra("key", operator);
            i.putExtra("number",a);
            startActivity(i);
        } else {
            Intent i = new Intent(getApplicationContext(), Main_PlayActivity.class);
            DecimalFormat df= new DecimalFormat("##.#");
            DecimalFormat df2= new DecimalFormat("###");
            float tries2  = i.getIntExtra("tries",tries);
            int qs=10;
            int perc=100;
            float percentage = (qs/(tries2+qs))*perc;
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Ανεπιτυχείς προσπάθειες: "+df2.format(tries2)+ ", Ποσοστό Επιτυχίας: " +df.format(percentage)+"%");
            alertDialogBuilder.setPositiveButton("Αρχική σελίδα",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Intent a = new Intent(getApplicationContext(), ChoiceActivity.class);
                            startActivity(a);

                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();

        }
    }






    // Settings Menu

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
                open();
                return true;


            default:
                return super.onOptionsItemSelected(item);


        }
    }

}




