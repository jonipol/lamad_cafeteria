package com.lamad.studentcafeterias;

import android.content.Context;
import android.os.AsyncTask;

public class SplashScreenTask extends AsyncTask<Void, Void, Void> {
    private Context context;
    private SplashScreenTaskCallback listener = null;

    public SplashScreenTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... params) {


        return null;
    }

    @Override
    public void onPreExecute() {}

    @Override
    public void onPostExecute(Void v) {
        if (listener != null) {
            listener.OnSplashScreenTaskCompleted();
        }
    }

    public void setListener(SplashScreenTaskCallback listener) {
        this.listener = listener;
    }

    public interface SplashScreenTaskCallback {
        void OnSplashScreenTaskCompleted();
    }
}
