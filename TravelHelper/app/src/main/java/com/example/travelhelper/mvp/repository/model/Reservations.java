package com.example.travelhelper.mvp.repository.model;

import java.util.Date;

public class Reservations {
    public String Id;
    public String UserId;
    public String RoomId;
    public String Status;
    public Date StartDate;
    public Date EndDate;

    public Reservations(String id, String userId, String roomId, String status, Date startDate, Date endDate) {
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

    public Date getStartDate() {
        return StartDate;
    }

    public Date getEndDate() {
        return EndDate;
    }
}
