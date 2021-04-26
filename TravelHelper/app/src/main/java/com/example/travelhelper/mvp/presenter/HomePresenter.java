package com.example.travelhelper.mvp.presenter;

import androidx.annotation.Nullable;

import com.example.travelhelper.mvp.contract.HomeContract;

public class HomePresenter implements HomeContract.Presenter {
    @Nullable
    private HomeContract.View view;

    public HomePresenter(HomeContract.View view){
        this.view = view;
    }

    @Override
    public void initLoginField() {
//        App.getInstance().getDatabase().userDao().getById(1)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DisposableMaybeObserver<User>() {
//                    @Override
//                    public void onSuccess(User user) {
//                        view.setLogin(user.Login);
//                        Log.i("LOG", user.Login);
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        Log.i("LOG", e.getMessage());
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        Log.i("LOG", "No record");
//                    }
//                });
    }
}
