package com.example.travelhelper.mvp.repository.model;

import java.util.Date;

public class ReservationsResponse {
    public String Id;
    public String UserId;
    public String RoomId;
    public String Status;
    public String StartDate;
    public String EndDate;

    public ReservationsResponse(String id, String userId, String roomId, String status, String startDate, String endDate) {
        Id = id;
        UserId = userId;
        RoomId = roomId;
        Status = status;
        StartDate = startDate;
        EndDate = endDate;
    }
}
