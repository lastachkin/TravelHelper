package com.example.travelhelper.mvp.repository.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Reservations {
    @SerializedName("id")
    public String Id;
    @SerializedName("userId")
    public String UserId;
    @SerializedName("roomId")
    public String RoomId;
    @SerializedName("status")
    public String Status;
    @SerializedName("startDate")
    public Date StartDate;
    @SerializedName("endDate")
    public Date EndDate;
    @SerializedName("comment")
    public String Comment;

    public Reservations(String id, String userId, String roomId, String status, Date startDate, Date endDate, String comment) {
        Id = id;
        UserId = userId;
        RoomId = roomId;
        Status = status;
        StartDate = startDate;
        EndDate = endDate;
        Comment = comment;
    }

    public String getId() {
        return Id;
    }

    public String getUserId() {
        return UserId;
    }

    public String getRoomId() {
        return RoomId;
    }

    public String getStatus() {
        return Status;
    }

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }

    public String getComment() {
        return Comment;
    }
}
