package com.example.travelhelper.mvp.contract;

import com.example.travelhelper.mvp.repository.model.User;

public interface RegistrationContract {
    interface View{
        void onRegistrationSuccess();
        void onRegistrationFailed();
    }

    interface Presenter {
        void registrationButtonClicked(User user);
    }
}
