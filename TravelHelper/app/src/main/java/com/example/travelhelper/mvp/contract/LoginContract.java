package com.example.travelhelper.mvp.contract;

public interface LoginContract {
    interface View{
        void onUserFound();
        void onUserNotFound();
        void startRegistrationPage();
    }
    interface Presenter {
        void loginButtonClicked(String login, String password);
        void regButtonClicked();
    }
}
