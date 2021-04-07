package com.example.travelhelper.mvp.presenter;


import android.util.Log;

import androidx.annotation.Nullable;

import com.example.travelhelper.common.App;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.repository.model.User;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    @Nullable
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
    }

    @Override
    public void loginButtonClicked(String login, String pass) {
        view.showInputError();

        Completable.fromAction(() -> {
            App.getInstance().getDatabase().userDao().insert(new User("1", login, "pass123"));
        }).observeOn(AndroidSchedulers.mainThread())
          .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
             @Override
             public void onSubscribe(Disposable d) {
             }

             @Override
             public void onComplete() {
             }

             @Override
             public void onError(Throwable e) {
                    Log.e("LOG", e.getMessage());
             }
        });

        view.startHomeActivity();
    }

    @Override
    public void regButtonClicked() {
        view.showInputError();
    }

    @Override
    public void getCurrentUser() {

    }
}
