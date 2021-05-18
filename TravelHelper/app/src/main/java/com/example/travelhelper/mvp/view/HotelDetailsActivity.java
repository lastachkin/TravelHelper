package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import com.example.travelhelper.R;
import com.example.travelhelper.databinding.ActivityHotelDetailsBinding;
import com.example.travelhelper.mvp.contract.HotelDetailsContract;
import com.example.travelhelper.mvp.presenter.HotelDetailsPresenter;
import com.example.travelhelper.mvp.repository.model.Favorites;
import com.example.travelhelper.utils.Constants;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;

import java.util.UUID;

public class HotelDetailsActivity extends AppCompatActivity implements HotelDetailsContract.View {
    HotelDetailsPresenter presenter;
    ActivityHotelDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHotelDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HotelDetailsPresenter(this);

        Bundle bundle = getIntent().getExtras();
        String hotelId = (String) bundle.get("Id");
        String title = (String) bundle.get("Title");
        String city = (String) bundle.get("City");
        String address = (String) bundle.get("Address");
        String addressToDisplay = city + ", " + address;
        String description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nisl nisl, tempor eu rutrum ut, vulputate in metus. Vestibulum vel tellus laoreet, accumsan dui vel, efficitur justo. Proin dictum elementum leo, vitae gravida sapien tincidunt ac.";

        presenter.onScreenLoaded(hotelId, Constants.currentUser.getId());
        binding.title.setText(title);
        binding.address.setText(addressToDisplay);
        binding.description.setText(description);
        binding.description.setMovementMethod(new ScrollingMovementMethod());

        binding.reservBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, RoomSelectionActivity.class);
            intent.putExtra("HotelId", hotelId);
            startActivity(intent);
        });

        binding.favorite.setOnClickListener(v -> presenter.onFavoriteButtonClicked(new Favorites(UUID.randomUUID().toString(), Constants.currentUser.getId(), hotelId)));
    }

    @Override
    public void setHotelImageBitmap(Bitmap bitmap) {
        binding.hotelImage.setImageBitmap(bitmap);
    }

    @Override
    public void setHotelImageId(int id) {
        binding.hotelImage.setImageResource(id);
    }

    @Override
    public void onFavoriteHotelAdded() {
        binding.favorite.setImageResource(R.drawable.ic_favorite);
    }

    @Override
    public void onFavoriteHotelRemoved() {
        binding.favorite.setImageResource(R.drawable.ic_non_favorite);
    }
}