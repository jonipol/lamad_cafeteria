package com.lamad.studentcafeterias;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler.post(startFetchingInBackground);
        handler.postDelayed(startMainActivity, 1000);
    }

    private Runnable startFetchingInBackground = new Runnable() {
        @Override
        public void run() {
            try{
                RequestFromServer requestFromServer = new RequestFromServer();
                requestFromServer.getCafeterias();
                requestFromServer.getMenus();
            }
            catch (JSONException e){
                e.printStackTrace();
            }
        }
    };

    private Runnable startMainActivity = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    };
}