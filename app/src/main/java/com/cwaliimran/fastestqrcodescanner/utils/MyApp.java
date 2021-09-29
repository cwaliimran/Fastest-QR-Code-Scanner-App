package com.cwaliimran.fastestqrcodescanner.utils;


import android.app.Application;

public class MyApp extends Application {
    public static MyApp myApp;
    public static Shared shared;

    public MyApp() {
    }

    public static MyApp getInstance() {
        return myApp;
    }

/*    //get current user
    public static ModelUser getAppUser() {
        Gson gson = new Gson();
        return gson.fromJson(MyApp.shared.getString(AppConstants.CURRENT_USER, ""), ModelUser.class);
    }

    public static List<String> getAllUsers() {
        Gson gson = new Gson();
        String json = shared.getString(AppConstants.ALL_USERS, AppConstants.EMPTY_ARRAY);
        Type type = new TypeToken<List<String>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    public static void saveAllUsers(List<String> data) {
        String json = new Gson().toJson(data);
        shared.setString(AppConstants.ALL_USERS, json);
    }*/

    @Override
    public void onCreate() {
        super.onCreate();
        myApp = this;
        shared = new Shared(this);

    }
}
