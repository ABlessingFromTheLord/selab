package com.example.a22b11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.LocaleList;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Sportactivity_Selection extends AppCompatActivity {
    Button button;
    String selectedActivity;
    TextView textView;
    Integer selectedActivityNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sportselection);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textView29);
        textView.setText(getString(R.string.selected_nothing));


        if(savedInstanceState != null){
                selectedActivityNumber = savedInstanceState.getInt("selectedActivityNumber");
                selectedActivity = savedInstanceState.getString("selectedActivity");
                textView.setText(getString(R.string.selected)+": " + selectedActivity);
        }
    }


    public void getSelectedActivity(View view)
    {
        Button clickedButton = (Button) view;

        if (clickedButton.getText() == getString(R.string.running)) selectedActivityNumber = 0;
        else if(clickedButton.getText() == getString(R.string.walking)) selectedActivityNumber = 1;
        else if(clickedButton.getText() == getString(R.string.swimming)) selectedActivityNumber = 2;
        else if(clickedButton.getText() == getString(R.string.hiking)) selectedActivityNumber = 3;
        else if(clickedButton.getText() == getString(R.string.dieing)) selectedActivityNumber = 4;
        else if(clickedButton.getText() == getString(R.string.yoga)) selectedActivityNumber = 5;
        else if(clickedButton.getText() == getString(R.string.meditation)) selectedActivityNumber = 6;

        selectedActivity = (String) clickedButton.getText();
        textView.setText(getString(R.string.selected)+": " + selectedActivity);

    }

    public void buttonRecordActivity(View view) {
        Intent intent = new Intent(this,Sportactivity_Record.class);

        //TODO change toast message to local string
        Toast toast = Toast.makeText(this, getString(R.string.please_select_activity), Toast.LENGTH_SHORT);
        if (selectedActivity == null) toast.show();
        else {
            intent.putExtra("selectedActivity", selectedActivity);
            intent.putExtra("selectedActivityNumber", selectedActivityNumber);



            startActivity(intent);
        }
    }



    //to save states when the device is rotated, the activtiy isn't created anew with null in selectedActivityNumber
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState); //takes care of the default
        outState.putInt("selectedActivityNumber" ,selectedActivityNumber);
        outState.putString("selectedActivity" ,selectedActivity);

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }



    //TODO: create other activity type
    //TODO: change the buttons from hard coding to translatable String


}