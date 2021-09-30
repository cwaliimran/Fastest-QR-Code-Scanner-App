package com.cwaliimran.fastestqrcodescanner.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cwaliimran.fastestqrcodescanner.R;
import com.cwaliimran.fastestqrcodescanner.databinding.ActivityMainBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends BaseActivity {
ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView.loadAd(adRequest);
        binding.btnStartScan.setOnClickListener(view -> {
            gotoActivity(ScanQRCodeActivity.class);
        });
    }
}