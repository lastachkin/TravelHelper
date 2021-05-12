package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.RoomEditContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomEditPresenter implements RoomEditContract.Presenter {
    private final RoomEditContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public RoomEditPresenter(RoomEditContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onScreenLoaded(String hotelId, String type) {
        // TODO: 12.05.2021 Move logic to repo
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("rooms/"+ hotelId + "_" + type).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        view.setRoomImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        view.setRoomImageResource(R.drawable.camera_lens);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDeleteButtonClicked(Rooms room) {
        //Delete image from Firebase
        FirebaseStorage.getInstance().getReference().child("rooms/"+ room.getHotelId() + "_" + room.getType()).delete()
                .addOnSuccessListener(taskSnapshot -> Log.i(Constants.appLog, "Room image deleted"))
                .addOnFailureListener(e -> Log.e(Constants.appLog, e.getMessage()));

        //Delete instance from db
        repository.deleteRoom(room.getId()).enqueue(new Callback<String>() {
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

    @Override
    public void onSaveButtonClicked(Uri uri, Rooms room) {
        if(uri != null){
            repository.uploadRoomPicture(uri, room.getHotelId() + "_" + room.getType())
                    .addOnSuccessListener(taskSnapshot -> Log.i(Constants.appLog, "Room image uploaded"))
                    .addOnFailureListener(e -> Log.e(Constants.appLog, e.getMessage()));
        }

        repository.updateRoom(room.getId(), room).enqueue(new Callback<String>() {
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
}
