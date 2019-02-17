package com.example.broadcastreceiverdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * @ProjectName: ZSXQProjects
 * @Package: com.example.broadcastreceiverdemo
 * @ClassName: MyBootCompleteBroadcastReceive
 * @Author: DashingQI
 * @CreateDate: 2019/2/16 11:11 PM
 * @UpdateUser: 更新者
 * @UpdateDate: 2019/2/16 11:11 PM
 * @UpdateRemark:
 * @Version: 1.0
 */
public class MyBootCompleteBroadcastReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "boot complete", Toast.LENGTH_SHORT).show();
    }
}
