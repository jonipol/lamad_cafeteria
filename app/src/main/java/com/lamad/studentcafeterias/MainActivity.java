package com.lamad.studentcafeterias;

import android.app.DownloadManager;
import android.content.pm.ActivityInfo;
import android.location.Location;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    static Location location = null;
    private final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Emergency fix. If not restarted the screen will be blank
        // TODO: Create more sophisticated solution to resetting the view

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //Log.v("MainActivity", "Starting new Frag");
        Handler handler = new Handler();
        handler.postDelayed(startFetchingInBackground,50);



        Fragment fragment = new RestaurantListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentPlaceHolder, fragment);
        transaction.commit();


//        Log.v("TAG", location.toString());

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
}
