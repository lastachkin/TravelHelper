package com.example.travelhelper.mvp.presenter;



import androidx.annotation.Nullable;

import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.utils.Extensions;


public class LoginPresenter implements LoginContract.Presenter {

    @Nullable
    private LoginContract.View view;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
    }

    @Override
    public void loginButtonClicked(String login) {
        if (Repository.login(login))
            view.startHomePage();
        else
            Extensions.errorToast("Пользователь не существует");
    }

    @Override
    public void regButtonClicked() {
        view.startRegistrationPage();
    }

    @Override
    public void getCurrentUser() {

    }
}
