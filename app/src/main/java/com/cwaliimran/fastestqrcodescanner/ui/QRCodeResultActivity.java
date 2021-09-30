package com.cwaliimran.fastestqrcodescanner.ui;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cwaliimran.fastestqrcodescanner.databinding.ActivityQRCodeResultBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

import java.util.Objects;

public class QRCodeResultActivity extends BaseActivity {
    ActivityQRCodeResultBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQRCodeResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("QR Code Result");
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView2.loadAd(adRequest);
        bundle = getIntent().getExtras();
        if (bundle != null) {
            binding.tvResult.setText(bundle.getString("qr_result"));
        } else {
            Toast.makeText(context, "No result found.", Toast.LENGTH_SHORT).show();
            binding.ivCopyToClipBoard.setVisibility(View.INVISIBLE);
        }
        binding.ivCopyToClipBoard.setOnClickListener(view -> {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("QR Result", bundle.getString("qr_result"));
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(context, "Copied to clipboard.", Toast.LENGTH_SHORT).show();
        });

    }


    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }
}