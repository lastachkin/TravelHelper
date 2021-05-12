package com.example.travelhelper.mvp.contract;


import android.graphics.Bitmap;
import android.net.Uri;

import com.example.travelhelper.mvp.repository.model.Rooms;

public interface RoomEditContract {
    interface View{
        void setRoomImageBitmap(Bitmap bitmap);
        void setRoomImageResource(int id);
        void onDeleteSuccess();
        void onDeleteFailed();
        void onSaveSuccess();
        void onSaveFailed();
    }

    interface Presenter {
        void onScreenLoaded(String hotelId, String type);
        void onDeleteButtonClicked(Rooms room);
        void onSaveButtonClicked(Uri uri, Rooms room);
    }
}
