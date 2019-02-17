package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.broadcastreceiverdemo
 * @ClassName: LocalBroadcastReceiveDemo
 * @Author: DashingQI
 * @CreateDate: 2019/2/17 10:42 AM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/17 10:42 AM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class LocalBroadcastReceiveDemo extends BroadcastReceiver {
    private static final String TAG = "LocalBroadcastReceiveDe";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show();

    }
}
