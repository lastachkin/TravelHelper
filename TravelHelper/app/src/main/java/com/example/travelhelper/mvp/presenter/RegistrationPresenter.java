package com.example.travelhelper.mvp.presenter;

import com.example.travelhelper.mvp.contract.RegistrationContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.User;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private RegistrationContract.View view;

    public RegistrationPresenter(RegistrationContract.View view){
        this.view = view;
    }

    @Override
    public void registrationButtonClicked(User user) {
        Repository.insertUser(user);
        view.onRegistrationSuccess();
    }
}
