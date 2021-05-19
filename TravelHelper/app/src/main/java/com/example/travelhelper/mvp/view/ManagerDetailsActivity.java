package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.travelhelper.databinding.ActivityManagerDetailsBinding;
import com.example.travelhelper.mvp.contract.ManagerDetailsContract;
import com.example.travelhelper.mvp.presenter.ManagerDetailsPresenter;
import com.example.travelhelper.utils.Extensions;

public class ManagerDetailsActivity extends AppCompatActivity implements ManagerDetailsContract.View {
    ActivityManagerDetailsBinding binding;
    ManagerDetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityManagerDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        Bundle bundle = getIntent().getExtras();

        String reservationId = (String) bundle.get("Id");
        String roomId = (String) bundle.get("RoomId");
        String status = (String) bundle.get("Status");
        String startDate = (String) bundle.get("StartDate");
        String endDate = (String) bundle.get("EndDate");

        binding.startDate.setText(startDate);
        binding.endDate.setText(endDate);

        presenter = new ManagerDetailsPresenter(this);
        presenter.onScreenLoaded(reservationId);

        binding.removeBtn.setOnClickListener(v -> presenter.onRemoveButtonClicked(reservationId));
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
    public void onDeleteSuccess() {
        Extensions.successToast("Бронь отменена");
        startActivity(new Intent(this, ManagerActivity.class));
    }

    @Override
    public void onDeleteFailed() {
        Extensions.errorToast("Ошибка");
    }
}