package com.example.travelhelper.utils;

import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import com.example.travelhelper.App;

import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

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

    public static String encodeToAES(String text){
        SecretKeySpec sks = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("Crypto", "AES secret key spec error");
        }

        // Encode the original data with AES
        byte[] encodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.ENCRYPT_MODE, sks);
            encodedBytes = c.doFinal(text.getBytes());
        } catch (Exception e) {
            Log.e("Crypto", "AES encryption error");
        }

        return new String(encodedBytes);
    }

    public static String decodeFromAES(String text){
        SecretKeySpec sks = null;
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            sr.setSeed("any data used as random seed".getBytes());
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128, sr);
            sks = new SecretKeySpec((kg.generateKey()).getEncoded(), "AES");
        } catch (Exception e) {
            Log.e("Crypto", "AES secret key spec error");
        }

        byte[] decodedBytes = null;
        try {
            Cipher c = Cipher.getInstance("AES");
            c.init(Cipher.DECRYPT_MODE, sks);
            decodedBytes = c.doFinal(text.getBytes());
        } catch (Exception e) {
            Log.e("Crypto", "AES decryption error");
        }

        return new String(decodedBytes);
    }
}
