package com.example.travelhelper.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.travelhelper.R;
import com.example.travelhelper.databinding.ActivityHomeBinding;
import com.example.travelhelper.mvp.contract.HomeContract;
import com.example.travelhelper.mvp.presenter.HomePresenter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity implements HomeContract.View {

    private HomePresenter presenter;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        presenter = new HomePresenter(this);
        presenter.initLoginField();

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        binding.bottomNavigation.setOnNavigationItemSelectedListener(navListener);
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new HomeFragment()).commit();
        }
    }

    @Override
    public void setLogin(String login) {
        //binding.username.setText(login);
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener navListener =
            item -> {
                Fragment selectedFragment = null;
                int id = item.getItemId();

                if(id == R.id.nav_home)
                    selectedFragment = new HomeFragment();
                else if (id == R.id.nav_favorites)
                    selectedFragment = new FavoritesFragment();
                else if (id == R.id.nav_me)
                    selectedFragment = new MeFragment();
                else if (id == R.id.nav_reservations)
                    selectedFragment = new ReservationsFragment();
                else if (id == R.id.nav_search)
                    selectedFragment = new SearchFragment();

                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        selectedFragment).commit();
                return true;
            };
}
