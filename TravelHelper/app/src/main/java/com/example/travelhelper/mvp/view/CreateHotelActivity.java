package com.example.travelhelper.mvp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityCreateHotelBinding;
import com.example.travelhelper.mvp.contract.CreateHotelContract;
import com.example.travelhelper.mvp.presenter.CreateHotelPresenter;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Extensions;

import java.util.UUID;

public class CreateHotelActivity extends AppCompatActivity implements CreateHotelContract.View {
    ActivityCreateHotelBinding binding;
    CreateHotelPresenter presenter;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new CreateHotelPresenter(this);
        binding =  ActivityCreateHotelBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createHotelBtn.setOnClickListener(view -> {
            presenter.onCreateHotelButtonClicked(
                    imageUri,
                    binding.title.getText().toString() + "_" + binding.city.getText().toString(),
                    new Hotels(UUID.randomUUID().toString(), binding.title.getText().toString(), binding.city.getText().toString(), binding.address.getText().toString()));
        });

        binding.hotelPic.setOnClickListener(view -> {
            if(binding.title.getText().toString().isEmpty() || binding.city.getText().toString().isEmpty())
                Extensions.errorToast("Заполните название и город");
            else{
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            binding.hotelPic.setImageURI(imageUri);
        }
    }

    @Override
    public void onCreationSuccess() {
        Extensions.successToast("Отель добавлен");
        startActivity(new Intent(this, AdminActivity.class));
    }

    @Override
    public void onCreationFailed() {
        Extensions.errorToast("Ошибка");
    }
}