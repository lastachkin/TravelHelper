package com.example.travelhelper.mvp.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.travelhelper.mvp.repository.dao.UserDao;
import com.example.travelhelper.mvp.repository.model.User;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
