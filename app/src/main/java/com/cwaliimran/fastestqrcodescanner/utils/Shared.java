package com.cwaliimran.fastestqrcodescanner.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class Shared {
    private static final String PREF_NAME = "BANDITOUR";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String value;


    public Shared(Context context) {
        try {
            sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SharedPreferences getSharedPref() {
        return sharedPreferences;
    }


    public void clearAllPreferances(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void removeKey(String key) {
        editor = getSharedPref().edit();
        editor.remove(key);
        editor.apply();
    }


    //for int values
    public int setInt(String key, int value) {
        editor = getSharedPref().edit();
        editor.putInt(key, value);
        editor.apply();
        return value;
    }

    public int getInt(String key) {
        return getSharedPref().getInt(key, 0);
    }

    public boolean setBoolean(String key, boolean value) {
        editor = getSharedPref().edit();
        editor.putBoolean(key, value);
        editor.apply();
        return value;
    }

    public boolean getBoolean(String key) {
        return getSharedPref().getBoolean(key, false);
    }

    public void setString(String key, String value) {
        try {
            editor = getSharedPref().edit();
            editor.putString(key, value);
            editor.apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getString(String key, String val) {
        try {
            value = getSharedPref().getString(key, val);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return value;
    }

}
