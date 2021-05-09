package com.example.travelhelper.mvp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.example.travelhelper.databinding.ActivityCreateRoomBinding;
import com.example.travelhelper.mvp.contract.CreateRoomContract;
import com.example.travelhelper.mvp.presenter.CreateRoomPresenter;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import java.util.UUID;

public class CreateRoomActivity extends AppCompatActivity implements CreateRoomContract.View {
    ActivityCreateRoomBinding binding;
    CreateRoomPresenter presenter;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new CreateRoomPresenter(this);
        Bundle bundle = getIntent().getExtras();
        String hotelId = (String) bundle.get("hotelId");

        binding.createRoomBtn.setOnClickListener(v -> {
            presenter.onCreateButtonClicked(
                    imageUri,
                    new Rooms(UUID.randomUUID().toString(),
                              hotelId,
                              Integer.parseInt(binding.count.getText().toString()),
                              Integer.parseInt(binding.cost.getText().toString()),
                              binding.type.getText().toString())
            );
        });

        binding.roomPic.setOnClickListener(view -> {
            if(binding.type.getText().toString().isEmpty())
                Extensions.errorToast("Заполните тип");
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
    public void onCreationSuccess() {
        Extensions.successToast("Номер добавлен");
        startActivity(new Intent(this, AdminActivity.class));
    }

    @Override
    public void onCreationFailed() {
        Extensions.errorToast("Ошибка");
    }
}