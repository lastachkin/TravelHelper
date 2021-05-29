package com.example.travelhelper.mvp.repository.model;

import com.google.gson.annotations.SerializedName;

public class ManagerResponse {
    @SerializedName("name")
    public String Name;
    @SerializedName("type")
    public String Type;
    @SerializedName("cost")
    public String Cost;
    @SerializedName("comment")
    public String Comment;

    public ManagerResponse(String name, String type, String cost, String comment) {
        Name = name;
        Type = type;
        Cost = cost;
        Comment = comment;
    }

    public String getName() {
        return Name;
    }

    public String getType() {
        return Type;
    }

    public String getCost() {
        return Cost;
    }

    public String getComment() {
        return Comment;
    }
}
