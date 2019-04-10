package com.example.spe_mobileapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {
    EditText email, rollno, password, repassword, roomno;
    Button register;
    String emailPattern = "[a-zA-Z0-9._-]+@iiitb.org";
    String password_pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
    String roll_pattern = "^(MS|MT|IMT|PHD|DT)+20+[0-9]{5}$";
    String room_pattern = "^[1-7][0-9][0-9]$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        email = (EditText) findViewById(R.id.emailBox);
        rollno = (EditText) findViewById(R.id.rollno);
        password = (EditText) findViewById(R.id.pass);
        repassword = (EditText) findViewById(R.id.repass);
        register = (Button) findViewById(R.id.register);
        roomno = (EditText) findViewById(R.id.room_number);

//        email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView view, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    boolean email_test = match_email(email.getText().toString().trim());
//                    System.out.println(email_test);
//
//                }
//                return false;
//            }});
        repassword.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                register.performClick();
                return true;
            }
            return false;
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean email_test, pass_test, repass_test, roll_test, room_test;
                email_test = match_email(email.getText().toString().trim());
                pass_test = match_pass(password.getText().toString());
                repass_test = match_repass(password.getText().toString(), repassword.getText().toString());
                roll_test = match_roll(rollno.getText().toString());
                room_test = match_room(roomno.getText().toString());
                if(email_test==true&&roll_test==true&&room_test==true&&pass_test==true&&repass_test==true){
                        System.out.println("All Valid");
                        new SignUpTask(getBaseContext(), email.getText().toString().trim(), rollno.getText().toString(), roomno.getText().toString(), password.getText().toString()).execute();

                }
                else{
                    if(email_test==false)
                        email.setError("Incorrect Email");
                    if(roll_test==false)
                        rollno.setError("Incorrect RollNumber");
                    if(room_test==false)
                        roomno.setError("Incorrect Room Number");
                    if(pass_test==false)
                        password.setError("Not Valid Password");
                    if(repass_test==false)
                        repassword.setError("Password Doesn't Matches");

                }
//                if (email_test == true) {
//                    if (roll_test == true) {
//                        if (room_test == true) {
//                            if (pass_test == true) {
//                                if (repass_test == true) {
//                                    System.out.println("All Valid");
//                                    new SignUpTask(getBaseContext(), email.getText().toString().trim(), rollno.getText().toString(), roomno.getText().toString(), password.getText().toString()).execute();
//
//                                } else
//                                    repassword.setError("Password Doesn't Matches");
//                            } else
//                                password.setError("Not Valid Password");
//                        } else
//                            roomno.setError("Incorrect Room Number");
//                    } else
//                        rollno.setError("Incorrect RollNumber");
//                } else
//                    email.setError("Incorrect Email");

            }
        });


    }

    public Boolean match_email(String email) {
        if (email.matches(emailPattern))
            return true;
        else
            return false;
    }

    public Boolean match_pass(String pass) {
        if (pass.matches(password_pattern))
            return true;
        else
            return false;
    }

    public Boolean match_repass(String repass, String pass) {
        if (pass.equals(repass))
            return true;
        else
            return false;
    }

    public Boolean match_roll(String roll_no) {
        if (roll_no.matches(roll_pattern))
            return true;
        else
            return false;

    }

    public Boolean match_room(String room_no) {
        if (room_no.matches(room_pattern))
            return true;
        else
            return false;
    }
}
