package com.example.spe_mobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class WelcomeActivity extends AppCompatActivity {

    public static final String login_flag = "";
    TextView login, signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        System.out.println("test"+mPreferences);
        login = findViewById(R.id.login_link);
        signup = findViewById(R.id.signup_link);
        System.out.println("check"+mPreferences.getAll());
        if (mPreferences != null && mPreferences.getBoolean("login_flag", false)) {
            startActivity(new Intent(WelcomeActivity.this, Home.class));
        }

        login.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, Login.class)));
        signup.setOnClickListener(v -> startActivity(new Intent(WelcomeActivity.this, MainActivity.class)));

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
//            }
//        });

    }


}