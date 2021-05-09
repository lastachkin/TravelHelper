package com.example.travelhelper.mvp.contract;

import android.net.Uri;

import com.example.travelhelper.mvp.repository.model.Rooms;

public interface CreateRoomContract {
    interface View{
        void onCreationSuccess();
        void onCreationFailed();
    }
    interface Presenter {
        void onCreateButtonClicked(Uri uri, Rooms room);
    }
}
