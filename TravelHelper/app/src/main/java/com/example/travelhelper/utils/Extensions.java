package com.example.travelhelper.utils;

import android.view.Gravity;
import android.widget.Toast;

import com.example.travelhelper.App;

import es.dmoral.toasty.Toasty;

public class Extensions {
    public static void successToast(String msg) {
        Toasty.success(App.getInstance().getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void errorToast(String msg) {
        Toasty.error(App.getInstance().getAppContext(), msg, Toast.LENGTH_SHORT).show();
    }

    public static void infoBottomToast(String msg) {
        Toast toasty = Toasty.info(App.getInstance().getAppContext(), msg, Toast.LENGTH_SHORT);
        toasty.setGravity(Gravity.CENTER_HORIZONTAL, 0, 900);
        toasty.show();
    }

    public static void successBottomToast(String msg){
        Toast toasty = Toasty.success(App.getInstance().getAppContext(), msg, Toast.LENGTH_SHORT);
        toasty.setGravity(Gravity.CENTER_HORIZONTAL, 0, 900);
        toasty.show();
    }
}
