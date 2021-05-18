package com.example.travelhelper.mvp.repository;

import android.net.Uri;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.Favorites;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.mvp.repository.model.UsersResponse;
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

    public Observable<String> createHotel(Hotels hotel){
        return App.getInstance().getApi().createHotel(hotel);
    }

    public Observable<String> createRoom(Rooms room){
        return App.getInstance().getApi().createRoom(room);
    }

    public Observable<String> createReservation(Reservations reservation){
        return App.getInstance().getApi().createReservation(reservation);
    }

    public Observable<String> addFavorite(Favorites favorite){
        return App.getInstance().getApi().addFavorite(favorite);
    }

    public Observable<String> searchUser(String login, String password){
        return App.getInstance().getApi().searchUser(login, password);
    }

    public Observable<String> searchHotel(String title, String city){
        return App.getInstance().getApi().searchHotel(title, city);
    }

    public Observable<String> searchFavorite(String userId, String hotelId){
        return App.getInstance().getApi().searchFavorite(userId, hotelId);
    }

    public Call<List<Hotels>> getHotelList(){
        return App.getInstance().getApi().getHotelList();
    }

    public Call<List<Hotels>> getFavoritesByUser(String userId){
        return App.getInstance().getApi().getFavoritesByUser(userId);
    }

    public Call<List<Rooms>> getRoomList(String hotelId){
        return App.getInstance().getApi().getRoomList(hotelId);
    }

    public Call<ReservationsResponse> getReservationDetails(String roomId){
        return App.getInstance().getApi().getReservationDetails(roomId);
    }

    public Call<UsersResponse> getUserById(String id){
        return App.getInstance().getApi().getUserById(id);
    }

    public Call<String> deleteHotel(String id){
        return App.getInstance().getApi().deleteHotel(id);
    }

    public Call<String> deleteRoom(String id){
        return App.getInstance().getApi().deleteRoom(id);
    }

    public Call<String> deleteReservation(String id){
        return App.getInstance().getApi().deleteReservation(id);
    }

    public Observable<String> removeFavorite(String userId, String hotelId){
        return App.getInstance().getApi().deleteFavorite(userId, hotelId);
    }

    public Call<String> updateHotel(String id, Hotels hotel){
        return App.getInstance().getApi().updateHotel(id, hotel);
    }

    public Call<String> updateRoom(String id, Rooms room){
        return App.getInstance().getApi().updateRoom(id, room);
    }

    public Call<String> updateUser(String id, Users user){
        return App.getInstance().getApi().updateUser(id, user);
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
