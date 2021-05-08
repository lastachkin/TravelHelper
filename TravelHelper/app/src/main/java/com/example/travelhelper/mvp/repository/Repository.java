package com.example.travelhelper.mvp.repository;

import android.net.Uri;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Users;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;

public class Repository {
    FirebaseStorage firebaseStorage;
    StorageReference firebaseStorageRef;

    public Repository() {
        firebaseStorage = FirebaseStorage.getInstance();
        firebaseStorageRef = firebaseStorage.getReference();
    }

    public Observable<String> createUser(Users user){
        return App.getInstance().getApi().createUser(user);
    }

    public Observable<String> searchUser(String login, String password){
        return App.getInstance().getApi().searchUser(login, password);
    }

    public Observable<String> createHotel(Hotels hotel){
        return App.getInstance().getApi().createHotel(hotel);
    }

    public Observable<String> searchHotel(String title, String city){
        return App.getInstance().getApi().searchHotel(title, city);
    }

    public Call<List<Hotels>> getHotelList(){
        return App.getInstance().getApi().getHotelList();
    }

    public Call<String> deleteHotel(String id){
        return App.getInstance().getApi().deleteHotel(id);
    }

    public UploadTask uploadHotelPicture(Uri uri, String title){
        StorageReference imageRef = firebaseStorageRef.child("hotels/" + title);
        return imageRef.putFile(uri);
    }
}
