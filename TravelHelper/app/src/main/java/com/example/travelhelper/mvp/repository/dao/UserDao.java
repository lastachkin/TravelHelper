package com.example.travelhelper.mvp.repository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.travelhelper.mvp.repository.model.User;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE Id = :id")
    User getById(String id);



    @Query("DELETE FROM User")
    void nukeTable();

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Delete
    void delete(User user);
}
