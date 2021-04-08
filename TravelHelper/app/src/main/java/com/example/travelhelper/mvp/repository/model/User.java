package com.example.travelhelper.mvp.repository.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User{
    @PrimaryKey(autoGenerate = true)
    public int Id = 0;

    public String Firstname;

    public String Lastname;

    public String Email;

    public String Phone;

    public String Login;

    public String Password;

    public User() {
    }

    public User(String firstname, String lastname, String email, String phone, String login, String password) {
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Phone = phone;
        Login = login;
        Password = password;
    }
}
