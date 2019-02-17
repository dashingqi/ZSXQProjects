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

    /**
     * 1. 对BroadcastReceive的理解
     *    #是一种消息机制
     *    #不能在广播内做耗时的任务
     * 2. 广播的分类？使用方式和场景
     *    #广播分为：普通广播，有序广播，系统广播，本地广播（应用内广播），粘性广播
     *    #普通广播：sendBroadcast(intent)
     *    #有序广播：sendOrderBroadcast(intent,null);
     *    #系统广播：比如监听手机开启的广播
     *    #本地广播：LocalBroadcastManager
     *    #粘性广播：Android5.0 & APi21 中已经失效了。
     * 3. 动态广播和静态广播有什么区别
     *       #日常中广播注册方式：静态注册（xml文件） 和动态注册
     *            # 静态注册：xml文件中配置
     *                        <receive
     *                        android:name=".MyBootCompleteBroadcastReceive"
     *                        android:enabled="true"
     *                        android:exported="true">
     *                        <receive/>
     *
     *            # 动态注册（代码注册）：IntentFilter intentFilter = new IntentFilter();
     *                       intentFilter.addAction("");
     *                       registerReceive(new MyBroadcastReceive(),intentFilter)
     *                       注册的广播需要在onDestroy()中进行unRegisterReceive(new MyBroadcastReceive());
     *            # 静态广播属于常住性广播   比动态注册的方式存活的时间长。动态广播跟随着Activity的生命周期
     *            # 同优先级的广播接收器，动态优先于静态。
     *            # 动态比静态的安全
     *  4. 关于各种注册方式 onReceive(Context context)中context的类型
     *      # 静态注册的广播（全局+应用内广播）返回值是 ReceiveRestrictedContext
     *      # 全局广播的动态注册：Activity context
     *      # 应用内的动态注册（LocalBroadcastReceive）返回值是 Application context；
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG, "onReceive: ");
        Toast.makeText(context, "test", Toast.LENGTH_LONG).show();
    }
}
