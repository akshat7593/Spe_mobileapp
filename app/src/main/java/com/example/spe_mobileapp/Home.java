package com.example.spe_mobileapp;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
import android.content.SharedPreferences;
public class Home extends Activity {

    TextView time;
    EditText rollno;
    Date currenttime;
    RatingBar rating;
    Button submit;
    Button logout;
    SharedPreferences sp;
    public static final String login_flag = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        //time = (TextView)findViewById(R.id.time);
        rollno = (EditText)findViewById(R.id.Student_id);
        currenttime = Calendar.getInstance().getTime();
        System.out.println(currenttime);
        //time.setText("Hello");
        Intent home = getIntent();
        String roll_no = home.getStringExtra("rollno");
        String login_roll = home.getStringExtra("login_roll");
        System.out.println(login_roll);
        rollno.setText(roll_no);
        //System.out.println(rollno.getText().toString());

        rating = (RatingBar)findViewById(R.id.rating_bar);
        submit = (Button)findViewById(R.id.data_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(rating.getRating());
            }
        });
        System.out.println(rating.getRating());
        sp = getSharedPreferences("login",0);
        logout = (Button)findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("check");
                sp.edit().clear().apply();
                //sp.edit().apply();
                startActivity(new Intent(Home.this,Welcome.class));
            }
        });
    }
}
//aks253854