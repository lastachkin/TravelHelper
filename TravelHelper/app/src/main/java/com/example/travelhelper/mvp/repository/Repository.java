package com.example.travelhelper.mvp.repository;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
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

    public UploadTask uploadHotelPicture(Uri uri, String title){
        StorageReference imageRef = firebaseStorageRef.child("hotels/" + title);
        return imageRef.putFile(uri);
    }

//    public void downloadImageByName(String name){
//        try{
//            final File localFile = File.createTempFile("minsk", "jpg");
//            storageRef.child("hotels/minsk.jpg").getFile(localFile)
//                    .addOnSuccessListener(taskSnapshot -> {
//                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
//                        //binding.hotelPic.setImageBitmap(bitmap);
//                    })
//                    .addOnFailureListener(e -> Extensions.errorToast("Ошибка загрузки"));
//            }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//    }
}
