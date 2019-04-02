package com.example.spe_mobileapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class Login extends Activity {
    EditText login_roll, login_pass;
    Button  login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        login_roll = (EditText) findViewById(R.id.login_roll);
        login_pass = (EditText) findViewById(R.id.login_pass);
        login_btn = (Button)findViewById(R.id.data_login);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent home = new Intent(Login.this, Home.class);
                home.putExtra("login_roll",login_roll.getText().toString());
                startActivity(home);
            }
        });

    }
}
