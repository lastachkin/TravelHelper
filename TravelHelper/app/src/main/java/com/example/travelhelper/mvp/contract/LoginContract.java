package com.example.travelhelper.mvp.contract;

public interface LoginContract {
    interface View{
        void startHomePage();
        void startRegistrationPage();
    }
    interface Presenter {
        void loginButtonClicked(String login);
        void regButtonClicked();
        void getCurrentUser();
    }
}
