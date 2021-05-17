package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.ReservationDetailsContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationDetailsPresenter implements ReservationDetailsContract.Presenter {
    private final ReservationDetailsContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public ReservationDetailsPresenter(ReservationDetailsContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }


    @Override
    public void onScreenLoaded(String roomId) {
        //Get room image
        // TODO: 17.05.2021 Move logic to repo
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("rooms/"+ roomId).getFile(localFile)
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

    @Override
    public void onDeleteButtonClicked(String reservationId) {
        //Delete instance from db
        repository.deleteReservation(reservationId).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(Constants.appLog, response.body());
                view.onDeleteSuccess();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
                view.onDeleteFailed();
            }
        });
    }
}
