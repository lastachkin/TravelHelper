package com.example.travelhelper.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.travelhelper.common.App;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.mvp.view.HomeActivity;
import com.example.travelhelper.mvp.view.LoginActivity;

import java.util.UUID;

public class LoginPresenter implements LoginContract.Presenter {

    @Nullable
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
    }

    @Override
    public void setView(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void loginButtonClicked() {
        if (view != null) {
            view.showInputError();

            App.getInstance().getDatabase().userDao().insert(new User("8",view.getLogin(), "pass123"));

            view.startHomeActivity();
        }
    }

    @Override
    public void regButtonClicked() {
        if (view != null) {
            view.showInputError();
        }
    }

    @Override
    public void getCurrentUser() {

    }
}
