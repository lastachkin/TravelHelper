package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.travelhelper.databinding.ActivityManagerDetailsBinding;
import com.example.travelhelper.mvp.contract.ManagerDetailsContract;
import com.example.travelhelper.mvp.presenter.ManagerDetailsPresenter;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ManagerDetailsActivity extends AppCompatActivity implements ManagerDetailsContract.View {
    ActivityManagerDetailsBinding binding;
    ManagerDetailsPresenter presenter;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
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
        String userId = (String) bundle.get("UserId");
        String startDate = (String) bundle.get("StartDate");
        String endDate = (String) bundle.get("EndDate");
        int startDateYear = (int) bundle.get("StartDate_Year");
        int startDateMonth = (int) bundle.get("StartDate_Month");
        int startDateDay = (int) bundle.get("StartDate_Day");
        int endDateYear = (int) bundle.get("EndDate_Year");
        int endDateMonth = (int) bundle.get("EndDate_Month");
        int endDateDay = (int) bundle.get("EndDate_Day");

        binding.startDate.setText(startDate);
        binding.endDate.setText(endDate);

        presenter = new ManagerDetailsPresenter(this);
        presenter.onScreenLoaded(reservationId);

        binding.rejectBtn.setOnClickListener(v -> presenter.onRejectButtonClicked(reservationId, new Reservations(reservationId, userId, roomId, "C", getDate(startDateYear + "-" + startDateMonth + "-" + startDateDay), getDate(endDateYear + "-" + endDateMonth + "-" + endDateDay), binding.comment.getText().toString())));
        binding.confirmBtn.setOnClickListener(v -> presenter.onConfirmButtonClicked(reservationId, new Reservations(reservationId, userId, roomId, "A", getDate(startDateYear + "-" + startDateMonth + "-" + startDateDay), getDate(endDateYear + "-" + endDateMonth + "-" + endDateDay), binding.comment.getText().toString())));
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
    public void onConfirmSuccess() {
        Extensions.successToast("Бронь подтверждена");
        startActivity(new Intent(this, ManagerActivity.class));
    }

    @Override
    public void onConfirmFailed() {
        Extensions.errorToast("Ошибка");
    }

    @Override
    public void onRejectSuccess() {
        Extensions.successToast("Бронь отменена");
        startActivity(new Intent(this, ManagerActivity.class));
    }

    @Override
    public void onRejectFailed() {
        Extensions.errorToast("Ошибка");
    }

    Date getDate(String date){
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            Log.e(Constants.appLog, e.getMessage());
        }
        return null;
    }
}