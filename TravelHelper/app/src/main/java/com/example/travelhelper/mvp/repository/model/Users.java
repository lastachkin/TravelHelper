package com.example.travelhelper.mvp.repository.model;

public class Users {
    public String Firstname;
    public String Lastname;
    public String Phone;
    public String Email;
    public String Login;
    public String Password;
    public String Role;

    public Users(){

    }

    public Users(String firstname, String lastname, String phone, String email, String login, String password, String role) {
        Firstname = firstname;
        Lastname = lastname;
        Phone = phone;
        Email = email;
        Login = login;
        Password = password;
        Role = role;
    }
}
