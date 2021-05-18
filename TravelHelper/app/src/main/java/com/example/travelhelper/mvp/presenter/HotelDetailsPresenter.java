package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.HotelDetailsContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Favorites;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelDetailsPresenter implements HotelDetailsContract.Presenter {
    private final HotelDetailsContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public HotelDetailsPresenter(HotelDetailsContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onScreenLoaded(String hotelId, String userId) {
        //Set image
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("hotels/"+ hotelId).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        view.setHotelImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        view.setHotelImageId(R.drawable.camera_lens);
                    });
        } catch (IOException e) {
            Log.e(Constants.appLog, e.getMessage());
        }

        mDisposable.add(repository.searchFavorite(userId, hotelId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if(s.contains("Exists"))
                        view.onFavoriteHotelAdded();
                    else if(s.contains("Does not exist"))
                        view.onFavoriteHotelRemoved();
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage()))
        );
    }

    @Override
    public void onFavoriteButtonClicked(Favorites favorite) {
        // TODO: 18.05.2021 Replace by flatmap 
        mDisposable.add(repository.searchFavorite(favorite.getUserId(), favorite.getHotelId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if(s.contains("Exists")){
                        mDisposable.add(repository.removeFavorite(favorite.getUserId(), favorite.getHotelId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(response -> {
                                    if(response.contains("Favorite hotel deleted"))
                                        view.onFavoriteHotelRemoved();
                                }, throwable -> Log.e(Constants.appLog, throwable.getMessage()))
                        );
                    }
                    else if(s.contains("Does not exist")){
                        mDisposable.add(repository.addFavorite(favorite)
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(response -> {
                                    if(response.contains("Favorite hotel added"))
                                        view.onFavoriteHotelAdded();
                                }, throwable -> Log.e(Constants.appLog, throwable.getMessage()))
                        );
                    }
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage()))
        );
    }
}
