package com.example.a22b11;


import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import java.time.Instant;

public class Sportactivity_Record extends AppCompatActivity {

    Chronometer chronometer;
    private boolean running = false;
    Button button, finishButton;
    private long pauseOffset;
    int buttonState = 0;  // initial state  0 = ready to start / 1 = ready to stop / 2 = stopped ready to resume or finish
    TextView textView;

    long startedAt;
    long pausedAt;
    String selectedActivity;
    Integer selectedActivityNumber;
    Instant startTime;
    Instant endTime;
    Integer duration;
    enum activityType {RUNNING, WALKING, SWIMMING, HIKING, DIEING, YOGA, MEDITATION};



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportrecord);
        button = findViewById(R.id.Start_Button);
        finishButton = findViewById(R.id.button20);
        textView = findViewById(R.id.testView);
        chronometer = findViewById(R.id.Chronometer);





        if(getIntent().hasExtra("selectedActivity"))
        {
            textView.setText(getString(R.string.selected) +": " + getIntent().getStringExtra("selectedActivity"));
            selectedActivity = getIntent().getStringExtra("selectedActivity");
        }

        if(getIntent().hasExtra("selectedActivityNumber")) selectedActivityNumber = getIntent().getIntExtra("selectedActivityNumber", -1); //TODO -1 means error


        if (buttonState != 2) {
            finishButton.setVisibility(View.GONE);
        }
        else finishButton.setVisibility(View.VISIBLE);
    }






    public void toggleButton(View view)
    {
        if (buttonState == 0) {  // ready to start
            button.setText(R.string.Stop);  // change to stop
            finishButton.setVisibility(View.GONE);

            startTimer(view);

            buttonState = 1;
        }

        else if (buttonState == 1) { // ready to stop
            button.setText(R.string.resume); // change to resume

            finishButton.setVisibility(View.VISIBLE);
            stopTimer(view);

            buttonState = 2;
        }

        else if (buttonState == 2) { // is stopped and ready to resume or finish
            button.setText(R.string.Stop);  // change to stop
            finishButton.setVisibility(View.GONE);


            startTimer(view);

            buttonState = 1;
        }

    }

    public void startTimer(View view){


        startTime = Instant.now();

        if(!running){
            chronometer.start();
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);


            running = true;
        }

    }

    public void stopTimer(View view) {


        if (running) {

            pauseOffset = SystemClock.elapsedRealtime() - chronometer.getBase();
            chronometer.stop();
            pausedAt = SystemClock.elapsedRealtime();
            running = false;
        }

        endTime = Instant.now();

    }

    public void resetTimer(View view){
        if(running){
            chronometer.stop();
            running = false;
        }

        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.stop();
        pauseOffset = 0;
    }





    //
    public void buttonClickFinish(View view){

       duration = (int) Math.floor(((pausedAt - chronometer.getBase()) / 1000) ); //TODO getting the duration in minutes, remember to bring back /60

        Log.e("this is the error we are looking for ", String.valueOf(duration));

        Intent intent = new Intent(this, Sportactivity_Finish.class);

        intent.putExtra("selectedActivity", selectedActivity);
        intent.putExtra("selectedActivityNumber", selectedActivityNumber);
        intent.putExtra("startTime", String.valueOf(startTime));
        intent.putExtra("endTime", String.valueOf(endTime));
        intent.putExtra("duration", duration);

        startActivity(intent);
    }


}

