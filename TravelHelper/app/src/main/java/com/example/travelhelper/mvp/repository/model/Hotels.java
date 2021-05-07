package com.example.travelhelper.mvp.repository.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class Hotels {
    @SerializedName("id")
    public String Id;
    @SerializedName("title")
    public String Title;
    @SerializedName("city")
    public String City;
    @SerializedName("address")
    public String Address;

    public Hotels(String id, String title, String city, String address) {
        Id = id;
        Title = title;
        City = city;
        Address = address;
    }

    public String getId() {
        return Id;
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
        return "Id: " + getId() + " Title: " + getTitle() + " City: " + getCity() + " Address: " + getAddress();
    }
}
