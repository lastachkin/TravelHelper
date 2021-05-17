package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityReservationDetailsBinding;
import com.example.travelhelper.mvp.contract.ReservationDetailsContract;
import com.example.travelhelper.mvp.presenter.ReservationDetailsPresenter;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Extensions;

public class ReservationDetailsActivity extends AppCompatActivity implements ReservationDetailsContract.View {
    ActivityReservationDetailsBinding binding;
    ReservationDetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        String reservationId = (String) bundle.get("Id");
        String roomId = (String) bundle.get("RoomId");

        presenter = new ReservationDetailsPresenter(this);
        presenter.onScreenLoaded(roomId);
        binding.deleteBtn.setOnClickListener(v -> presenter.onDeleteButtonClicked(reservationId));
    }

    @Override
    public void setHotelImageBitmap(Bitmap bitmap) {
        binding.roomImage.setImageBitmap(bitmap);
    }

    @Override
    public void setHotelImageId(int id) {
        binding.roomImage.setImageResource(id);
    }

    @Override
    public void onDeleteSuccess() {
        Extensions.successToast("Удалено");
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onDeleteFailed() {
        Extensions.errorToast("Ошибка");
    }
}