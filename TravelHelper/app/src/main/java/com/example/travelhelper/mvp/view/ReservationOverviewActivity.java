package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityReservationOverviewBinding;
import com.example.travelhelper.mvp.contract.ReservationOverviewContract;
import com.example.travelhelper.mvp.presenter.ReservationOverviewPresenter;

public class ReservationOverviewActivity extends AppCompatActivity implements ReservationOverviewContract.View {
    ActivityReservationOverviewBinding binding;
    ReservationOverviewPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReservationOverviewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new ReservationOverviewPresenter(this);
        Bundle bundle = getIntent().getExtras();

        String reservationId = (String) bundle.get("Id");
        String roomId = (String) bundle.get("RoomId");
        String status = (String) bundle.get("Status");
        String userId = (String) bundle.get("UserId");
        String startDate = (String) bundle.get("StartDate");
        String endDate = (String) bundle.get("EndDate");

        binding.startDate.setText(startDate);
        binding.endDate.setText(endDate);

        presenter.onScreenLoaded(reservationId);
    }

    @Override
    public void setCost(String cost) {
        binding.cost.setText(cost);
    }

    @Override
    public void setType(String type) {
        binding.type.setText(type);
    }

    @Override
    public void setName(String name) {
        binding.clientName.setText(name);
    }

    @Override
    public void setComment(String comment) {
        binding.comment.setText(comment);
    }
}