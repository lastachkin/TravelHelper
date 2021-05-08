package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;
import android.net.Uri;

import com.example.travelhelper.mvp.repository.model.Hotels;

public interface HotelEditContract {
    interface View{
        void onSaveSuccess();
        void onSaveFailed();
        void onDeleteSuccess();
        void onDeleteFailed();
        void setHotelImageBitmap(Bitmap bitmap);
        void setHotelImageId(int id);
    }
    interface Presenter {
        void onScreenLoaded(String title, String city);
        void onSaveButtonClicked(Uri uri, Hotels hotel);
        void onDeleteButtonClicked(Hotels hotel);
    }
}
