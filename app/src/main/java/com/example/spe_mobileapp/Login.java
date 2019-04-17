package com.example.spe_mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends Activity {
    public static final String login_flag = "";
    EditText login_roll, login_pass;
    //SharedPreferences sp;
    Button login_btn;
    String url = "http://172.16.132.137:4000/login";

    //    AlertDialog.Builder builder
//            = new AlertDialog
//            .Builder(Login.this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        //SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //SharedPreferences.Editor mEdit = mPreferences.edit();
        //System.out.println("test11" + mPreferences);
        login_roll = (EditText) findViewById(R.id.login_roll);
        login_pass = (EditText) findViewById(R.id.login_pass);
        login_btn = (Button) findViewById(R.id.data_login);
        //sp = getSharedPreferences("login",0);
        //sp.Editor editor = sp.edit();

        //check






        //end
        login_btn.setOnClickListener(v -> {

            System.out.println(login_roll.getText().toString());

            //Network Test
            new NetworkTask(getBaseContext(), login_roll.getText().toString(), login_pass.getText().toString()).execute();
        });



    }
    String roll_pattern = "^(MS|MT|IMT|PHD|DT)+20+[0-9]{5}$";
    public Boolean match_roll(String roll_no) {
        if (roll_no.matches(roll_pattern))
            return true;
        else
            return false;

    }
}
