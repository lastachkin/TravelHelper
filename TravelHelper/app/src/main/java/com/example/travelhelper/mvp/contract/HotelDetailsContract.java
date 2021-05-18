package com.example.travelhelper.mvp.contract;

import android.graphics.Bitmap;

import com.example.travelhelper.mvp.repository.model.Favorites;

public interface HotelDetailsContract {
    interface View{
        void setHotelImageBitmap(Bitmap bitmap);
        void setHotelImageId(int id);
        void onFavoriteHotelAdded();
        void onFavoriteHotelRemoved();
    }
    interface Presenter {
        void onScreenLoaded(String hotelId, String userId);
        void onFavoriteButtonClicked(Favorites favorite);
    }
}
