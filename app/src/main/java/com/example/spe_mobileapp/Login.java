package com.example.spe_mobileapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
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
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        SharedPreferences.Editor mEdit = mPreferences.edit();
        System.out.println("test11" + mPreferences);
        login_roll = (EditText) findViewById(R.id.login_roll);
        login_pass = (EditText) findViewById(R.id.login_pass);
        login_btn = (Button) findViewById(R.id.data_login);
        //sp = getSharedPreferences("login",0);
        //sp.Editor editor = sp.edit();
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println(login_roll.getText().toString());

                //Network Test
                new NetworkTask(getBaseContext(), login_roll.getText().toString(), login_pass.getText().toString()).execute();


                // Ends

//                if ((login_roll.getText().toString()).equals("12345")) {
//                    if ((login_pass.getText().toString()).equals("aks253854")) {
//                        //sp.edit().putString(login_flag,"hi").apply();
//                        //mPreferences.edit().putBoolean("login_flag",true);
//                        mEdit.putBoolean("login_flag", true);
//                        mEdit.apply();
//                        Intent home = new Intent(Login.this, Home.class);
//                        home.putExtra("login_roll", login_roll.getText().toString());
//                        home.putExtra("room_no", "123");
//                        startActivity(home);
//                    } else {
////                            builder.setMessage("Wrong Password");
////                            builder.setTitle("Alert!");
////                            builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
////                                @Override
////                                public void onClick(DialogInterface dialog, int which) {
////                                    dialog.cancel();
////                                }
////                            });
//                        Toast.makeText(getApplicationContext(), "Wrong Password", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(getApplicationContext(), "Wrong RollNo", Toast.LENGTH_SHORT).show();
//                }
//                AsyncTask.execute(new Runnable() {
//                    @Override
//                    public void run() {
//                        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
//                            @Override
//                            public void onResponse(String s) {
////                        if(s.equals("true")){
////                            Toast.makeText(Login.this, "Registration Successful", Toast.LENGTH_LONG).show();
////                        }
////                        else{
////                            Toast.makeText(Login.this, "Can't Register", Toast.LENGTH_LONG).show();
////                        }
//                                System.out.println("check0");
//                                System.out.println(s);
//                            }
//                        },new Response.ErrorListener(){
//                            @Override
//                            public void onErrorResponse(VolleyError volleyError) {
//                                System.out.println("check1");
//                                Toast.makeText(Login.this, "Some error occurred -> "+volleyError, Toast.LENGTH_LONG).show();;
//                            }
//                        }) {
//                            @Override
//                            protected Map<String, String> getParams() throws AuthFailureError {
//                                System.out.println("check2");
//                                Map<String, String> parameters = new HashMap<String, String>();
//                                parameters.put("Email", login_roll.getText().toString());
//                                parameters.put("Password", login_pass.getText().toString());
//                                return parameters;
//                            }
//                        };
//                        System.out.println("check3");
//                        RequestQueue rQueue = Volley.newRequestQueue(Login.this);
//                        rQueue.add(request);
//                    }
//                });
//
////                System.out.println("check3");
////                RequestQueue rQueue = Volley.newRequestQueue(Login.this);
////                rQueue.add(request);
//
            }
        });

    }
}
