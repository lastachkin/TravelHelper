package com.example.travelhelper;

import android.app.Application;
import android.content.Context;

import com.example.travelhelper.mvp.repository.remote.RetrofitClient;
import com.example.travelhelper.mvp.repository.remote.TravelHelperApi;

public class App extends Application {
    static App instance;
    Context appContext;
    TravelHelperApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();
        api = RetrofitClient.getInstance().create(TravelHelperApi.class);
    }

    public static App getInstance() {
        return instance;
    }

    public TravelHelperApi getApi(){
        return api;
    }

    public Context getAppContext(){
        return appContext;
    }
}
