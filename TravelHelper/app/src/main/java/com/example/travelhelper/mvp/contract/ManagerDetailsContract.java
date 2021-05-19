package com.example.travelhelper.mvp.contract;


public interface ManagerDetailsContract {
    interface View{
        void setCost(String cost);
        void setType(String type);
        void setName(String name);
        void onDeleteSuccess();
        void onDeleteFailed();
    }
    interface Presenter{
        void onScreenLoaded(String reservationId);
        void onRemoveButtonClicked(String reservationId);
    }
}
