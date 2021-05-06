package com.example.travelhelper.mvp.repository.model;

public class Hotels {
    public String Title;
    public String City;
    public String Address;

    public Hotels(String title, String city, String address) {
        Title = title;
        City = city;
        Address = address;
    }

    public String getTitle() {
        return Title;
    }

    public String getCity() {
        return City;
    }

    public String getAddress() {
        return Address;
    }
}
