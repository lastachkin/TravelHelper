package com.example.travelhelper.mvp.contract;

import com.example.travelhelper.mvp.repository.model.Users;

public interface MeContract {
    interface View{
        boolean isFieldsEnabled();
        void setFieldsEnabled();
        void setFieldsDisabled();
        void onUserUpdated();
        void onFieldsIsEmpty();
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setEmail(String email);
        void setPhone(String phone);
    }
    interface Presenter{
        void onEditButtonClicked();
        void onSaveButtonClicked(Users user);
        void onScreenLoaded();
    }
}
