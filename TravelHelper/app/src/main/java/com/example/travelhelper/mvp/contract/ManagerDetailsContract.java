package com.example.travelhelper.mvp.contract;


import com.example.travelhelper.mvp.repository.model.Reservations;

public interface ManagerDetailsContract {
    interface View{
        void setCost(String cost);
        void setType(String type);
        void setName(String name);
        void onConfirmSuccess();
        void onConfirmFailed();
        void onRejectSuccess();
        void onRejectFailed();
    }
    interface Presenter{
        void onScreenLoaded(String reservationId);
        void onConfirmButtonClicked(String reservationId, Reservations reservation);
        void onRejectButtonClicked(String reservationId, Reservations reservation);
    }
}
