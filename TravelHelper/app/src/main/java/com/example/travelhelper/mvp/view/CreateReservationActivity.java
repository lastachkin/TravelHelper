package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityCreateReservationBinding;
import com.example.travelhelper.mvp.contract.CreateReservationContract;
import com.example.travelhelper.mvp.presenter.CreateReservationPresenter;

public class CreateReservationActivity extends AppCompatActivity implements CreateReservationContract.View {
    ActivityCreateReservationBinding binding;
    CreateReservationPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CreateReservationPresenter(this);
    }
}