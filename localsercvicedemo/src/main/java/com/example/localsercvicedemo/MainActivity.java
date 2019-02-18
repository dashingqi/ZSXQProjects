package com.example.localsercvicedemo;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    private MyService.MyBinder myBinder;
    private MyServiceConnection myServiceConnection;
    private com.example.localsercvicedemo.MyBindIntentService.MyBind binder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        findViewById(R.id.btnUnBindService).setOnClickListener(this);
        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnStartService).setOnClickListener(this);
        findViewById(R.id.btnStartIntentService).setOnClickListener(this);
        findViewById(R.id.btnBindIntentService).setOnClickListener(this);
        findViewById(R.id.btnStopService).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartService:
                startService();
                break;
            case R.id.btnBindService:
                bindService();

                break;
            case R.id.btnUnBindService:
                unBindService();
                break;
            case R.id.btnStartIntentService:
                startIntentService();
                break;
            case R.id.btnBindIntentService:
                bindIntentService();
                break;
            case R.id.btnStopService:
                stopService(new Intent(MainActivity.this, MyService.class));
                break;
        }
    }

    /**
     * 1. 首次开启服务 先执行 onCreate()  onStartCommand()  onStart()
     * 2. 再次去开启服务 不执行onCreate（），直接执行onStartCommand()和onStart();
     * 3. 跟调用者声明周期没有关系，调用者只是开启服务，不能调用服务内部的方法
     */
    private void startService() {
        startService(new Intent(MainActivity.this, MyService.class));

    }

    /**
     * 绑定服务
     * 1.首次绑定 执行的方法有 onCreate  onBind onServiceConnected
     * 2. 再次去绑定 仅仅执行 onServiceConnected方法
     * 3. 调用者挂了，服务也会跟着挂，这种方式下，绑定者可以调用服务里面的方法
     * 4. BIND_AUTO_CREATE ： 表示活动和服务进行绑定后自动创建服务，这样服务的onBind方法就会执行，onStartCommand就不会执行了。
     * 5. 首次绑定服务会执行onCreate() 再次去启动服务只会执行onServiceConnected(ServiceConnected中的一个抽象方法)
     * 6. 如果一个服务调用了startService和bindService 如果要停止这个服务 必须得调用 stopService和unbindService
     */
    private void bindService() {
        myServiceConnection = new MyServiceConnection();
        bindService(new Intent(MainActivity.this, MyService.class), myServiceConnection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解绑服务
     * 1. 首先的有已经绑定服务 再去解绑服务，执行的方法有 onUnbind  onDestroy
     */
    private void unBindService() {
        unbindService(myServiceConnection);

    }

    /**
     * 当调用bindService方法进行绑定IntentService
     * 1. 不会调用onHandleIntent ，就不会自己开启工作线程和自动结束 需要调用方法unbindService()来结束。
     */
    private void bindIntentService() {
        Log.i(TAG, "bindIntentService: " + Thread.currentThread().getId());
        MyBindIntentServiceConnection myBindIntentServiceConnection = new MyBindIntentServiceConnection();
        bindService(new Intent(MainActivity.this, MyBindIntentService.class), myBindIntentServiceConnection, BIND_AUTO_CREATE);
    }

    private void startIntentService() {
        Log.i(TAG, "startIntentService: " + Thread.currentThread().getId());
        startService(new Intent(MainActivity.this, MyIntentService.class));
    }

    class MyServiceConnection implements ServiceConnection {


        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.i("MyService", "onServiceConnected: ");
            myBinder = (MyService.MyBinder) service;
            myBinder.invokeMethodInMyService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.i("MyService", "onServiceDisconnected: ");

        }
    }


    class MyBindIntentServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            binder = (com.example.localsercvicedemo.MyBindIntentService.MyBind) service;
            binder.startService();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
