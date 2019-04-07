package com.example.spe_mobileapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

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
            //Toast.makeText(mContext, "Internet connection error", Toast.LENGTH_SHORT).show();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            System.out.println("checkkssssss");
            String mResponseBody = mResponse.body().string();
            System.out.println("Response"+mResponseBody);
            JSONObject jsonObject=new JSONObject(mResponseBody);
            String msg_valid=jsonObject.getString("msg");
            String room_no=jsonObject.getString("room_no");
            Log.d("NetworkTask", mResponseBody);
            if (mResponse != null && msg_valid.equals("Valid")) {

                //Shared preference
                SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                SharedPreferences.Editor mEdit = mPreferences.edit();
                mEdit.putBoolean("login_flag", true);
                mEdit.putString("login_roll",mRollNumber);
                mEdit.putString("room_no",room_no);
                mEdit.apply();
                //ends

                Intent homeActivity = new Intent(mContext, Home.class);
                //homeActivity.putExtra("login_roll", mRollNumber);
                //homeActivity.putExtra("room_no",room_no);
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
