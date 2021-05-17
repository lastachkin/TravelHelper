package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;


public interface ReservationDetailsContract {
    interface View{
        void setHotelImageBitmap(Bitmap bitmap);
        void setHotelImageId(int id);
        void onDeleteSuccess();
        void onDeleteFailed();
    }

    interface Presenter {
        void onScreenLoaded(String roomId);
        void onDeleteButtonClicked(String roomId);
    }
}
