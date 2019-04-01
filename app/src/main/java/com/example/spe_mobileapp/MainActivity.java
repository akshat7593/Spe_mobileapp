package com.example.spe_mobileapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    EditText email,rollno,password,repassword;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = (EditText)findViewById(R.id.emailBox);
        rollno = (EditText)findViewById(R.id.rollno);
        password = (EditText)findViewById(R.id.pass);
        repassword = (EditText)findViewById(R.id.repass);
        register = (Button)findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean email_test,pass_test,repass_test;
                email_test = match_email(email.getText().toString().trim());
                pass_test = match_pass(password.getText().toString());
                repass_test = match_repass(password.getText().toString(),repassword.getText().toString());
                if(email_test == true){
                    if(pass_test == true){
                        if(repass_test == true) {
                            Intent home = new Intent(MainActivity.this,Home.class);
                            home.putExtra("rollno",rollno.getText().toString());
                            startActivity(home);
                        }
                        else
                            System.out.println("password_mismatch");
                    }
                    else
                        System.out.println("Invalid_password");
                }
                else
                    System.out.println("Invalid_email");
            }
        });


    }
    String emailPattern = "[a-zA-Z0-9._-]+@iiitb.org";
    String password_pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    //String roll_pattern = "^";

    public Boolean match_email(String email){
            if(email.matches(emailPattern))
                return true;
            else
                return false;
    }
    public Boolean match_pass(String pass){
        if(pass.matches(password_pattern))
            return true;
        else
            return false;
    }
    public Boolean match_repass(String repass,String pass){
        if(pass.equals(repass))
            return true;
        else
            return false;
    }
}
