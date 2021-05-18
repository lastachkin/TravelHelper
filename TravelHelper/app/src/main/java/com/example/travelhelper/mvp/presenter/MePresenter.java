package com.example.travelhelper.mvp.presenter;

import android.util.Log;

import com.example.travelhelper.mvp.contract.MeContract;
import com.example.travelhelper.mvp.repository.Repository;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.mvp.repository.model.UsersResponse;
import com.example.travelhelper.utils.Constants;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MePresenter implements MeContract.Presenter {
    private final MeContract.View view;
    private final Repository repository;

    public MePresenter(MeContract.View view){
        this.view = view;
        repository = new Repository();
    }

    @Override
    public void onEditButtonClicked() {
        if(view.isFieldsEnabled())
            view.setFieldsDisabled();
        else
            view.setFieldsEnabled();
    }

    @Override
    public void onSaveButtonClicked(Users user) {
        if(user.getFirstname().isEmpty() || user.getLastname().isEmpty() || user.getPhone().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty())
            view.onFieldsIsEmpty();
        else
            repository.updateUser(user.getId(), user).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.body() != null && response.body().contains("User updated")) {
                        view.onUserUpdated();
                        view.setFieldsDisabled();
                        onScreenLoaded();
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.e(Constants.appLog, t.getMessage());
                }
            });
    }

    @Override
    public void onScreenLoaded() {
        repository.getUserById(Constants.currentUser.getId()).enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.body() != null){
                    view.setFirstName(response.body().getFirstname());
                    Log.i(Constants.appLog, response.body().toString());
                    view.setLastName(response.body().getLastname());
                    view.setPhone(response.body().getPhone());
                    view.setEmail(response.body().getEmail());
                }
            }
            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                Log.e(Constants.appLog, t.getMessage());
            }
        });
    }
}
