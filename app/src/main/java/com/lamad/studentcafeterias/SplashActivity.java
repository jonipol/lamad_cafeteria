package com.lamad.studentcafeterias;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;

public class SplashActivity extends AppCompatActivity implements SplashScreenTask.SplashScreenTaskCallback {

    private RequestFromServer requestFromServer = null;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handler.postDelayed(startFetchingInBackground, 1000);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private Runnable startFetchingInBackground = new Runnable() {
        @Override
        public void run() {
            try{
                requestFromServer = new RequestFromServer();
                requestFromServer.getCafeterias();
                requestFromServer.getMenus();
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    };

    @Override
    public void OnSplashScreenTaskCompleted() {

    }
}