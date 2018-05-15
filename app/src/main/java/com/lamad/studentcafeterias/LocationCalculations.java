package com.lamad.studentcafeterias;

import android.content.res.Resources;

import com.loopj.android.http.JsonHttpResponseHandler;

public class LocationCalculations {

    public static double calculateDistance(double originLatitude, double originLongitude,
                                           double destinationLatitude, double destinationLongitude) {

        String originString = "origins=" + originLatitude + "," + originLongitude;
        String destinationString = "destinations=" + destinationLatitude + "," + destinationLongitude;

        String beginningOfUrl = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&";
        String endOfUrl = "&key=" + Resources.getSystem().getString(R.string.google_maps_key);

        ServerConnection.post("studentCafeteriaMenus.json", null, new JsonHttpResponseHandler() {

        });

        return 0;
    }
}
