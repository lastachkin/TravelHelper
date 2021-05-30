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

import com.example.travelhelper.App;
import com.example.travelhelper.R;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.mvp.repository.model.ReservationsResponse;
import com.example.travelhelper.mvp.view.ManagerDetailsActivity;
import com.example.travelhelper.mvp.view.ReservationDetailsActivity;
import com.example.travelhelper.mvp.view.ReservationOverviewActivity;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
                        if(Constants.currentUser.getRole().contains("M")){
                            if(reservation.getStatus().equals("A"))
                                holder.hotelImageView.setImageResource(R.drawable.p);
                            if(reservation.getStatus().equals("O"))
                                holder.hotelImageView.setImageResource(R.drawable.o);
                        }
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
            //statusView = view.findViewById(R.id.status);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            if(Constants.currentUser.getRole().contains("U"))
                intent = new Intent(v.getContext(), ReservationDetailsActivity.class);
            else if(reservations.get(getLayoutPosition()).getStatus().equals("O"))
                intent = new Intent(v.getContext(), ManagerDetailsActivity.class);
            else
                intent = new Intent(v.getContext(), ReservationOverviewActivity.class);

            intent.putExtra("Id", reservations.get(getLayoutPosition()).getId());
            intent.putExtra("RoomId", reservations.get(getLayoutPosition()).getRoomId());
            intent.putExtra("UserId", reservations.get(getLayoutPosition()).getUserId());
            intent.putExtra("Status", reservations.get(getLayoutPosition()).getStatus());
            intent.putExtra("StartDate", reservations.get(getLayoutPosition()).getStartDate().getDate() + "." + (reservations.get(getLayoutPosition()).getStartDate().getMonth() + 1) + "." + (reservations.get(getLayoutPosition()).getStartDate().getYear() + 1900));
            intent.putExtra("EndDate", reservations.get(getLayoutPosition()).getEndDate().getDate() + "." + (reservations.get(getLayoutPosition()).getEndDate().getMonth() + 1) + "." + (reservations.get(getLayoutPosition()).getEndDate().getYear() + 1900));
            intent.putExtra("StartDate_Year", (reservations.get(getLayoutPosition()).getStartDate().getYear() + 1900));
            intent.putExtra("StartDate_Month", (reservations.get(getLayoutPosition()).getStartDate().getMonth() + 1));
            intent.putExtra("StartDate_Day", reservations.get(getLayoutPosition()).getStartDate().getDate());
            intent.putExtra("EndDate_Year", (reservations.get(getLayoutPosition()).getEndDate().getYear() + 1900));
            intent.putExtra("EndDate_Month", (reservations.get(getLayoutPosition()).getEndDate().getMonth() + 1));
            intent.putExtra("EndDate_Day", reservations.get(getLayoutPosition()).getEndDate().getDate());
            v.getContext().startActivity(intent);
        }
    }
}
