package com.example.travelhelper.mvp.repository;

import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.dao.UserDao;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    static CompositeDisposable mDisposable = new CompositeDisposable();

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

    public Single<User> searchUser(String login, String password){
        return App.getInstance().getDatabase().userDao().getByCredentials(login, password);
    }
}
