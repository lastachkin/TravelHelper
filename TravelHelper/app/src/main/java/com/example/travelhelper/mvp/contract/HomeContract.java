package com.example.travelhelper.mvp.contract;

public interface HomeContract {
    interface View{
        void SetLogin(String login);
    }
    interface Presenter {
        void setView(HomeContract.View view);
    }
}
