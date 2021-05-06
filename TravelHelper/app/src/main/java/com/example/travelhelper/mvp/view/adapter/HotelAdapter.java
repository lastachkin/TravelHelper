package com.example.travelhelper.mvp.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.repository.model.Hotels;

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
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HotelAdapter.ViewHolder holder, int position) {
        Hotels hotel = hotels.get(position);
        holder.hotelImageView.setImageResource(R.drawable.camera_lens);//set image from firebase
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

            hotelImageView = (AppCompatImageView) view.findViewById(R.id.hotelImage);
            nameView = (AppCompatTextView) view.findViewById(R.id.title);
            addressView = (AppCompatTextView) view.findViewById(R.id.address);
        }
    }
}
