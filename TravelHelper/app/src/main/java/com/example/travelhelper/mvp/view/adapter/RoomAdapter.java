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
import com.example.travelhelper.mvp.presenter.CreateReservationPresenter;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.repository.model.Rooms;
import com.example.travelhelper.mvp.view.CreateReservationActivity;
import com.example.travelhelper.mvp.view.RoomEditActivity;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomAdapter.ViewHolder>{
    private static List<Rooms> rooms;

    public RoomAdapter(List<Rooms> rooms) {
        RoomAdapter.rooms = rooms;
    }

    @NonNull
    @Override
    public RoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_room_item,parent,false);
        view.findViewById(R.id.roomImage).setClipToOutline(true);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomAdapter.ViewHolder holder, int position) {
        Rooms room = rooms.get(position);
        try{
            final File localFile = File.createTempFile("tmp", "jpg");
            FirebaseStorage.getInstance().getReference().child("rooms/" + room.getId()).getFile(localFile)
                    .addOnSuccessListener(taskSnapshot -> {
                        Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                        holder.roomImageView.setImageBitmap(bitmap);
                    })
                    .addOnFailureListener(e -> {
                        Log.e(Constants.appLog, e.getMessage());
                        holder.roomImageView.setImageResource(R.drawable.camera_lens);
                    });
        }
        catch (IOException e){
            e.printStackTrace();
        }

        holder.typeView.setText(room.getType());
        String costToShow = room.getCost() + "$";
        holder.costView.setText(costToShow);
    }

    @Override
    public int getItemCount() {
        return rooms.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final AppCompatImageView roomImageView;
        final AppCompatTextView typeView, costView;

        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            roomImageView = view.findViewById(R.id.roomImage);
            typeView = view.findViewById(R.id.type);
            costView = view.findViewById(R.id.cost);
        }

        @Override
        public void onClick(View v) {
            Intent intent;
            if(!Constants.isAdmin)
                intent = new Intent(v.getContext(), CreateReservationActivity.class);
            else
                intent = new Intent(v.getContext(), RoomEditActivity.class);

            intent.putExtra("Id", rooms.get(getLayoutPosition()).getId());
            intent.putExtra("HotelId", rooms.get(getLayoutPosition()).getHotelId());
            intent.putExtra("Type", rooms.get(getLayoutPosition()).getType());
            intent.putExtra("Cost", String.valueOf(rooms.get(getLayoutPosition()).getCost()));
            intent.putExtra("Count", String.valueOf(rooms.get(getLayoutPosition()).getCount()));
            v.getContext().startActivity(intent);
        }
    }
}
