package com.example.travelhelper.mvp.repository.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import com.example.travelhelper.mvp.repository.model.User;
import io.reactivex.Maybe;
import io.reactivex.Single;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User WHERE Id = :id")
    Maybe<User> getById(int id);

    @Query("SELECT * FROM User WHERE Login = :login and Password = :password")
    Single<User> getByCredentials(String login, String password);

    @Query("DELETE FROM User")
    void dropTable();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User...users);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    Single<Integer> update(User user);
}
