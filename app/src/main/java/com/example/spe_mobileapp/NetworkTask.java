package com.example.spe_mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.BufferedSink;

public class NetworkTask extends AsyncTask<Void, Void, Void> {

    private Context mContext;
    private String mPassword, mRollNumber;
    private Response mResponse;

    NetworkTask(Context mContext, String mRollNumber, String mPassword) {
        this.mContext = mContext;
        this.mPassword = mPassword;
        this.mRollNumber = mRollNumber;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String mPostBody = "{\"Password\":\"" + mPassword + "\", \"RollNo\": \"" + mRollNumber + "\"}";

            OkHttpClient mClient = new OkHttpClient();
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, mPostBody);
            okhttp3.Request mRequest = new okhttp3.Request.Builder()
                    .post(body)
                    .url("http://172.16.132.137:4000/login")
                    .build();

            mResponse = mClient.newCall(mRequest).execute();
            Log.d("NetworkTask", mPostBody);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NetworkTask", "Error:: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            String mResponseBody = mResponse.body().string();
            Log.d("NetworkTask", mResponseBody);
            if (mResponse != null && mResponseBody.equals("Valid")) {
//                SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
//                SharedPreferences.Editor mEdit = mPreferences.edit();
//                mEdit.putBoolean("USER_LOGGED_IN", true);
//                mEdit.apply();

                Intent homeActivity = new Intent(mContext, Home.class);
                homeActivity.putExtra("login_roll", mRollNumber);
                homeActivity.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(homeActivity);
            } else {
                Toast.makeText(mContext, "Error", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("NetworkTask", "Error:: " + e.getMessage());
        }
    }
}
