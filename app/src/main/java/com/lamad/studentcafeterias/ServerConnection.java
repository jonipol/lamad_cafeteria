package com.lamad.studentcafeterias;

import com.loopj.android.http.*;

/**
 * Created by Janne on 3.5.2018.
 */

public class ServerConnection {
    private static AsyncHttpClient client = new AsyncHttpClient();
    private static final String BASE_URL = "http://cs.uef.fi/~jannkar/lamad_server/";

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }

}
