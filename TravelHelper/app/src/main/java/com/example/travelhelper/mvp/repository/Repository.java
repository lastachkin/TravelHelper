package com.example.travelhelper.mvp.repository;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.repository.model.Users;

import io.reactivex.Observable;

public class Repository {
    public Observable<String> createUser(Users user){
        return App.getInstance().getApi().createUser(user);
    }

    public Observable<String> searchUser(String login, String password){
        return App.getInstance().getApi().searchUser(login, password);
    }
}
