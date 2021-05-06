package com.example.travelhelper.mvp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.example.travelhelper.databinding.ActivityAdminBinding;
import com.example.travelhelper.mvp.contract.AdminContract;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AdminActivity extends AppCompatActivity implements AdminContract.View {
    ActivityAdminBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createHotelBtn.setOnClickListener(view -> startActivity(new Intent(this, CreateHotelActivity.class)));
    }
}