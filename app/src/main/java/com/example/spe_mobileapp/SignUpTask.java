package com.example.spe_mobileapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SignUpTask extends AsyncTask<Void, Void, Void> {

    private Context SContext;
    private String S_email, S_rollno, S_roomno, S_password;
    private Response mResponse;

    SignUpTask(Context SContext, String S_email, String S_rollno, String S_roomno, String S_password) {
        this.SContext = SContext;
        this.S_email = S_email;
        this.S_rollno = S_rollno;
        this.S_roomno = S_roomno;
        this.S_password = S_password;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String mPostBody = "{\"email\":\"" + S_email + "\",\"rollno\": \"" + S_rollno + "\",\"room_no\": \"" + S_roomno + "\",\"password\": \"" + S_password + "\"}";

            OkHttpClient mClient = new OkHttpClient();
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, mPostBody);
            okhttp3.Request mRequest = new okhttp3.Request.Builder()
                    .post(body)
                    .url("http://172.16.132.137:4000/signup")
                    .build();

            mResponse = mClient.newCall(mRequest).execute();
            Log.d("SignUpTask", mPostBody);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SignUpTask", "Error:: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            String mResponseBody = mResponse.body().string();
            Log.d("SignUpTask", mResponseBody);
            if (mResponse != null && mResponseBody.equals("1")) {
//                SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
//                SharedPreferences.Editor mEdit = mPreferences.edit();
//                mEdit.putBoolean("USER_LOGGED_IN", true);
//                mEdit.apply();

//                Intent homeActivity = new Intent(SContext, Home.class);
//                homeActivity.putExtra("login_roll", S_rollno);
//                homeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                SContext.startActivity(homeActivity);
                System.out.println("Email or rollno repeated");
            } else if (mResponse != null && mResponseBody.equals("2")) {
                Toast.makeText(SContext, "Good", Toast.LENGTH_SHORT).show();
            } else
                System.out.println("some error please try again or later");
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("SignUpTask", "Error:: " + e.getMessage());
        }
    }
}