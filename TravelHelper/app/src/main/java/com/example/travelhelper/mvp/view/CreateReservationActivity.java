package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import com.example.travelhelper.databinding.ActivityCreateReservationBinding;
import com.example.travelhelper.mvp.contract.CreateReservationContract;
import com.example.travelhelper.mvp.presenter.CreateReservationPresenter;
import com.example.travelhelper.mvp.repository.model.Reservations;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class CreateReservationActivity extends AppCompatActivity implements CreateReservationContract.View {
    ActivityCreateReservationBinding binding;
    CreateReservationPresenter presenter;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    int startDateYear = Calendar.getInstance().get(Calendar.YEAR);
    int startDateMonth = Calendar.getInstance().get(Calendar.MONTH);
    int startDateDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    int endDateYear = Calendar.getInstance().get(Calendar.YEAR);
    int endDateMonth = Calendar.getInstance().get(Calendar.MONTH);
    int endDateDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle bundle = getIntent().getExtras();
        String roomId = (String) bundle.get("Id");
        String hotelId = (String) bundle.get("HotelId");
        String type = (String) bundle.get("Type");
        String cost = (String) bundle.get("Cost") + "$";

        presenter = new CreateReservationPresenter(this);
        presenter.onScreenLoaded(hotelId, type);
        binding.type.setText(type);
        binding.cost.setText(cost);
        binding.startDate.setOnClickListener(v -> showDialog(1));
        binding.endDate.setOnClickListener(v -> showDialog(2));
        binding.reservBtn.setOnClickListener(v -> {
            if(binding.startDate.getText().length() != 0 && binding.endDate.getText().length() != 0)
                presenter.onReserveButtonClicked(new Reservations(UUID.randomUUID().toString(),
                        Constants.currentUser.getId(),
                        roomId,
                        "O",
                        getDate(startDateYear + "-" + startDateMonth + "-" + startDateDay),
                        getDate(endDateYear + "-" + endDateMonth + "-" + endDateDay)));
            else
                Extensions.errorToast("Введите данные");
            }
        );
    }

    // TODO: 16.05.2021 Deprecated. Replace by using DialogFragments
    protected Dialog onCreateDialog(int id) {
        if (id == 1)
            return new DatePickerDialog(this, startDateCallBack, startDateYear, startDateMonth, startDateDay);
        if (id == 2)
            return new DatePickerDialog(this, endDateCallBack, endDateYear, endDateMonth, endDateDay);

        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener startDateCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            startDateYear = year;
            startDateMonth = monthOfYear;
            startDateDay = dayOfMonth;
            binding.startDate.setText(startDateDay + "." + startDateMonth + "." + startDateYear);
        }
    };

    DatePickerDialog.OnDateSetListener endDateCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            endDateYear = year;
            endDateMonth = monthOfYear;
            endDateDay = dayOfMonth;
            binding.endDate.setText(endDateDay + "." + endDateMonth + "." + endDateYear);
        }
    };

    Date getDate(String date){
        try {
            return formatter.parse(date);
        } catch (ParseException e) {
            Log.e(Constants.appLog, e.getMessage());
        }
        return null;
    }

    @Override
    public void onReserveSuccess() {
        Extensions.successToast("Успешно");
        startActivity(new Intent(this, HomeActivity.class));
    }

    @Override
    public void onReserveFailed() {
        Extensions.errorToast("Ошибка");
    }

    @Override
    public void setRoomImageBitmap(Bitmap bitmap) {
        binding.roomImage.setImageBitmap(bitmap);
    }

    @Override
    public void setRoomImageId(int id) {
        binding.roomImage.setImageResource(id);
    }
}