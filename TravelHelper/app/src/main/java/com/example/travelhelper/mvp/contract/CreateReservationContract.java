package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;

import com.example.travelhelper.mvp.repository.model.Reservations;

public interface CreateReservationContract {
    interface View{
        void onReserveSuccess();
        void onReserveFailed();
        void setRoomImageBitmap(Bitmap bitmap);
        void setRoomImageId(int id);
    }
    interface Presenter {
        void onScreenLoaded(String roomId);
        void onReserveButtonClicked(Reservations reservation);
    }
}
