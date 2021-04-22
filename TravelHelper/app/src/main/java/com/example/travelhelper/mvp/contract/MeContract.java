package com.example.travelhelper.mvp.contract;

public interface MeContract {
    interface View{
        boolean isFieldsEnabled();
        void setFieldsEnabled();
        void setFieldsDisabled();
        void onUserUpdated();
        void setFirstName(String firstName);
        void setLastName(String lastName);
        void setEmail(String email);
        void setPhone(String phone);
    }
    interface Presenter{
        void onEditButtonClicked();
        void onSaveButtonClicked(String password);
        void onScreenLoaded();
    }
}
