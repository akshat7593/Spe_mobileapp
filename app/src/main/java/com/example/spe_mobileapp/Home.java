package com.example.spe_mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RatingBar;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Home extends Activity {

    //TextView time;
    EditText rollno, roomno, workerid;
    Date currenttime;
    RatingBar rating;
    Button submit;
    Button logout;
    //RadioGroup flag;
    RadioButton sweep, mop, selected;
    //SharedPreferences sp;
    //public static final String login_flag = "";

    //flag for radio and rating
    int sweep_mop;
    float rates;
    //ends
    String roll_pattern = "^(MS|MT|IMT|PHD|DT)+20+[0-9]{5}$";
    String room_pattern = "^[1-7][0-9][0-9]$";
    String work_pattern = "^[1-9][0-9]{4}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        //Shared Preference Starts
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor mEdit = mPreferences.edit();
        //ends

        //Initializing
        rollno = (EditText) findViewById(R.id.Student_id);
        roomno = (EditText) findViewById(R.id.Student_room);
        workerid = findViewById(R.id.workerid);
        //flag = findViewById(R.id.radio_choice);
        rating = (RatingBar) findViewById(R.id.rating_bar);
        submit = (Button) findViewById(R.id.data_submit);
        sweep = findViewById(R.id.radio_sweep);
        mop = findViewById(R.id.radio_mop);
        logout = (Button) findViewById(R.id.logout);
        //ends

        //time
        currenttime = Calendar.getInstance().getTime();
        System.out.println(currenttime);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        System.out.println(date);
        //ends

        //Initializing front end
        Intent home = getIntent();
        //String roll_no = home.getStringExtra("login_roll");
        //String room_no = home.getStringExtra("room_no");
        String roll_no = mPreferences.getString("login_roll", null);
        String room_no = mPreferences.getString("room_no", null);
        //System.out.println(room_no);
        rollno.setText(roll_no);
        roomno.setText(room_no);
        //ends

        //Submit button clicks
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Storing in string
                String work_id, fin_roll, fin_room;
                work_id = workerid.getText().toString();
                fin_roll = rollno.getText().toString();
                fin_room = roomno.getText().toString();
                //ends

                //validation
                boolean worker_id_test, roll_test, room_test;
                worker_id_test = match_work_id(work_id);
                roll_test = match_roll(fin_roll);
                room_test = match_room(fin_room);

                //set value of flags
                if (sweep.isChecked())
                    sweep_mop = 0;
                else
                    sweep_mop = 1;
                rates = rating.getRating();


                //ends

                if (worker_id_test == true && roll_test == true && room_test == true) {
                    System.out.println("all valid");
                    new DataEntryTask(getBaseContext(), work_id, fin_roll, fin_room, sweep_mop, rates, date, currenttime).execute();
                } else {
                    if (worker_id_test == false)
                        workerid.setError("Invalid worker id");
                    if (roll_test == false)
                        rollno.setError("Invalid rollno");
                    if (room_test == false)
                        roomno.setError("Invalid roomno");

                }


                //ends
//                System.out.println("entry submitted");
//                System.out.println(rating.getRating());
//                //test radio values
//                if(sweep.isChecked())
//                    System.out.println("radio_button"+sweep);
//                System.out.println("radio_group"+flag);
//                if(flag.getCheckedRadioButtonId()==-1)
//                    System.out.println("noone");
//                else{
//                    int id=flag.getCheckedRadioButtonId();
//                    selected=findViewById(id);
//                    System.out.println("which"+selected.getText().toString());
//
//                }
                //System.out.println("radio_button"+sweep);
                //ends
            }
        });
        //ends
        //Logout button clicks
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("check");
                //sp.edit().clear().apply();
                //sp.edit().apply();
                mEdit.clear().apply();
                startActivity(new Intent(Home.this, WelcomeActivity.class));
            }
        });
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

    public Boolean match_work_id(String work_id) {
        if (work_id.matches(work_pattern))
            return true;
        else
            return false;
    }
}
//aks253854