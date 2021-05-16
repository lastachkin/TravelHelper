package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;

import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.repository.model.Rooms;

public interface CreateReservationContract {
    interface View{
        void onReserveSuccess();
        void onReserveFailed();
        void setRoomImageBitmap(Bitmap bitmap);
        void setRoomImageId(int id);
    }
    interface Presenter {
        void onScreenLoaded(String hotelId, String type);
        void onReserveButtonClicked(Reservations reservation);
    }
}
