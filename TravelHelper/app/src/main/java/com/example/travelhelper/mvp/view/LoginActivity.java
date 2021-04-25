package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityLoginBinding;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.presenter.LoginPresenter;
import com.example.travelhelper.mvp.repository.model.User;
import com.example.travelhelper.utils.Extensions;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private LoginPresenter presenter;
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new LoginPresenter(this);

        binding.loginBtn.setOnClickListener(view -> presenter.onLoginButtonClicked(binding.login.getText().toString(), binding.password.getText().toString()));
        binding.registerBtn.setOnClickListener(view -> startActivity(new Intent(this, RegistrationActivity.class)));
    }

    @Override
    public void onUserFound() {
        // TODO: 4/21/2021 implement logic to define admin 
        if(binding.login.getText().toString().equals("admin"))
            startActivity(new Intent(this, AdminActivity.class));
        else
            startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onUserIncorrectPass() {
        Extensions.errorToast("Неверный пароль");
    }

    @Override
    public void onUserNotFound() {
        Extensions.errorToast("Пользователь не существует");
    }
}