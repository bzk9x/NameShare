package com.bittscafe.nameshare;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ScanNFCActivity extends AppCompatActivity {

    private final Intent scanActivity = new Intent();
    private final Intent EditNameCardActivity = new Intent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_scan_nfc);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button cancel = findViewById(R.id.cancel);
        cancel.setOnClickListener(view -> finish());

        NfcAdapter nfcAdapter = NfcAdapter.getDefaultAdapter(this);
        TextView scanMessage = findViewById(R.id.scan_message);
        if (!nfcAdapter.isEnabled()) {
            scanMessage.setText(getString(R.string.nfc_is_disabled));
        }

        ImageView scanQR = findViewById(R.id.scan_qr);
        scanQR.setOnClickListener(view -> {
            scanActivity.setClass(getApplicationContext(), ScanActivity.class);
            startActivity(scanActivity);
            finish();
        });

        ImageView edit = findViewById(R.id.edit);
        edit.setOnClickListener(view -> {
            EditNameCardActivity.setClass(getApplicationContext(), EditNameCardActivity.class);
            startActivity(EditNameCardActivity);
            finish();
        });
    }
}