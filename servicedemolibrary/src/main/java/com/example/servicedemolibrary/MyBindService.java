package com.example.servicedemolibrary;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyBindService extends Service {
    private static final String TAG = "MyBindService";
    private DownBinder mBinder = new DownBinder();

    public MyBindService() {

    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    class DownBinder extends Binder {
        public void startDown() {
            Log.e(TAG, "startDown: ");
        }

        public int getProgress() {
            Log.e(TAG, "getProgress: ");
            return 0;
        }
    }
}
