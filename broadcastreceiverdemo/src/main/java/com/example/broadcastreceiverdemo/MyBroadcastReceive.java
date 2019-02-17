package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.broadcastreceiverdemo
 * @ClassName: MyBroadcastReceive
 * @Author: DashingQI
 * @CreateDate: 2019/2/16 11:00 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/16 11:00 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyBroadcastReceive extends BroadcastReceiver {
    private static final String TAG = "MyBroadcastReceive";

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show();
    }
}
