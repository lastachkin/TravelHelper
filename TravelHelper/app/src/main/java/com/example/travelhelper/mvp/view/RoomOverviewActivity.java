package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.ActivityRoomOverviewBinding;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.mvp.view.adapter.RoomAdapter;
import com.example.travelhelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RoomOverviewActivity extends AppCompatActivity {
    List<Rooms> rooms;
    ActivityRoomOverviewBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomOverviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        String hotelId = (String) bundle.get("hotelId");

        rooms = new ArrayList<>();
        rooms.add(new Rooms("1","1",1, 1, "Standard"));
        RoomAdapter adapter = new RoomAdapter(rooms);
        binding.roomList.setLayoutManager(new LinearLayoutManager(this));
        binding.roomList.setAdapter(adapter);
//        App.getInstance().getApi().getRoomList(hotelId).enqueue(new Callback<List<Rooms>>() {
//            @Override
//            public void onResponse(Call<List<Rooms>> call, Response<List<Rooms>> response) {
//                rooms.clear();
//
//                List<Rooms> roomsResponse = response.body();
//                rooms.addAll(roomsResponse);
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onFailure(Call<List<Rooms>> call, Throwable t) {
//                Log.e(Constants.appLog, t.getMessage());
//            }
//        });
    }
}