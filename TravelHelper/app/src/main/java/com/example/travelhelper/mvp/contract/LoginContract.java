package com.example.travelhelper.mvp.contract;

public interface LoginContract {
    interface View{
        String getLogin();
        String getPassword();
        void showInputError();
        void startHomeActivity();
    }
    interface Presenter {
        void setView(View view);
        void loginButtonClicked();
        void regButtonClicked();
        void getCurrentUser();
    }
}
