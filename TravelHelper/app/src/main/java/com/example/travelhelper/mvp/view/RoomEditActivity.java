package com.example.travelhelper.mvp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityRoomEditBinding;
import com.example.travelhelper.mvp.contract.RoomEditContract;
import com.example.travelhelper.mvp.presenter.RoomEditPresenter;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.utils.Extensions;

public class RoomEditActivity extends AppCompatActivity implements RoomEditContract.View {
    ActivityRoomEditBinding binding;
    RoomEditPresenter presenter;
    Uri imageUri;
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
        presenter.onScreenLoaded(id);

        binding.deleteBtn.setOnClickListener(v -> presenter.onDeleteButtonClicked(new Rooms(id, hotelId, Integer.parseInt(cost), Integer.parseInt(count), type)));
        binding.saveBtn.setOnClickListener(v ->
                presenter.onSaveButtonClicked(imageUri,
                                                new Rooms(id,
                                                          hotelId,
                                                          Integer.parseInt(binding.count.getText().toString()),
                                                          Integer.parseInt(binding.cost.getText().toString()),
                                                          binding.type.getText().toString())));

        binding.roomPic.setOnClickListener(view -> {
            if(binding.type.getText().toString().isEmpty())
                Extensions.errorToast("Заполните тип номера");
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
            binding.roomPic.setImageURI(imageUri);
        }
    }

    @Override
    public void setRoomImageBitmap(Bitmap bitmap) {
        binding.roomPic.setImageBitmap(bitmap);
    }

    @Override
    public void setRoomImageResource(int id) {
        binding.roomPic.setImageResource(id);
    }

    @Override
    public void onDeleteSuccess() {
        Extensions.successToast("Удалено");
        startActivity(new Intent(this, AdminActivity.class));
    }

    @Override
    public void onDeleteFailed() {
        Extensions.errorToast("Ошибка");
    }

    @Override
    public void onSaveSuccess() {
        Extensions.successToast("Сохранено");
    }

    @Override
    public void onSaveFailed() {
        Extensions.errorToast("Ошибка");
    }
}