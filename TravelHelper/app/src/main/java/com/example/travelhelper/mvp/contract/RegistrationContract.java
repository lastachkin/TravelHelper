package com.example.travelhelper.mvp.contract;

import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.mvp.repository.model.Users;

public interface RegistrationContract {
    interface View{
        void onRegistrationSuccess();
        void onRegistrationFailed();
        void onEmptyFields();
    }

    interface Presenter {
        void onRegistrationButtonClicked(Users user);
    }
}
