package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.mvp.contract.ManagerDetailsContract;
import com.example.travelhelper.mvp.contract.ReservationOverviewContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class ReservationOverviewPresenter implements ReservationOverviewContract.Presenter {
    private final  ReservationOverviewContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public ReservationOverviewPresenter( ReservationOverviewContract.View view){
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
                    view.setComment(response.getComment());
                }, throwable -> Log.e(Constants.appLog, throwable.getMessage())));
    }
}
