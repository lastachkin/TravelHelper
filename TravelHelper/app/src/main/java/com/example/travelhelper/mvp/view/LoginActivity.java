package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityLoginBinding;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new LoginPresenter(this);

        binding.loginBtn.setOnClickListener(view -> presenter.loginButtonClicked(binding.login.getText().toString()));
        binding.registerBtn.setOnClickListener(view -> presenter.regButtonClicked());
    }

    @Override
    public void startHomePage() {
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void startRegistrationPage() {
        startActivity(new Intent(this, RegistrationActivity.class));
    }
}