package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;

public interface HotelDetailsContract {
    interface View{
        void setHotelImageBitmap(Bitmap bitmap);
        void setHotelImageId(int id);
    }
    interface Presenter {
        void onScreenLoaded(String title, String city, String address);
    }
}
