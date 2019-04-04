package com.example.spe_mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

public class Welcome extends AppCompatActivity {
    public static final String login_flag = "";
    TextView login,signup;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launch);

        sp = getSharedPreferences("login",0);

        login = (TextView) findViewById(R.id.login_link);
        signup = (TextView) findViewById(R.id.signup_link);

//        if(sp.getBoolean("logged",true)){
//            startActivity(new Intent(Welcome.this,Home.class));
//        }
        if(sp.contains(login_flag)){
            startActivity(new Intent(Welcome.this,Home.class));
        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,Login.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,MainActivity.class));
            }
        });

//        signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(Welcome.this,MainActivity.class));
//            }
//        });

    }


}