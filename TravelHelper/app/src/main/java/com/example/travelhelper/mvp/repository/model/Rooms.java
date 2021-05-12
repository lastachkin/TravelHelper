package com.example.travelhelper.mvp.repository.model;

import com.google.gson.annotations.SerializedName;

public class Rooms {
    @SerializedName("id")
    public String Id;
    @SerializedName("hotelId")
    public String HotelId;
    @SerializedName("count")
    public int Count;
    @SerializedName("cost")
    public int Cost;
    @SerializedName("type")
    public String Type;

    public Rooms(String id, String hotelId, int count, int cost, String type) {
        Id = id;
        HotelId = hotelId;
        Count = count;
        Cost = cost;
        Type = type;
    }

    public String getId() {
        return Id;
    }

    public String getHotelId() {
        return HotelId;
    }

    public int getCount() {
        return Count;
    }

    public int getCost() {
        return Cost;
    }

    public String getType() {
        return Type;
    }
}
