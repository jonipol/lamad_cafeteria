package com.lamad.studentcafeterias;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private RequestFromServer requestFromServer = null;
    private final Handler handler = new Handler();
    static List<Restaurant> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            requestFromServer = new RequestFromServer();
            requestFromServer.getCafeterias();
            requestFromServer.getMenus();
        }
        catch (JSONException e){
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e){
            e.printStackTrace();
        }
        //handler.post(startFetchingInBackground);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private Runnable startFetchingInBackground = new Runnable() {
        @Override
        public void run() {

        }
    };

    public static void parseCafeterias(JSONArray jsonArray) {
        try {
            dataList.addAll(JSONParser.readRestaurantJson(jsonArray));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static List<Restaurant> getDatalist(){
        return dataList;
    }
}
