package com.example.travelhelper.mvp.contract;

import com.example.travelhelper.mvp.repository.model.Reservations;

public interface ReservationOverviewContract {
    interface View{
        void setCost(String cost);
        void setType(String type);
        void setName(String name);
        void setComment(String comment);
    }
    interface Presenter{
        void onScreenLoaded(String reservationId);
    }
}
