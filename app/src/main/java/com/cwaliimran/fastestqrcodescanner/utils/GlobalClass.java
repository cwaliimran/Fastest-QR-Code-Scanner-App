package com.cwaliimran.fastestqrcodescanner.utils;


import android.app.Activity;
import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.core.location.LocationManagerCompat;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import org.ocpsoft.prettytime.PrettyTime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.UUID;

public class GlobalClass {
    private static final String TAG = "GlobalClass";

    public static void hideKeyboard(Activity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public static boolean isValidEmail(String email) {
        return TextUtils.isEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }

    public static boolean isOnline(Context context) {
        boolean isConnected = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null) {
            if (netInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isConnected = true;
            } else if (netInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isConnected = true;
            }
        }
        return isConnected;
    }

    //generates random strig
    public static String randomId() {
        return UUID.randomUUID().toString();
    }

    public static boolean isLocationEnabled(Context context) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        return LocationManagerCompat.isLocationEnabled(locationManager);
    }

    public static void showSnackBar(View view) {
        final Snackbar sb = Snackbar.make(view, "please check your internet connection", BaseTransientBottomBar.LENGTH_INDEFINITE);
        sb.setAction("Ok", v -> {
            sb.dismiss();
        });
        sb.show();
    }


    public static String combineId(String id1, String id2) {
        if (id1.compareTo(id2) > 0) {
            return id1 + id2;
        } else {
            return id2 + id1;
        }
    }

    public static String messageDate() {
        return new SimpleDateFormat("MMM dd, yyyy 'at' hh:mm aa", Locale.getDefault()).format(new Date());
    }

    public static String prettyTime(Date date) {
        PrettyTime p = new PrettyTime();
        return p.format(date);
    }
}
