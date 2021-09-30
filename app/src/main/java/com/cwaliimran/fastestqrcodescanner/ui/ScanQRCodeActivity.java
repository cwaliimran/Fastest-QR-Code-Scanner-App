package com.cwaliimran.fastestqrcodescanner.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.cwaliimran.fastestqrcodescanner.databinding.ActivityScanQRCodeBinding;
import com.cwaliimran.fastestqrcodescanner.utils.AnimUtils;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;

public class ScanQRCodeActivity extends BaseActivity {


    private static final int REQUEST_CAMERA_PERMISSION = 201;
    public String QRValue;
    ActivityScanQRCodeBinding binding;
    String intentData = "";
    Vibrator vibrator;
    private BarcodeDetector barcodeDetector;
    private CameraSource cameraSource;
    private boolean isVibrating = false;
    private boolean isScanned = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScanQRCodeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        context = this;
        getSupportActionBar().setTitle("Scan QR Code");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        AdRequest adRequest = new AdRequest.Builder().build();
        binding.adView3.loadAd(adRequest);
//
//        binding.btnScanNow.setOnClickListener(v ->
//        {
//            customer_id = binding.text.getText().toString().trim();
//            if (customer_id.equals("")){
//                Toast.makeText(ScanQRCodeActivity.this, "QR code not scanned! Scan Before you verify.", Toast.LENGTH_LONG).show();
//            }else {
//                verifyCustomer(ScanQRCodeActivity.this);
//            }
//        });
    }

    private void initBarcode() {
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        barcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.ALL_FORMATS)
                .build();

        cameraSource = new CameraSource.Builder(context, barcodeDetector)
//                .setRequestedPreviewSize(1920, 1080)
                .setAutoFocusEnabled(true) //you should add this feature
                .build();

        binding.surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                openCamera();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                cameraSource.stop();
            }
        });

        barcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {
//                Toast.makeText(getApplicationContext(), "To prevent memory leaks barcode scanner has been stopped", Toast.LENGTH_LONG).show();
            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> barCode = detections.getDetectedItems();
                if (barCode.size() > 0) {
                    barcodeDetector.release();
                    setBarCode(barCode);
                }
            }
        });
    }

    private void openCamera() {
        try {
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                AnimUtils.scanner_animation(binding.line, context);
                cameraSource.start(binding.surfaceView.getHolder());
            } else {
                ActivityCompat.requestPermissions(ScanQRCodeActivity.this, new
                        String[]{Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBarCode(final SparseArray<Barcode> barCode) {
        Log.d(TAG, "setBarCode: " + barCode.valueAt(0).displayValue);
        assert vibrator != null;
        if (!isVibrating) {
            vibrator.vibrate(100);
            isVibrating = true;
        }
        Intent intent = new Intent(context, QRCodeResultActivity.class);
        intent.putExtra("qr_result", barCode.valueAt(0).displayValue);
        startActivity(intent);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA_PERMISSION && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_DENIED)
                finish();
            else
                openCamera();
        } else
            finish();
    }


    @Override
    protected void onPause() {
        super.onPause();
        cameraSource.release();
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        initBarcode();
    }
}