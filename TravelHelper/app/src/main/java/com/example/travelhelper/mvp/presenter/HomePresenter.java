package com.example.travelhelper.mvp.presenter;

import androidx.annotation.Nullable;

import com.example.travelhelper.common.App;
import com.example.travelhelper.mvp.contract.HomeContract;
import com.example.travelhelper.mvp.repository.model.User;

public class HomePresenter implements HomeContract.Presenter {

    @Nullable
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view){
        this.view = view;
        User test = App.getInstance().getDatabase().userDao().getById("8");
        this.view.SetLogin(test.Login);
    }

    @Override
    public void setView(HomeContract.View view) {
        this.view = view;
    }
}
