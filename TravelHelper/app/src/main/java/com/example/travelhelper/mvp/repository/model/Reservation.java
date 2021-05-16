package com.example.travelhelper.mvp.repository.model;

public class Reservation {
    public String Id;
    public String UserId;
    public String RoomId;
    public String Status;
    public String StartDate;
    public String EndDate;

    public Reservation(String id, String userId, String roomId, String status, String startDate, String endDate) {
        Id = id;
        UserId = userId;
        RoomId = roomId;
        Status = status;
        StartDate = startDate;
        EndDate = endDate;
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

    public String getStartDate() {
        return StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }
}
