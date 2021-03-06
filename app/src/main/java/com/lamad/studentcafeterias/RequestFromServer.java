package com.lamad.studentcafeterias;

import android.util.Log;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Janne on 3.5.2018.
 */

public class RequestFromServer {

    private static final String BASE_URL = "http://cs.uef.fi/~jannkar/lamad_server/";

    // method to request menus from the server
    public void getMenus() throws JSONException {
        ServerConnection.get(BASE_URL + "studentCafeteriaMenus.json", null, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray menus) {
                // Do something with the response
                RestaurantListFragment.parseMenus(menus);
            }
        });
    }

    // method to request cafeterias from the server
    public void getCafeterias() throws JSONException {
        RequestParams params = new RequestParams();
        params.put("get_cafeterias", "");
        ServerConnection.get(BASE_URL + "requests.php", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray cafeterias) {
                // Do something with the response
                RestaurantListFragment.parseCafeterias(cafeterias);
            }
        });
    }
}
