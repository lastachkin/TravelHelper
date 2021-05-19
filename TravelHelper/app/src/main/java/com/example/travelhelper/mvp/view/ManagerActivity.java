package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.ActivityManagerBinding;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.view.adapter.ReservationAdapter;
import com.example.travelhelper.utils.Constants;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerActivity extends AppCompatActivity {
    ActivityManagerBinding binding;
    List<Reservations> reservations;
    CompositeDisposable mDisposable = new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManagerBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        reservations = new ArrayList<>();
        ReservationAdapter adapter = new ReservationAdapter(reservations);

        // TODO: 18.05.2021 Move logic to repo and migrate to rx
        mDisposable.add(App.getInstance().getApi().getHotelByManagerId(Constants.currentUser.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    App.getInstance().getApi().getReservationsByHotel(response).enqueue(new Callback<List<Reservations>>() {
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
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage()))
        );

        binding.reservationList.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.reservationList.setAdapter(adapter);

        binding.logout.setOnClickListener(v -> startActivity(new Intent(this, LoginActivity.class)));
    }
}