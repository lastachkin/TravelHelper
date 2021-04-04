package com.example.travelhelper.mvp.contract;

public interface LoginContract {
    interface View{
        void showInputError();
        void startHomeActivity();
    }
    interface Presenter {
        void loginButtonClicked(String login, String pass);
        void regButtonClicked();
        void getCurrentUser();
    }
}
