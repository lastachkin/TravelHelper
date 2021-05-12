package com.example.travelhelper.mvp.contract;


import android.graphics.Bitmap;

public interface RoomEditContract {
    interface View{
        void setRoomImageBitmap(Bitmap bitmap);
        void setRoomImageResource(int id);
    }

    interface Presenter {
        void onScreenLoaded(String hotelId, String type);
    }
}
