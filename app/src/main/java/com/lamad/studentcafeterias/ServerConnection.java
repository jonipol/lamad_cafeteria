package com.lamad.studentcafeterias;

import com.loopj.android.http.*;

/**
 * Created by Janne on 3.5.2018.
 * Little changes by Joni 15.5.2018 - To make the class more modular.
 */

public class ServerConnection {
    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }
}
