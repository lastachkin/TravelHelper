package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.utils.Constants;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void onLoginButtonClicked(String login, String password) {
        assert view != null;
        mDisposable.add(repository.searchUser(login, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s -> {
                    Log.i(Constants.appLog, s);
                    if(s.contains("Not found"))
                        view.onUserNotFound();
                    else if (s.contains("Incorrect password"))
                        view.onUserIncorrectPass();
                    else
                        view.onUserFound();
                }, throwable -> {
                    Log.e(Constants.appLog, throwable.getMessage());
                }));
    }
}
