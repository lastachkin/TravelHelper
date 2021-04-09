package com.example.travelhelper.mvp.presenter;

import com.example.travelhelper.mvp.contract.RegistrationContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.User;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private final RegistrationContract.View view;

    public RegistrationPresenter(RegistrationContract.View view){
        this.view = view;
    }

    @Override
    public void registrationButtonClicked(User user) {
        if(user.Firstname.isEmpty() || user.Lastname.isEmpty() || user.Login.isEmpty() || user.Email.isEmpty() || user.Phone.isEmpty() || user.Password.isEmpty())
            view.onRegistrationFailed();
        else{
            Repository.insertUser(user);
            view.onRegistrationSuccess();
        }
    }
}
