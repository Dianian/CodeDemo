package com.dainian.codedemo.demoBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * 本地广播接受
 */
public class MyReceiver extends BroadcastReceiver {

    private static final String TAG = "MyReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "000000000000000000000", Toast.LENGTH_SHORT).show();
    }
}
