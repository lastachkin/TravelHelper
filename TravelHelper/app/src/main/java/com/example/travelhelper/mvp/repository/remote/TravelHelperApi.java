package com.example.travelhelper.mvp.repository.remote;

import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Users;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface TravelHelperApi {
    @GET("api/user")
    Observable<String> searchUser(@Query("login") String login, @Query("password") String password);
    @GET("api/hotel/")
    Observable<String> searchHotel(@Query("title") String title, @Query("city") String city);
    @GET("api/hotel/")
    Call<List<Hotels>> getHotelList();

    @POST("api/user")
    Observable<String> createUser(@Body Users user);
    @POST("api/hotel")
    Observable<String> createHotel(@Body Hotels hotel);
}
