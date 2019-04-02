package com.example.spe_mobileapp;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Calendar;
import java.util.Date;
public class Home extends Activity {

    TextView time;
    EditText rollno;
    Date currenttime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
        time = (TextView)findViewById(R.id.time);
        rollno = (EditText)findViewById(R.id.Student_id);
        currenttime = Calendar.getInstance().getTime();
        System.out.println(currenttime);
        time.setText("Hello");
        Intent home = getIntent();
        String roll_no = home.getStringExtra("rollno");
        String login_roll = home.getStringExtra("login_roll");
        System.out.println(login_roll);
        rollno.setText(roll_no);
        //System.out.println(rollno.getText().toString());
    }
}
//aks253854