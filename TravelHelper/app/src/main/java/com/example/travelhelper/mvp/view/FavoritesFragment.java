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

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.FragmentFavoritesBinding;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.view.adapter.HotelAdapter;
import com.example.travelhelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritesFragment extends Fragment {
    List<Hotels> hotels;
    FragmentFavoritesBinding binding;

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoritesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        // TODO: 18.05.2021 Move logic to repo
        hotels = new ArrayList<>();
        HotelAdapter adapter = new HotelAdapter(hotels);
        Call<List<Hotels>> call = App.getInstance().getApi().getFavoritesByUser(Constants.currentUser.getId());
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
        binding.favoriteList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.favoriteList.setAdapter(adapter);
        return view;
    }
}
