package com.lamad.studentcafeterias;

import android.Manifest;
import android.app.DownloadManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;

public class MainActivity extends AppCompatActivity {

    static Location location;
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

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                // Called when a new location is found by the network location provider.
                MainActivity.location = location;
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {}

            public void onProviderEnabled(String provider) {}

            public void onProviderDisabled(String provider) {}
        };

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }

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
