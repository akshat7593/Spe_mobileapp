package com.example.spe_mobileapp;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
public class DataEntryTask extends AsyncTask<Void, Void, Void> {
    private Context SContext;
    private String work_id,fin_roll,fin_room;
    private int sweep_mop;
    private float rates;
    private Response mResponse;

    //constructor
    DataEntryTask(Context SContext,String work_id,String fin_roll,String fin_room,int sweep_mop,float rates){
        this.SContext = SContext;
        this.work_id = work_id;
        this.fin_roll = fin_roll;
        this.fin_room = fin_room;
        this.sweep_mop = sweep_mop;
        this.rates = rates;
    }
    //ends

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            String mPostBody = "{\"work_id\":\"" + work_id + "\",\"rollno\": \"" + fin_roll + "\",\"room_no\": \"" + fin_room + "\",\"sweep_mop\": \"" + sweep_mop + "\",\"rates\":\""+ rates +"\"}";

            OkHttpClient mClient = new OkHttpClient();
            MediaType JSON = MediaType.get("application/json; charset=utf-8");
            RequestBody body = RequestBody.create(JSON, mPostBody);
            okhttp3.Request mRequest = new okhttp3.Request.Builder()
                    .post(body)
                    .url("http://172.16.132.137:4000/signup")
                    .build();

            mResponse = mClient.newCall(mRequest).execute();
            Log.d("DataEntryTask", mPostBody);
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("DataEntryTask", "Error:: " + e.getMessage());
        }
        return null;
    }
}
