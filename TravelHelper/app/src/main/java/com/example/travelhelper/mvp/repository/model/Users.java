package com.example.travelhelper.mvp.repository.model;

public class Users {
    public String Id;
    public String Firstname;
    public String Lastname;
    public String Phone;
    public String Email;
    public String Login;
    public String Password;
    public String Role;

    public Users(String id, String firstname, String lastname, String phone, String email, String login, String password, String role) {
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
