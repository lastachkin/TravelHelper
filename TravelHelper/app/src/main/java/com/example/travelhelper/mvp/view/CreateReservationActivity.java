package com.example.travelhelper.mvp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.example.travelhelper.databinding.ActivityCreateReservationBinding;
import com.example.travelhelper.mvp.contract.CreateReservationContract;
import com.example.travelhelper.mvp.presenter.CreateReservationPresenter;

import java.util.Calendar;

public class CreateReservationActivity extends AppCompatActivity implements CreateReservationContract.View {
    ActivityCreateReservationBinding binding;
    CreateReservationPresenter presenter;

    int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    int currentMonth = Calendar.getInstance().get(Calendar.MONTH);
    int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateReservationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new CreateReservationPresenter(this);
        binding.startDate.setOnClickListener(v -> showDialog(1));
    }

    // TODO: 16.05.2021 Deprecated. Replace by using DialogFragments
    protected Dialog onCreateDialog(int id) {
        if (id == 1) {
            return new DatePickerDialog(this, myCallBack, currentYear, currentMonth, currentDay);
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            currentYear = year;
            currentMonth = monthOfYear;
            currentDay = dayOfMonth;
            binding.startDate.setText(currentDay + "." + currentMonth + "." + currentYear);
        }
    };
}