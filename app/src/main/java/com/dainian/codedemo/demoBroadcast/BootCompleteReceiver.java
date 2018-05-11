package com.dainian.codedemo.demoBroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * 静态注册广播
 * 在Manifest页注册
 */
public class BootCompleteReceiver extends BroadcastReceiver {
    private static final String TAG = "BootCompleteReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(TAG, "onReceive: ");
        Toast.makeText(context, "Boot Complete", Toast.LENGTH_LONG).show();


    }
}
