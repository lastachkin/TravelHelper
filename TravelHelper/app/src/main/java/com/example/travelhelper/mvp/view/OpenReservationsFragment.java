package com.example.travelhelper.mvp.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.FragmentOpenReservationsBinding;
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

public class OpenReservationsFragment extends Fragment {
    FragmentOpenReservationsBinding binding;
    List<Reservations> openReservations;
    CompositeDisposable mDisposable = new CompositeDisposable();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentOpenReservationsBinding.inflate(getLayoutInflater());

        // TODO: 29.05.2021 Move to presenter
        openReservations = new ArrayList<>();
        ReservationAdapter openAdapter = new ReservationAdapter(openReservations);
        mDisposable.add(App.getInstance().getApi().getHotelByManagerId(Constants.currentUser.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    App.getInstance().getApi().getReservationsByHotel(response).enqueue(new Callback<List<Reservations>>() {
                        @Override
                        public void onResponse(Call<List<Reservations>> call, Response<List<Reservations>> response) {
                            openReservations.clear();

                            assert response.body() != null;
                            for (Reservations item : response.body())
                                if(item.getStatus().contains("O"))
                                    openReservations.add(item);

                            openAdapter.notifyDataSetChanged();
                        }
                        @Override
                        public void onFailure(Call<List<Reservations>> call, Throwable t) {
                            Log.e(Constants.appLog, t.getMessage());
                        }
                    });
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage())));

        binding.openReservations.setLayoutManager(new LinearLayoutManager(binding.getRoot().getContext()));
        binding.openReservations.setAdapter(openAdapter);

        return binding.getRoot();
    }
}