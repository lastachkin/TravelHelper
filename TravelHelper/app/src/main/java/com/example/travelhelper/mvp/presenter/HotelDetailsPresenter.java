package com.example.travelhelper.mvp.presenter;

import com.example.travelhelper.mvp.contract.HotelDetailsContract;
import com.example.travelhelper.mvp.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;

public class HotelDetailsPresenter implements HotelDetailsContract.Presenter {
    private final HotelDetailsContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public HotelDetailsPresenter(HotelDetailsContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

}
