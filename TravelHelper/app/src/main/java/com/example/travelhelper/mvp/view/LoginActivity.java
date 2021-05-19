package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityLoginBinding;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.presenter.LoginPresenter;
import com.example.travelhelper.utils.Constants;
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
    public void onUserFound(String role) {
        if(role.contains("A")){
            Constants.isAdmin = true;
            startActivity(new Intent(this, AdminActivity.class));
        }
        else if(role.contains("M")){
            Constants.isAdmin = false;
            startActivity(new Intent(this, ManagerActivity.class));
        }
        else{
            Constants.isAdmin = false;
            startActivity(new Intent(this, HomeActivity.class));
        }
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