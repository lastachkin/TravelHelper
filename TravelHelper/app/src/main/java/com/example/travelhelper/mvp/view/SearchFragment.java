package com.example.travelhelper.mvp.view;

import android.os.Bundle;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.FragmentSearchBinding;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.view.adapter.HotelAdapter;
import com.example.travelhelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    FragmentSearchBinding binding;
    List<String> cities;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        cities = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, cities);
        App.getInstance().getApi().getCityList().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                cities.clear();
                List<String> citiesResponse = response.body();
                if (citiesResponse != null)
                    cities.addAll(citiesResponse);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.citySpinner.setAdapter(adapter);

        binding.findHotelBtn.setOnClickListener(v -> {
            List<Hotels> hotels = new ArrayList<>();
            HotelAdapter hotelAdapter = new HotelAdapter(hotels);
            App.getInstance().getApi().getHotelsByFilters(binding.citySpinner.getSelectedItem().toString(), binding.hotel.getText().toString()).enqueue(new Callback<List<Hotels>>() {
                @Override
                public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                    hotels.clear();
                    List<Hotels> hotelsResponse = response.body();
                    if (hotelsResponse != null)
                        hotels.addAll(hotelsResponse);
                    hotelAdapter.notifyDataSetChanged();
                }
                @Override
                public void onFailure(Call<List<Hotels>> call, Throwable t) {
                    Log.e(Constants.appLog, t.getMessage());
                }
            });
            binding.hotelList.setLayoutManager(new LinearLayoutManager(view.getContext()));
            binding.hotelList.setAdapter(hotelAdapter);
        });
        return view;
    }
}