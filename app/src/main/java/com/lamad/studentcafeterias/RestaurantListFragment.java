package com.lamad.studentcafeterias;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestaurantListFragment extends Fragment {

    String TAG = "RestaurantListFragment";
    ExpandableListView expandableListView;
    ExpandableListAdapter listAdapter;
    static List<Restaurant> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.list_fragment, container, false);

        expandableListView = view.findViewById(R.id.expendableRestaurantListView);

        // Get distances for the restaurants
        Location currentLocation = MainActivity.location;
        System.out.println(currentLocation);
        if (currentLocation != null) {
            for (Restaurant restaurant : dataList) {
                restaurant.setDistance(LocationCalculations.calculateDistance(currentLocation.getLatitude(), currentLocation.getLongitude(), restaurant.getLatitude(), restaurant.getLongitude()));
            }
        }
        // Sort datalist
        dataList.sort(Restaurant.RestauranComparator);

        listAdapter = new ExpandableRestaurantListAdapter(this.getContext(), dataList, getFragmentManager());
        expandableListView.setAdapter(listAdapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousItem = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousItem)
                    expandableListView.collapseGroup(previousItem);
                previousItem = groupPosition;
            }
        });

        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);

        if (getContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            // Register the listener with the Location Manager to receive location updates
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }


        return view;
    }

    public static void parseCafeterias(JSONArray jsonArray) {
        try {
            dataList.addAll(JSONParser.readRestaurantJson(jsonArray));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void parseMenus(JSONArray jsonArray) {
        try {
            JSONParser.readDishJson(jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Define a listener that responds to location updates
    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            // Called when a new location is found by the network location provider.
            MainActivity.location = location;
            Log.v("LOCATION", " " + location);
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {}

        public void onProviderEnabled(String provider) {}

        public void onProviderDisabled(String provider) {}
    };
}
