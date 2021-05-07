package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.HotelEditContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelEditPresenter implements HotelEditContract.Presenter {
    private final HotelEditContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public HotelEditPresenter(HotelEditContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onScreenLoaded(String title, String city) {
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("hotels/"+ title + "_" + city).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        view.setHotelImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        view.setHotelImageId(R.drawable.camera_lens);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveButtonClicked(Uri uri, Hotels hotel) {
        repository.uploadHotelPicture(uri, hotel.getTitle() + "_" + hotel.getCity())
                .addOnSuccessListener(taskSnapshot -> Log.i(Constants.appLog, "Hotel image uploaded"))
                .addOnFailureListener(e -> Log.e(Constants.appLog, e.getMessage()));

        Call<String> call = App.getInstance().getApi().updateHotel(hotel.getId(), hotel);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(Constants.appLog, response.body());
                view.onSaveSuccess();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });
    }

    @Override
    public void onDeleteButtonClicked() {

    }
}
