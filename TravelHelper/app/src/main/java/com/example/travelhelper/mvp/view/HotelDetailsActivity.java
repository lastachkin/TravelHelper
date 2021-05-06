package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;
import com.example.travelhelper.R;
import com.example.travelhelper.databinding.ActivityHotelDetailsBinding;
import com.example.travelhelper.mvp.contract.HotelDetailsContract;
import com.example.travelhelper.mvp.presenter.HotelDetailsPresenter;
import com.example.travelhelper.utils.Constants;

import android.os.Bundle;
import android.util.Log;

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
        if(bundle != null){
            String title = (String) bundle.get("Title");
            Log.i(Constants.appLog, title);
        }
    }
}