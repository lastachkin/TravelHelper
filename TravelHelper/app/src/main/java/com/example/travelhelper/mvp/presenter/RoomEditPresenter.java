package com.example.travelhelper.mvp.presenter;

import com.example.travelhelper.mvp.contract.RoomEditContract;
import com.example.travelhelper.mvp.repository.Repository;

import io.reactivex.disposables.CompositeDisposable;

public class RoomEditPresenter implements RoomEditContract.Presenter {
    private final RoomEditContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public RoomEditPresenter(RoomEditContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }
}
