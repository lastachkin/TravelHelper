package com.example.travelhelper.mvp.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.FragmentHotelsBinding;
import com.example.travelhelper.mvp.contract.HotelsContract;
import com.example.travelhelper.mvp.presenter.HotelsPresenter;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.view.adapter.HotelAdapter;
import com.example.travelhelper.utils.Constants;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsFragment extends Fragment implements HotelsContract.View {
    List<Hotels> hotels;
    HotelsPresenter presenter;
    FragmentHotelsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHotelsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        presenter = new HotelsPresenter(this);

        hotels = new ArrayList<>();
        HotelAdapter adapter = new HotelAdapter(hotels);

        // TODO: 06.05.2021 Move logic to presenter
        Call<List<Hotels>> call = App.getInstance().getApi().getHotelList();
        call.enqueue(new Callback<List<Hotels>>() {
            @Override
            public void onResponse(Call<List<Hotels>> call, Response<List<Hotels>> response) {
                hotels.clear();

                List<Hotels> hotelsResponse = (List<Hotels>) response.body();
                hotels.addAll(hotelsResponse);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Hotels>> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });

        binding.hotelList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.hotelList.setAdapter(adapter);

        return view;
    }
}
