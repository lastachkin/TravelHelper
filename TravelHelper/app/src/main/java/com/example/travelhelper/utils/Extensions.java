package com.example.travelhelper.utils;

import android.widget.Toast;

import com.example.travelhelper.App;

import es.dmoral.toasty.Toasty;

public class Extensions {
    public static void successToast(String msg) {
        Toasty.success(App.appContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void errorToast(String msg) {
        Toasty.error(App.appContext, msg, Toast.LENGTH_SHORT).show();
    }

    public static void warningToast(String msg) {
        Toasty.warning(App.appContext, msg, Toast.LENGTH_SHORT).show();
    }
}
