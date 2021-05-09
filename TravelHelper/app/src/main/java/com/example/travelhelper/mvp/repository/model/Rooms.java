package com.example.travelhelper.mvp.repository.model;

public class Rooms {
    public String Id;
    public String HotelId;
    public int Count;
    public int Cost;
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
