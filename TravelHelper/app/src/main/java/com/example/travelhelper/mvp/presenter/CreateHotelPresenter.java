package com.example.travelhelper.mvp.presenter;

import android.net.Uri;
import android.util.Log;

import com.example.travelhelper.mvp.contract.CreateHotelContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Managers;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Constants;

import java.util.Random;
import java.util.UUID;

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
        repository.uploadHotelPicture(uri, hotel.getId())
                .addOnSuccessListener(taskSnapshot -> Log.i(Constants.appLog, "Hotel image uploaded"))
                .addOnFailureListener(e -> Log.e(Constants.appLog, e.getMessage()));

        String[] dict = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".split("");
        Random rnd = new Random();
        StringBuilder password = new StringBuilder();
        for(int k = 0; k < 6; k++) {
            password.append(dict[rnd.nextInt(dict.length)]);
        }

        Users user = new Users(UUID.randomUUID().toString(), "", "", "", "", hotel.getCity() + "." + hotel.getTitle(), password.toString(), "M");

        mDisposable.add(repository.createUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.i(Constants.appLog, s), throwable -> Log.e(Constants.appLog, throwable.getMessage())));

        mDisposable.add(repository.createManager(new Managers(UUID.randomUUID().toString(), user.getId(), hotel.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> Log.i(Constants.appLog, s), throwable -> Log.e(Constants.appLog, throwable.getMessage())));

        mDisposable.add(repository.createHotel(hotel)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.i(Constants.appLog, s);
                    if(s.contains("Hotel already exists"))
                        view.onCreationFailed();
                    else
                        view.onCreationSuccess();
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage())));
    }
}
