package com.example.travelhelper.mvp.repository;

import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.utils.Extensions;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    public static boolean loginResult;

    public static void insertUser(User user){
        assert user != null;
        Completable.fromAction(() -> {
            App.getInstance().getDatabase().userDao().insert(user);
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
    }

    public static boolean login(String login){
        App.getInstance().getDatabase().userDao().getByLogin(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<User>() {
                    @Override
                    public void onSuccess(User user) {
                        Repository.loginResult = true;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Repository.loginResult = false;
                    }
                });

        return Repository.loginResult;
    }
}
