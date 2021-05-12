package com.example.travelhelper.mvp.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.RoomEditContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.utils.Constants;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;

import io.reactivex.disposables.CompositeDisposable;

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
}
