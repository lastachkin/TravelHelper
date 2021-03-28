package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.contract.LoginContract;
import com.example.travelhelper.mvp.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {

    private EditText login;
    private EditText password;
    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login = findViewById(R.id.login);
        password = findViewById(R.id.password);
        presenter = new LoginPresenter(this);

        Button login_btn = findViewById(R.id.login_btn);
        Button register_btn = findViewById(R.id.register_btn);
        login_btn.setOnClickListener(view -> presenter.loginButtonClicked());
        register_btn.setOnClickListener(view -> presenter.regButtonClicked());
    }

    @Override
    public String getLogin() {
        return login.getText().toString();
    }

    @Override
    public String getPassword() {
        return password.getText().toString();
    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "Input error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void startHomeActivity() {
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(intent);
    }
}