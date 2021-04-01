package com.example.travelhelper.mvp.view;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.HomeContract;
import com.example.travelhelper.mvp.presenter.HomePresenter;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {
    private HomePresenter presenter;
    private TextView username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        username = findViewById(R.id.username);
        presenter = new HomePresenter(this);
        presenter.onGetLoginRequest();
    }

    @Override
    public void SetLogin(String login) {
        username.setText(login);
    }
}
