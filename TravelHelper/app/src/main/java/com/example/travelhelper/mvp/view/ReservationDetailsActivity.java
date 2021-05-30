package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.travelhelper.App;
import com.example.travelhelper.databinding.ActivityReservationDetailsBinding;
import com.example.travelhelper.mvp.contract.ReservationDetailsContract;
import com.example.travelhelper.mvp.presenter.ReservationDetailsPresenter;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        String status = (String) bundle.get("Status");

        if(status.equals("O")){
            binding.status.setText("Бронь не подтверждена");
            binding.comment.setVisibility(View.INVISIBLE);
        }
        else if(status.equals("A")){
            binding.status.setText("Бронь подтверждена");
            binding.comment.setVisibility(View.VISIBLE);
            binding.deleteBtn.setVisibility(View.INVISIBLE);
        }
        else{
            binding.status.setText("Бронь отклонена");
            binding.comment.setVisibility(View.VISIBLE);
            binding.deleteBtn.setVisibility(View.INVISIBLE);
        }

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

    @Override
    public void setType(String type) {
        binding.type.setText(type);
    }

    @Override
    public void setCost(String cost) {
        binding.cost.setText(cost);
    }

    @Override
    public void setCity(String city) {
        binding.city.setText(city);
    }

    @Override
    public void setAddress(String address) {
        binding.address.setText(address);
    }
}