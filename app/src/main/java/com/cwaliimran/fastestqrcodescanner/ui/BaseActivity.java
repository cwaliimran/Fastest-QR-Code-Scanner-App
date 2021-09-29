package com.cwaliimran.fastestqrcodescanner.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity {
    public String TAG = "response";
    public Context context;
    public Intent intent;
    public String name, email;
    public Bundle bundle;
    public String randomId;

    protected void gotoActivity(Class<?> activity) {
        intent = new Intent(context, activity);
        startActivity(intent);
    }

    //starts new activity and finishes current activity
    protected void gotoActivityFinish(Class<?> activity) {
        intent = new Intent(context, activity);
        startActivity(intent);
        finish();
    }

    //starts new activity and finishes all previous activities
    protected void gotoActivityFinishAll(Class<?> activity) {
        intent = new Intent(context, activity);
        startActivity(intent);
        finishAffinity();
    }

    protected void fullScreen(int colorRes) {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        getWindow().setStatusBarColor(colorRes);
    }
    public void hideActionbar() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
    }

    public void showActionBar(String title, boolean backButton) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
            if (backButton) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }
    }
}