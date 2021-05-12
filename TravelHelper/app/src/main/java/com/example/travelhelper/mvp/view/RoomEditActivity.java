package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.travelhelper.databinding.ActivityRoomEditBinding;
import com.example.travelhelper.mvp.contract.RoomEditContract;
import com.example.travelhelper.mvp.presenter.RoomEditPresenter;
import com.example.travelhelper.utils.Constants;

public class RoomEditActivity extends AppCompatActivity implements RoomEditContract.View {
    ActivityRoomEditBinding binding;
    RoomEditPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRoomEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new RoomEditPresenter(this);

        Bundle bundle = getIntent().getExtras();
        String id = (String) bundle.get("Id");
        String hotelId = (String) bundle.get("HotelId");
        String cost = (String) bundle.get("HotelId");
        String count = (String) bundle.get("Count");
        String type = (String) bundle.get("Type");

        Log.i(Constants.appLog, id + cost);
    }
}