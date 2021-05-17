package com.example.travelhelper.mvp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.FragmentReservationsBinding;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.mvp.view.adapter.ReservationAdapter;
import com.example.travelhelper.utils.Constants;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationsFragment extends Fragment {
    List<Reservations> reservations;
    FragmentReservationsBinding binding;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentReservationsBinding.inflate(getLayoutInflater());

        reservations = new ArrayList<>();
        ReservationAdapter adapter = new ReservationAdapter(reservations);

        Call<List<Reservations>> call = App.getInstance().getApi().getReservationList(Constants.currentUser.getId());
        call.enqueue(new Callback<List<Reservations>>() {
            @Override
            public void onResponse(Call<List<Reservations>> call, Response<List<Reservations>> response) {
                reservations.clear();

                List<Reservations> reservationsResponse = response.body();
                reservations.addAll(reservationsResponse);

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Reservations>> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });

        binding.reservationList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.reservationList.setAdapter(adapter);

        return binding.getRoot();
    }
}
