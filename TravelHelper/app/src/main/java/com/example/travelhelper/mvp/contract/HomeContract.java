package com.example.travelhelper.mvp.contract;

public interface HomeContract {
    interface View{
        void setLogin(String login);
    }
    interface Presenter {
        void onGetLoginRequest();
    }
}
