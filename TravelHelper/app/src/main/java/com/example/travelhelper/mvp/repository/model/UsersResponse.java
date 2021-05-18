package com.example.travelhelper.mvp.repository.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class UsersResponse {
    @SerializedName("id")
    public String Id;
    @SerializedName("firstname")
    public String Firstname;
    @SerializedName("lastname")
    public String Lastname;
    @SerializedName("phone")
    public String Phone;
    @SerializedName("email")
    public String Email;
    @SerializedName("login")
    public String Login;
    @SerializedName("password")
    public String Password;
    @SerializedName("role")
    public String Role;

    public UsersResponse(String id, String firstname, String lastname, String phone, String email, String login, String password, String role) {
        Id = id;
        Firstname = firstname;
        Lastname = lastname;
        Phone = phone;
        Email = email;
        Login = login;
        Password = password;
        Role = role;
    }

    public String getId() {
        return Id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public String getPhone() {
        return Phone;
    }

    public String getEmail() {
        return Email;
    }

    public String getLogin() {
        return Login;
    }

    public String getPassword() {
        return Password;
    }

    public String getRole() {
        return Role;
    }
}
