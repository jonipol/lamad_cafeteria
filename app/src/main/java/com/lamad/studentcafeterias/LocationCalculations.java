package com.lamad.studentcafeterias;

import android.location.Location;
import android.util.Log;
import com.loopj.android.http.JsonHttpResponseHandler;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class LocationCalculations {
    private final static String TAG = "LocationCalculations";

    public static void calculateDistanceToAllRestaurants(Location location) {
        for (Restaurant restaurant : RestaurantListFragment.dataList) {
            calculateDistance(location.getLatitude(), location.getLongitude(),
                    restaurant);
        }
    }

    public static void calculateDistance(double originLatitude, double originLongitude,
                                           final Restaurant restaurant) {

        String originString = "&origins=" + originLatitude + "," + originLongitude;
        String destinationString = "&destinations=" + restaurant.getLatitude() + "," + restaurant.getLongitude();

        String beginningOfUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric";
//        String key = "&key=" + Resources.getSystem().getString(R.string.google_maps_key);
        String key = "&key=AIzaSyDR33nZUk3EdkgvJ_KI-Ke9SrDlwrv9pMc";
        String modeString = "&mode=walking";
        String url = beginningOfUrl + originString + destinationString + key + modeString;

        ServerConnection.post(url, null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject distanceMatrix) {
                try {
                    double distance = Double.valueOf(distanceMatrix.getJSONArray("rows").getJSONObject(0).getJSONArray("elements").getJSONObject(0).getJSONObject("distance").getString("value"));
                    Log.v(TAG, "Distance: " + distance);
                    restaurant.setDistance(distance);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e(TAG, "Json parsing error in calculateDistance");
                }
            }
        });
    }
}
