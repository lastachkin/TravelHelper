package com.example.travelhelper.mvp.view.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder>{
    private final List<Hotels> hotels;

    public HotelAdapter(List<Hotels> hotels) {
        this.hotels = hotels;
    }

    @NonNull
    @Override
    public HotelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_item,parent,false);
        view.findViewById(R.id.hotelImage).setClipToOutline(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.ViewHolder holder, int position) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageRef = storage.getReference();
        // TODO: 06.05.2021 Move logic to presenter 
        Hotels hotel = hotels.get(position);
        try{
            final File localFile = File.createTempFile("minsk13", "jpg");
            storageRef.child("hotels/"+ hotel.getTitle() + "_" + hotel.getCity()).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        holder.hotelImageView.setImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> Extensions.errorToast("Ошибка загрузки"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
        holder.nameView.setText(hotel.getTitle());
        holder.addressView.setText(hotel.getCity());
    }

    @Override
    public int getItemCount() {
        return hotels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final AppCompatImageView hotelImageView;
        final AppCompatTextView nameView, addressView;
        ViewHolder(View view){
            super(view);

            hotelImageView = view.findViewById(R.id.hotelImage);
            nameView = view.findViewById(R.id.title);
            addressView = view.findViewById(R.id.address);
        }
    }
}
