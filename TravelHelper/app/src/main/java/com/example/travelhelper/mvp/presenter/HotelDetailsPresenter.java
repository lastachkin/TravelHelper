package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.HotelDetailsContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

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
    public void onScreenLoaded(String hotelId) {
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
    }
}
