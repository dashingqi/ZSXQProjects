package com.example.localsercvicedemo;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.localsercvicedemo
 * @ClassName: IntentServiceDemo
 * @Author: DashingQI
 * @CreateDate: 2019/2/16 5:58 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/16 5:58 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class IntentServiceDemo extends IntentService {
    private static final String TAG = "IntentServiceDemo";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public IntentServiceDemo(String name) {
        super("IntentServiceDemo");
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
}
