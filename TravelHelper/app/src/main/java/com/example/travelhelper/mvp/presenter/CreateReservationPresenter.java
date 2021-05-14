package com.example.travelhelper.mvp.presenter;

import com.example.travelhelper.mvp.contract.CreateReservationContract;
import com.example.travelhelper.mvp.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;

public class CreateReservationPresenter implements CreateReservationContract.Presenter {
    private final CreateReservationContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public CreateReservationPresenter(CreateReservationContract.View view) {
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }
}
