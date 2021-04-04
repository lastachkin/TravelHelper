package com.example.travelhelper.mvp.view;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.travelhelper.databinding.ActivityHomeBinding;
import com.example.travelhelper.mvp.contract.HomeContract;
import com.example.travelhelper.mvp.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private HomePresenter presenter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HomePresenter(this);
        presenter.onGetLoginRequest();
    }

    @Override
    public void setLogin(String login) {
        binding.username.setText(login);
    }
}
