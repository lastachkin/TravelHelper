package com.example.travelhelper.mvp.repository;

import android.net.Uri;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Rooms;
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

    public Observable<String> createRoom(Rooms room){
        return App.getInstance().getApi().createRoom(room);
    }

    public Observable<String> searchHotel(String title, String city){
        return App.getInstance().getApi().searchHotel(title, city);
    }

    public Call<List<Hotels>> getHotelList(){
        return App.getInstance().getApi().getHotelList();
    }

    public Call<List<Rooms>> getRoomList(String hotelId){
        return App.getInstance().getApi().getRoomList(hotelId);
    }

    public Call<String> deleteHotel(String id){
        return App.getInstance().getApi().deleteHotel(id);
    }

    public Call<String> deleteRoom(String id){
        return App.getInstance().getApi().deleteRoom(id);
    }

    public Call<String> updateHotel(String id, Hotels hotel){
        return App.getInstance().getApi().updateHotel(id, hotel);
    }

    public Call<String> updateRoom(String id, Rooms room){
        return App.getInstance().getApi().updateRoom(id, room);
    }

    public UploadTask uploadHotelPicture(Uri uri, String title){
        StorageReference imageRef = firebaseStorageRef.child("hotels/" + title);
        return imageRef.putFile(uri);
    }

    public UploadTask uploadRoomPicture(Uri uri, String title){
        StorageReference imageRef = firebaseStorageRef.child("rooms/" + title);
        return imageRef.putFile(uri);
    }
}
