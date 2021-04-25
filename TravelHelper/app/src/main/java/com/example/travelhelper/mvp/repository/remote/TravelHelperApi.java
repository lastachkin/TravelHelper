package com.example.travelhelper.mvp.repository.remote;

import com.example.travelhelper.mvp.repository.model.Users;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface TravelHelperApi {
    @POST("api/register")
    Observable<String> createUser(@Body Users user);
}
