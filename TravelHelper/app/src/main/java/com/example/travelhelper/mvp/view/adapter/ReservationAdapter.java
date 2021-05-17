package com.example.travelhelper.mvp.view.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travelhelper.R;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.view.ReservationDetailsActivity;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder>{
    private static List<Reservations> reservations;

    public ReservationAdapter(List<Reservations> reservations) {
        ReservationAdapter.reservations = reservations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_hotel_item,parent,false);
        view.findViewById(R.id.hotelImage).setClipToOutline(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Reservations reservation = reservations.get(position);
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("rooms/" + reservation.getRoomId()).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        holder.hotelImageView.setImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        holder.hotelImageView.setImageResource(R.drawable.camera_lens);
                    });
        }
        catch (IOException e){
            e.printStackTrace();
        }
        holder.nameView.setText(reservation.getStartDate().getDate() + "." + (reservation.getStartDate().getMonth() + 1) + "." + (reservation.getStartDate().getYear() + 1900));
        holder.addressView.setText(reservation.getEndDate().getDate() + "." + (reservation.getEndDate().getMonth() + 1) + "." + (reservation.getEndDate().getYear() + 1900));
    }

    @Override
    public int getItemCount() {
        return reservations.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final AppCompatImageView hotelImageView;
        final AppCompatTextView nameView, addressView;
        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            hotelImageView = view.findViewById(R.id.hotelImage);
            nameView = view.findViewById(R.id.title);
            addressView = view.findViewById(R.id.address);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            //if(!Constants.isAdmin)
                intent = new Intent(v.getContext(), ReservationDetailsActivity.class);
            ///else
                //intent = new Intent(v.getContext(), RoomEditActivity.class);

            intent.putExtra("Id", reservations.get(getLayoutPosition()).getId());
            intent.putExtra("RoomId", reservations.get(getLayoutPosition()).getRoomId());
            intent.putExtra("UserId", reservations.get(getLayoutPosition()).getUserId());
            intent.putExtra("StartDate", String.valueOf(reservations.get(getLayoutPosition()).getStartDate()));
            intent.putExtra("EndDate", String.valueOf(reservations.get(getLayoutPosition()).getEndDate()));
            v.getContext().startActivity(intent);
        }
    }
}
