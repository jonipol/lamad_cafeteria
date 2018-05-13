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


    // method to request menus from the server
    public void getMenus() throws JSONException {
        ServerConnection.get("amicaMenu.json", null, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                System.out.println("JSONObject");
                System.out.println(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray menus) {
                // Do something with the response
                System.out.println(menus);
            }
        });
    }

    // method to request cafeterias from the server
    public void getCafeterias() throws JSONException {
        RequestParams params = new RequestParams();
        params.put("get_cafeterias", "");
        ServerConnection.get("index.php", params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                System.out.println("JSONObject");
                System.out.println(response);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray cafeterias) {
                // Do something with the response
                System.out.println(cafeterias);
            }
        });
    }
}