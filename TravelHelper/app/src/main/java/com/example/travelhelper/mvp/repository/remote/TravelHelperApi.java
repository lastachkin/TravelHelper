package com.example.travelhelper.mvp.repository.remote;

import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.mvp.repository.model.Users;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface TravelHelperApi {
    @GET("api/user")
    Observable<String> searchUser(@Query("login") String login, @Query("password") String password);
    @GET("api/hotel/")
    Observable<String> searchHotel(@Query("title") String title, @Query("city") String city);
    @GET("api/hotel/")
    Call<List<Hotels>> getHotelList();
    @GET("api/room/{id}")
    Call<List<Rooms>> getRoomList(@Path("id") String hotelId);
    @GET("api/reservation/{userId}")
    Call<List<Reservations>> getReservationList(@Path("userId") String userId);
    @GET("api/reservationdetails")
    Call<ReservationsResponse> getReservationDetails(@Query("roomId") String roomId);

    @POST("api/user")
    Observable<String> createUser(@Body Users user);
    @POST("api/hotel")
    Observable<String> createHotel(@Body Hotels hotel);
    @POST("api/room")
    Observable<String> createRoom(@Body Rooms room);
    @POST("api/reservation")
    Observable<String> createReservation(@Body Reservations reservation);

    @PUT("api/hotel/{id}")
    Call<String> updateHotel(@Path("id") String id, @Body Hotels hotel);
    @PUT("api/room/{id}")
    Call<String> updateRoom(@Path("id") String id, @Body Rooms room);

    @DELETE("api/hotel/{id}")
    Call<String> deleteHotel(@Path("id") String id);
    @DELETE("api/room/{id}")
    Call<String> deleteRoom(@Path("id") String id);
    @DELETE("api/reservation/{id}")
    Call<String> deleteReservation(@Path("id") String id);
}
