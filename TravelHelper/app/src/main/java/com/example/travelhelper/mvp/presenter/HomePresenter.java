package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.contract.HomeContract;
import com.example.travelhelper.mvp.repository.model.User;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    @Nullable
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view){
        this.view = view;
    }

    @Override
    public void initLoginField() {
//        App.getInstance().getDatabase().userDao().getById(1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableMaybeObserver<User>() {
//                    @Override
//                    public void onSuccess(User user) {
//                        view.setLogin(user.Login);
//                        Log.i("LOG", user.Login);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("LOG", e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("LOG", "No record");
//                    }
//                });
    }
}
