package com.example.travelhelper.mvp.view;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import com.example.travelhelper.R;
import com.example.travelhelper.databinding.ActivityAdminBinding;
import com.example.travelhelper.mvp.contract.AdminContract;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class AdminActivity extends AppCompatActivity implements AdminContract.View {
    FirebaseStorage storage;
    StorageReference storageRef;
    ActivityAdminBinding binding;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.hotelPic.setOnClickListener(view -> onImageClicked());
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();
        downloadPic();
    }

    @Override
    public void onImageClicked() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null){
            imageUri = data.getData();
            binding.hotelPic.setImageURI(imageUri);
            uploadPic();
        }
    }

    private void uploadPic() {
        final String randomKey = UUID.randomUUID().toString();

        StorageReference imageRef = storageRef.child("hotels/" + randomKey);

        imageRef.putFile(imageUri)
                .addOnSuccessListener(taskSnapshot -> Extensions.successToast("Загружено"))
                .addOnFailureListener(e -> Extensions.errorToast("Ошибка загрузки"));
    }

    private void downloadPic() {
        try{
            final File localFile = File.createTempFile("minsk", "jpg");
            storageRef.child("hotels/minsk.jpg").getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        binding.hotelPic.setImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> Extensions.errorToast("Ошибка загрузки"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}