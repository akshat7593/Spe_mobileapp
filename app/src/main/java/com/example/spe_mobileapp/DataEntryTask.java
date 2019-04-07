package com.example.spe_mobileapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.Date;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DataEntryTask extends AsyncTask<Void, Void, Void> {
    private Context SContext;
    private String work_id, fin_roll, fin_room, date;
    private int sweep_mop;
    private float rate;
    private Response mResponse;
    private Date currenttime;

    //constructor
    DataEntryTask(Context SContext, String work_id, String fin_roll, String fin_room, int sweep_mop, float rate, String date, Date currenttime) {
        this.SContext = SContext;
        this.work_id = work_id;
        this.fin_roll = fin_roll;
        this.fin_room = fin_room;
        this.sweep_mop = sweep_mop;
        this.rate = rate;
        this.date = date;
        this.currenttime = currenttime;
    }
    //ends


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String mPostBody = "{\"work_id\":\"" + work_id + "\",\"roll_no\": \"" + fin_roll + "\"," +
                    "\"room_no\": \"" + fin_room + "\",\"sweep_mop\": \"" + sweep_mop + "\"," +
                    "\"rates\":\"" + rate + "\"," + "\"date\":\"" + date + "\"," + "\"currenttime\":\"" + currenttime + "\"}";
            System.out.println(mPostBody);
            OkHttpClient mClient = new OkHttpClient();
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, mPostBody);
            okhttp3.Request mRequest = new okhttp3.Request.Builder()
                    .post(body)
                    .url("http://172.16.132.137:4000/cleaning")
                    .build();

            mResponse = mClient.newCall(mRequest).execute();
            Log.d("DataEntryTask", mPostBody);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DataEntryTask", "Error:: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            String mResponseBody = mResponse.body().string();
            System.out.println(mResponseBody);
            Log.d("DataEntryTask", mResponseBody);
            if (mResponse != null && mResponseBody.equals("-1")) {
                Toast.makeText(SContext, "Roll_No doesn't exists", Toast.LENGTH_SHORT).show();
            } else if (mResponse != null && mResponseBody.equals("2")) {
                Toast.makeText(SContext, "Worker_id Wrong", Toast.LENGTH_SHORT).show();
            } else if (mResponse != null && mResponseBody.equals("3")) {
                Toast.makeText(SContext, "Data with same entry", Toast.LENGTH_SHORT).show();
            } else if (mResponse != null && mResponseBody.equals("5")) {
                Toast.makeText(SContext, "Succesfully Inserted", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(SContext, "Try again later", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DataEntryTask", "Error:: " + e.getMessage());
        }
    }
}
