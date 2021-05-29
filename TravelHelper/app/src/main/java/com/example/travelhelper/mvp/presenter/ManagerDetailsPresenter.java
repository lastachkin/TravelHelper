package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.mvp.contract.ManagerDetailsContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManagerDetailsPresenter implements ManagerDetailsContract.Presenter {
    private final  ManagerDetailsContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public ManagerDetailsPresenter( ManagerDetailsContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onScreenLoaded(String reservationId) {
        mDisposable.add(repository.getDetailsForManager(reservationId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response -> {
                    view.setCost(response.getCost() + "$");
                    view.setName(response.getName());
                    view.setType(response.getType());
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage())));
    }

    @Override
    public void onConfirmButtonClicked(String reservationId, Reservations reservation) {
        repository.updateReservation(reservationId, reservation).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(Constants.appLog, response.body());
                view.onConfirmSuccess();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
                view.onConfirmFailed();
            }
        });
    }

    @Override
    public void onRejectButtonClicked(String reservationId, Reservations reservation) {
        repository.updateReservation(reservationId, reservation).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i(Constants.appLog, response.body());
                view.onRejectSuccess();
            }
            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
                view.onRejectFailed();
            }
        });
    }
}
