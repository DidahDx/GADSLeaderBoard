package com.didahdx.gadsleaderboard;

import android.app.Application;

import timber.log.Timber;

public class GADsApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if(BuildConfig.DEBUG){
            Timber.plant(new Timber.DebugTree());
        }
    }
}
