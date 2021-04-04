package com.example.travelhelper.common;

import android.app.Application;

import androidx.room.Room;

import com.example.travelhelper.mvp.repository.AppDatabase;

public class App extends Application {
    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "app_db").build();
    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }
}
