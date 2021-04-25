package com.example.travelhelper.mvp.repository;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.mvp.repository.model.Users;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public class Repository {
    public Observable<String> createUser(Users user){
        return App.getInstance().getApi().createUser(user);
    }

    public Single<User> searchUser(String login, String password){
        return App.getInstance().getDatabase().userDao().getByCredentials(login, password);
    }

    public Completable updateUser(User user){
        return App.getInstance().getDatabase().userDao().update(user);
    }
}
