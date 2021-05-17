package com.example.travelhelper.mvp.repository.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class ReservationsResponse {
    @SerializedName("title")
    public String Title;
    @SerializedName("type")
    public String Type;
    @SerializedName("city")
    public String City;
    @SerializedName("address")
    public String Address;
    @SerializedName("cost")
    public int Cost;

    public ReservationsResponse(String title, String type, String city, String address, int cost) {
        Title = title;
        Type = type;
        City = city;
        Address = address;
        Cost = cost;
    }

    public String getTitle() {
        return Title;
    }

    public String getType() {
        return Type;
    }

    public String getCity() {
        return City;
    }

    public String getAddress() {
        return Address;
    }

    public int getCost() {
        return Cost;
    }

    @NonNull
    @Override
    public String toString() {
        return "Title:" + getTitle() + " Type:" + getType() + " City:" + getCity() + " Address:" + getAddress();
    }
}
