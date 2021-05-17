package com.example.travelhelper.mvp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;

import com.example.travelhelper.databinding.ActivityHotelEditBinding;
import com.example.travelhelper.mvp.contract.HotelEditContract;
import com.example.travelhelper.mvp.presenter.HotelEditPresenter;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

public class HotelEditActivity extends AppCompatActivity implements HotelEditContract.View {
    ActivityHotelEditBinding binding;
    HotelEditPresenter presenter;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new HotelEditPresenter(this);

        Bundle bundle = getIntent().getExtras();
        String id = (String) bundle.get("Id");
        String title = (String) bundle.get("Title");
        String city = (String) bundle.get("City");
        String address = (String) bundle.get("Address");
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nisl nisl, tempor eu rutrum ut, vulputate in metus. Vestibulum vel tellus laoreet, accumsan dui vel, efficitur justo. Proin dictum elementum leo, vitae gravida sapien tincidunt ac.";

        presenter.onScreenLoaded(id);
        binding.title.setText(title);
        binding.city.setText(city);
        binding.address.setText(address);
        binding.description.setText(description);
        binding.description.setMovementMethod(new ScrollingMovementMethod());

        binding.saveBtn.setOnClickListener(v -> presenter.onSaveButtonClicked(imageUri, new Hotels(id, binding.title.getText().toString(), binding.city.getText().toString(), binding.address.getText().toString())));
        binding.deleteBtn.setOnClickListener(v -> presenter.onDeleteButtonClicked(new Hotels(id, title, city, address)));
        binding.addRoomBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomOverviewActivity.class);
            intent.putExtra("hotelId", id);
            startActivity(intent);
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
    public void onSaveSuccess() {
        Extensions.successToast("Сохранено");
        startActivity(new Intent(this, AdminActivity.class));
    }

    @Override
    public void onSaveFailed() {
        Extensions.errorToast("Не удалось сохранить");
    }

    @Override
    public void onDeleteSuccess() {
        Extensions.successToast("Отель удален");
        startActivity(new Intent(this, AdminActivity.class));
    }

    @Override
    public void onDeleteFailed() {
        Extensions.errorToast("Не удалось удалить");
    }

    @Override
    public void setHotelImageBitmap(Bitmap bitmap) {
        binding.hotelPic.setImageBitmap(bitmap);
    }

    @Override
    public void setHotelImageId(int id) {
        binding.hotelPic.setImageResource(id);
    }
}