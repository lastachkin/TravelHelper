package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityRoomEditBinding;
import com.example.travelhelper.mvp.contract.RoomEditContract;
import com.example.travelhelper.mvp.presenter.RoomEditPresenter;

public class RoomEditActivity extends AppCompatActivity implements RoomEditContract.View {
    ActivityRoomEditBinding binding;
    RoomEditPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new RoomEditPresenter(this);
    }
}