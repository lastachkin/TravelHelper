package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;

import com.example.travelhelper.mvp.repository.model.Reservations;

import java.util.Date;

public interface CreateReservationContract {
    interface View{
        void onReserveSuccess();
        void onReserveFailed();
        void setRoomImageBitmap(Bitmap bitmap);
        void setRoomImageId(int id);
        void onCheckPassed();
        void onCheckFailed();
    }
    interface Presenter {
        void onScreenLoaded(String roomId);
        void onReserveButtonClicked(Reservations reservation);
        void onCheckButtonClicked(Reservations reservation);
    }
}
