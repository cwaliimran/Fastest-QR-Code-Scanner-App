package com.cwaliimran.fastestqrcodescanner.ui;

import android.os.Bundle;

import com.cwaliimran.fastestqrcodescanner.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        hideActionbar();
        context = this;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                gotoActivityFinish(MainActivity.class);
            }
        }, 2000);
    }
}