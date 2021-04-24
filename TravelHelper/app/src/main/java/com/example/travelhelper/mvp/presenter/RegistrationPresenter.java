package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.App;
import com.example.travelhelper.mvp.contract.RegistrationContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RegistrationPresenter implements RegistrationContract.Presenter {

    private final RegistrationContract.View view;
    Repository repository;
    CompositeDisposable mDisposable;

    public RegistrationPresenter(RegistrationContract.View view){
        this.view = view;
        repository = new Repository();
        mDisposable = new CompositeDisposable();
    }

    @Override
    public void registrationButtonClicked(Users user) {
        assert view != null;
        if(user.Firstname.isEmpty() || user.Lastname.isEmpty() || user.Login.isEmpty() || user.Email.isEmpty() || user.Phone.isEmpty() || user.Password.isEmpty())
            view.onEmptyFields();
        else{
            mDisposable.add(repository.createUser(user)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {
                        view.onRegistrationSuccess();
                        Log.i(Constants.appLog, s);
                    }, throwable -> {
                        view.onRegistrationFailed();
                        Log.e(Constants.appLog, throwable.getMessage());
                    }));
            //Repository.insertUser(user);
        }
    }
}
