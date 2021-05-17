package com.example.travelhelper.mvp.presenter;

import android.net.Uri;
import android.util.Log;

import com.example.travelhelper.mvp.contract.CreateRoomContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CreateRoomPresenter implements CreateRoomContract.Presenter {
    private final CreateRoomContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public CreateRoomPresenter(CreateRoomContract.View view) {
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onCreateButtonClicked(Uri uri, Rooms room) {
        repository.uploadRoomPicture(uri, room.getId())
                .addOnSuccessListener(taskSnapshot -> Log.i(Constants.appLog, "Room image uploaded"))
                .addOnFailureListener(e -> Log.e(Constants.appLog, e.getMessage()));

        mDisposable.add(repository.createRoom(room)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.i(Constants.appLog, s);
                    if(s.contains("Room type already exists"))
                        view.onCreationFailed();
                    else
                        view.onCreationSuccess();
                }, throwable -> {
                    Log.e(Constants.appLog, throwable.getMessage());
                }));
    }
}
