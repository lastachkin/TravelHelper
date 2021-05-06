package com.example.travelhelper.mvp.view;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.travelhelper.databinding.FragmentHotelsBinding;
import com.example.travelhelper.mvp.contract.HotelsContract;
import com.example.travelhelper.mvp.repository.model.Hotels;
import com.example.travelhelper.mvp.view.adapter.HotelAdapter;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class HotelsFragment extends Fragment implements HotelsContract.View {
    List<Hotels> hotels;
    FragmentHotelsBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHotelsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();

        hotels = new ArrayList<>();
        hotels.add(new Hotels("Europe", "Minsk", "ul. Lenna 6"));
        hotels.add(new Hotels("Euro1pe", "Minsk2424", "ul. Lenna 7"));

        binding.hotelList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        binding.hotelList.setAdapter(new HotelAdapter(hotels));

        return view;
    }
}
