package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
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

        Bundle bundle = getIntent().getExtras();
        String id = (String) bundle.get("Id");
        String hotelId = (String) bundle.get("HotelId");
        String cost = (String) bundle.get("Cost");
        String count = (String) bundle.get("Count");
        String type = (String) bundle.get("Type");

        binding.cost.setText(cost);
        binding.count.setText(count);
        binding.type.setText(type);
        presenter.onScreenLoaded(hotelId, type);
    }

    @Override
    public void setRoomImageBitmap(Bitmap bitmap) {
        binding.roomPic.setImageBitmap(bitmap);
    }

    @Override
    public void setRoomImageResource(int id) {
        binding.roomPic.setImageResource(id);
    }
}