package com.lamad.studentcafeterias;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import org.json.JSONException;

import java.util.Collections;

public class SplashActivity extends AppCompatActivity {

    private final Handler handler = new Handler();
    private LocationManager locationManager;
    static Location location;
    private final String TAG = "SplashActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        handler.post(startFetchingInBackground);
        setLocation();

//        handler.postDelayed(prepareDataList, 2000); //TODO: Find way to handle this. (Not working)
        handler.postDelayed(startMainActivity, 2500);
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
            intent.putExtra("location", location);
            startActivity(intent);
            finish();
        }
    };

    public static Location getLocation(){
        return location;
    }

    private void setLocation() {
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            noGPSProviderEnabledMessage();
            return;
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            System.out.println("Permission not granted yet, continuing without it");

            //ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
            //        1);
        } else {
            Location locationNetwork = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location locationGps = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location locationPassive = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

            if (locationNetwork != null)
                location = locationNetwork;
            else if (locationGps != null)
                location = locationGps;
            else if (locationPassive != null)
                location = locationPassive;
            else {
                Log.e(TAG, "Unable to get the location");
                Toast.makeText(this, "Unable to get your GPS-location", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void noGPSProviderEnabledMessage() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please turn ON GPS connection of your device")
                .setCancelable(false)
                .setPositiveButton("Turn on", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        builder.show();
    }



//    private Runnable prepareDataList = new Runnable() {
//        @Override
//        public void run() {
//            Log.v(TAG, "Sorting dataList");
//            LocationCalculations.calculateDistanceToAllRestaurants(location);
//            Collections.sort(RestaurantListFragment.dataList);
//            Log.v(TAG, "Sorting dataList done");
//        }
//    };
}