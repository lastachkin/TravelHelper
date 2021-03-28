package com.example.travelhelper.mvp.repository.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User{
    @PrimaryKey
    @NonNull
    public String Id;

    public String Login;

    public String Password;

    public User() {
    }

    public User(@NonNull String id, String login, String password) {
        Id = id;
        Login = login;
        Password = password;
    }
}
