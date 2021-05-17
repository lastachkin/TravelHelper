package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import androidx.annotation.Nullable;

import com.example.travelhelper.mvp.contract.MeContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Constants;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MePresenter implements MeContract.Presenter {
    @Nullable
    private final MeContract.View view;
    private final Repository repository;
    private final CompositeDisposable mDisposable = new CompositeDisposable();

    public MePresenter(@Nullable MeContract.View view){
        this.view = view;
        repository = new Repository();
    }

    @Override
    public void onEditButtonClicked() {
        if (view != null) {
            if(view.isFieldsEnabled())
                view.setFieldsDisabled();
            else
                view.setFieldsEnabled();
        }
    }

    @Override
    public void onSaveButtonClicked(String password) {
        //update in db
//        mDisposable.add(repository.updateUser(new User(Constants.currentUser.Firstname, Constants.currentUser.Lastname, Constants.currentUser.Email, Constants.currentUser.Phone, Constants.currentUser.Login, password))
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(view::onUserUpdated,
//                        throwable -> Log.e(Constants.appLog, throwable.getMessage())));
        //onScreenLoaded();

    }

    @Override
    public void onScreenLoaded() {
        repository.getUserById(Constants.currentUser.getId()).enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if (response.body() != null){
                    view.setEmail(response.body().getEmail());
                }
            }
            @Override
            public void onFailure(Call<Users> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });
    }
}
