package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.contract.HotelsContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Constants;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HotelsPresenter implements HotelsContract.Presenter {
    private final HotelsContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public HotelsPresenter(HotelsContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onScreenLoaded() {
        //Paste logic from UI
    }
}
