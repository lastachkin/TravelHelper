package com.example.travelhelper.mvp.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.travelhelper.databinding.FragmentMeBinding;
import com.example.travelhelper.mvp.contract.MeContract;
import com.example.travelhelper.mvp.presenter.MePresenter;
import com.example.travelhelper.mvp.repository.model.Users;
import com.example.travelhelper.utils.Constants;
import com.example.travelhelper.utils.Extensions;

import org.jetbrains.annotations.Nullable;

public class MeFragment extends Fragment implements MeContract.View {
    private MePresenter presenter;
    private FragmentMeBinding binding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMeBinding.inflate(getLayoutInflater());
        presenter = new MePresenter(this);
        presenter.onScreenLoaded();
        binding.editBtn.setOnClickListener(view -> presenter.onEditButtonClicked());
        binding.saveBtn.setOnClickListener(view -> presenter.onSaveButtonClicked(
                new Users(Constants.currentUser.getId(),
                        binding.firstName.getText().toString(),
                        binding.lastName.getText().toString(),
                        binding.phone.getText().toString(),
                        binding.email.getText().toString(),
                        Constants.currentUser.getLogin(),
                        binding.password.getText().toString(),
                        "U")));
        return binding.getRoot();
    }

    @Override
    public boolean isFieldsEnabled() {
        if(binding.password.isEnabled())
            return true;
        else
            return false;
    }

    @Override
    public void setFieldsEnabled() {
        binding.firstName.setEnabled(true);
        binding.lastName.setEnabled(true);
        binding.email.setEnabled(true);
        binding.phone.setEnabled(true);
        binding.password.setEnabled(true);
    }

    @Override
    public void setFieldsDisabled() {
        binding.firstName.setEnabled(false);
        binding.lastName.setEnabled(false);
        binding.email.setEnabled(false);
        binding.phone.setEnabled(false);
        binding.password.setEnabled(false);
    }

    @Override
    public void onUserUpdated() {
        Extensions.successToast("Обновлено");
    }

    @Override
    public void onFieldsIsEmpty() {
        Extensions.errorToast("Заполните поля");
    }

    @Override
    public void setFirstName(String firstName) {
        binding.firstName.setText(firstName);
    }

    @Override
    public void setLastName(String lastName) {
        binding.lastName.setText(lastName);
    }

    @Override
    public void setEmail(String email) {
        binding.email.setText(email);
    }

    @Override
    public void setPhone(String phone) {
        binding.phone.setText(phone);
    }
}
