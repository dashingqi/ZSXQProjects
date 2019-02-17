package com.example.broadcastreceiverdemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private LocalBroadcastManager localBroadcastManager;
    private LocalBroadcastReceiveDemo localBroadcastReceiveDemo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initLocalBroadcastReceive();
    }

    private void initView() {
        findViewById(R.id.btnSendBroadcast).setOnClickListener(this);
        findViewById(R.id.btnSendOrderBroadcast).setOnClickListener(this);
        findViewById(R.id.btnSendLocalBroad).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSendBroadcast:
                sendBroadcastReceive();
                break;
            case R.id.btnSendOrderBroadcast:
                break;
            case R.id.btnSendLocalBroad:
                sendLocalBroadcastReceive();
                break;
        }

    }

    public void sendBroadcastReceive() {
        Log.i(TAG, "sendBroadcastReceive: ");
        Intent intent = new Intent("com.example.broadcastreceiverdemo.TEST");
        sendBroadcast(intent);
    }

    /**
     * 初始化本地广播
     */
    public void initLocalBroadcastReceive() {
        initLocalBroadcastManage();
        registerBroadcast();
    }

    /**
     * 注册广播
     */
    private void registerBroadcast() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.broadcastreceiverdemo.LOCAL_BROAD");
        localBroadcastReceiveDemo = new LocalBroadcastReceiveDemo();
        localBroadcastManager.registerReceiver(localBroadcastReceiveDemo, intentFilter);
    }

    /**
     * 初始化本地广播
     */
    private void initLocalBroadcastManage() {
        localBroadcastManager = LocalBroadcastManager.getInstance(this);
    }

    /**
     * 发送本地广播
     */
    private void sendLocalBroadcastReceive() {
        Intent intent = new Intent();
        intent.setAction("com.example.broadcastreceiverdemo.LOCAL_BROAD");
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        localBroadcastManager.unregisterReceiver(localBroadcastReceiveDemo);
    }
}
