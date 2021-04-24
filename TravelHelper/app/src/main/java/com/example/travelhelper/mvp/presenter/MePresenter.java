package com.example.travelhelper.mvp.presenter;

import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.example.travelhelper.mvp.contract.MeContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class MePresenter implements MeContract.Presenter {
    @Nullable
    private final MeContract.View view;
    private final Repository repository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public MePresenter(@Nullable MeContract.View view){
        this.view = view;
        repository = new Repository();
    }

    @Override
    public void onEditButtonClicked() {
        if (view != null) {
            if(view.isFieldsEnabled())
                view.setFieldsDisabled();
            else
                view.setFieldsEnabled();
        }
    }

    @Override
    public void onSaveButtonClicked(String password) {
        //update in db
//        mDisposable.add(repository.updateUser(new User(Constants.currentUser.Firstname, Constants.currentUser.Lastname, Constants.currentUser.Email, Constants.currentUser.Phone, Constants.currentUser.Login, password))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(view::onUserUpdated,
//                        throwable -> Log.e(Constants.appLog, throwable.getMessage())));
        //onScreenLoaded();
    }

    @Override
    public void onScreenLoaded() {
        //get data from db by user
        //set data to fields on screen
    }
}
