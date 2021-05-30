package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.CreateReservationContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class CreateReservationPresenter implements CreateReservationContract.Presenter {
    private final CreateReservationContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public CreateReservationPresenter(CreateReservationContract.View view) {
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onScreenLoaded(String roomId) {
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("rooms/"+ roomId).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        view.setRoomImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        view.setRoomImageId(R.drawable.camera_lens);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onReserveButtonClicked(Reservations reservation) {
        mDisposable.add(repository.createReservation(reservation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.i(Constants.appLog, s);
                    if(s.contains("Reservation already exists"))
                        view.onReserveFailed();
                    else
                        view.onReserveSuccess();
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage())));
    }

    @Override
    public void onCheckButtonClicked(Reservations reservation) {
        mDisposable.add(repository.checkReservationByDates(reservation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    if(s.contains("Ok"))
                        view.onCheckPassed();
                    else if(s.contains("Too much reservations"))
                        view.onCheckFailed();
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage())));
    }
}
