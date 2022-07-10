package com.example.a22b11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.a22b11.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MyApplication myApplication = MyApplication.getInstance();

        if (!myApplication.isLoggedIn()) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
        else {
            setContentView(R.layout.activity_main);
        }
    }

    public void buttonClickQuestionnaire(View view) {
        Intent intent = new Intent(this, QuestionnaireWelcome.class);
        startActivity(intent);
    }

    public void buttonClickSPORTACTIVITYHOMEPLACEHOLDER(View view) {
        Intent intent = new Intent(this, Sportactivity_Home.class);
        startActivity(intent);
    }
}