package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;


public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    Repository repository;

    public LoginPresenter(LoginContract.View view){
        this.view = view;
        repository = new Repository();
    }

    @Override
    public void loginButtonClicked(String login, String password) {
        assert view != null;
        repository.searchUser(login, password)
                  .subscribeOn(Schedulers.io())
                  .observeOn(AndroidSchedulers.mainThread())
                  .subscribe(new DisposableSingleObserver<User>() {
                      @Override
                      public void onSuccess(User user) {
                          view.onUserFound();
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.e(Constants.appLog, e.getMessage());
                          view.onUserNotFound();
                      }
                  });
    }

    @Override
    public void regButtonClicked() {
        assert view != null;
        view.startRegistrationPage();
    }
}
