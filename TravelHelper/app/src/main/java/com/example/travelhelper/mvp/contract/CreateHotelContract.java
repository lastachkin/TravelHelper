package com.example.travelhelper.mvp.contract;

import android.net.Uri;

import com.example.travelhelper.mvp.repository.model.Hotels;

public interface CreateHotelContract {
    interface View{
        void onCreationSuccess();
        void onCreationFailed();
    }
    interface Presenter {
        //void onUploadPicture(Uri uri, String title);
        void onCreateHotelButtonClicked(Uri uri, String title, Hotels hotel);
    }
}
