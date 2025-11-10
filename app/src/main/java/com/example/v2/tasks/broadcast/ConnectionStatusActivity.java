package com.example.v2.tasks.broadcast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.Network;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.v2.R;

public class ConnectionStatusActivity extends AppCompatActivity
{
    private CheckBox checkBox;
    private Button btnCheckStatus;
    private TextView txtStatus;
    private ConnectivityReceiver connectivityReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection_status);

        checkBox = findViewById(R.id.checkBox);
        btnCheckStatus = findViewById(R.id.btnCheckStatus);
        txtStatus = findViewById(R.id.txtStatus);

        connectivityReceiver = new ConnectivityReceiver();

        btnCheckStatus.setOnClickListener(v -> {
            if (checkBox.isChecked()) {
                showStatusDialog(getNetworkStatus(this));
            }
        });

        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                unregisterReceiverSafe();
                txtStatus.setText("Button mode enabled — press button to check status");
            } else {
                registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
            }
        });

        registerReceiver(connectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
    }

    private void showStatusDialog(String message) {
        new AlertDialog.Builder(this)
                .setTitle("Internet Status")
                .setMessage(message)
                .setPositiveButton("OK", null)
                .show();
    }

    private String getNetworkStatus(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) return "No connectivity manager";

        Network network = cm.getActiveNetwork();
        if (network == null) return "No Internet Connection";
        NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
        if (capabilities == null) return "No Internet Connection";

        if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
            return "Connected via Wi-Fi";
        else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
            return "Connected via Mobile Data";
        else
            return "Connected (unknown type)";
    }

    private void unregisterReceiverSafe() {
        try {
            unregisterReceiver(connectivityReceiver);
        } catch (IllegalArgumentException ignored) {}
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiverSafe();
    }

    public class ConnectivityReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (!checkBox.isChecked()) {
                String status = getNetworkStatus(context);
                txtStatus.setText(status);
            }
        }
    }
}
