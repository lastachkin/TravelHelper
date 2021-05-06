package com.example.travelhelper.mvp.presenter;

import com.example.travelhelper.mvp.contract.HotelsContract;
import com.example.travelhelper.mvp.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;

public class HotelsPresenter implements HotelsContract.Presenter {
    private final HotelsContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public HotelsPresenter(HotelsContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }
}
