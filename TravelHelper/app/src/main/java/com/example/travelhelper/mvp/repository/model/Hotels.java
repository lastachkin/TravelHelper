package com.example.travelhelper.mvp.repository.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Hotels {
    @SerializedName("title")
    public String Title;
    @SerializedName("city")
    public String City;
    @SerializedName("address")
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

    @NonNull
    @Override
    public String toString() {
        return "Title: " + getTitle() + " City: " + getCity() + " Address: " + getAddress();
    }
}
