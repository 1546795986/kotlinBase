package com.example.broadcasttest;

import static android.widget.Toast.makeText;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        makeText(context, "received in MyBroadcastReceiver", Toast.LENGTH_SHORT).show();
    }
}
