package com.example.travelhelper;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.example.travelhelper.mvp.repository.AppDatabase;
import com.example.travelhelper.mvp.repository.remote.RetrofitClient;
import com.example.travelhelper.mvp.repository.remote.TravelHelperApi;

public class App extends Application {
    public static App instance;
    public static Context appContext;
    private AppDatabase database;
    private TravelHelperApi api;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        appContext = getApplicationContext();
        database = Room.databaseBuilder(this, AppDatabase.class, "app_db").fallbackToDestructiveMigration().build();
        api = RetrofitClient.getInstance().create(TravelHelperApi.class);
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

    public TravelHelperApi getApi(){
        return api;
    }
}
