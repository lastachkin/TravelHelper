package com.example.travelhelper.mvp.presenter;

import android.net.Uri;
import android.util.Log;

import com.example.travelhelper.mvp.contract.CreateHotelContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CreateHotelPresenter implements CreateHotelContract.Presenter {
    private final CreateHotelContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public CreateHotelPresenter(CreateHotelContract.View view) {
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreateHotelButtonClicked(Uri uri, String title, Hotels hotel) {
        //Upload image to Firebase
        //Remove title
        repository.uploadHotelPicture(uri, hotel.getId())
                .addOnSuccessListener(taskSnapshot -> Log.i(Constants.appLog, "Hotel image uploaded"))
                .addOnFailureListener(e -> Log.e(Constants.appLog, e.getMessage()));

        mDisposable.add(repository.createHotel(hotel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.i(Constants.appLog, s);
                    if(s.contains("Hotel already exists"))
                        view.onCreationFailed();
                    else
                        view.onCreationSuccess();
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage()))
        );
    }
}
