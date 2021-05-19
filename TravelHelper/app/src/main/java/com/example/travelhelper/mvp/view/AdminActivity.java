package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.ActivityAdminBinding;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.view.adapter.HotelAdapter;
import com.example.travelhelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdminActivity extends AppCompatActivity{
    ActivityAdminBinding binding;
    List<Hotels> hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        hotels = new ArrayList<>();
        HotelAdapter adapter = new HotelAdapter(hotels);

        Call<List<Hotels>> call = App.getInstance().getApi().getHotelList();
        call.enqueue(new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                hotels.clear();

                List<Hotels> hotelsResponse = response.body();
                if (hotelsResponse != null) {
                    hotels.addAll(hotelsResponse);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });

        binding.hotelList.setLayoutManager(new LinearLayoutManager(this));
        binding.hotelList.setAdapter(adapter);

        binding.createHotelBtn.setOnClickListener(view -> startActivity(new Intent(this, CreateHotelActivity.class)));
        binding.logout.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}