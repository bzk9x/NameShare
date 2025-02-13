package com.bittscafe.nameshare;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.nfc.NfcAdapter;

public class MainActivity extends AppCompatActivity {

    private final Intent scanActivity = new Intent();
    private final Intent scanNFCActivity = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        if (NfcAdapter.getDefaultAdapter(this) != null) {
            scanNFCActivity.setClass(getApplicationContext(), ScanNFCActivity.class);
            startActivity(scanNFCActivity);
            overridePendingTransition(R.anim.slide_in_bottom, 0);
        } else {
            scanActivity.setClass(getApplicationContext(), ScanActivity.class);
            startActivity(scanActivity);
            overridePendingTransition(R.anim.slide_in_bottom, 0);
        }
        finish();
    }
}