package com.example.localsercvicedemo;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class MyBindIntentService extends IntentService {

    private static final String TAG = "MyBindIntentService";
    public MyBindIntentService() {
        super("MyBindIntentService");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: ");
        Log.i(TAG, "onBind: "+Thread.currentThread().getId());
        return new MyBind();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.i(TAG, "onHandleIntent: "+Thread.currentThread().getId());

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }


    public class MyBind extends Binder{

        public void startService(){
            Log.i(TAG, "startService: "+"sds");
        }
    }
}
