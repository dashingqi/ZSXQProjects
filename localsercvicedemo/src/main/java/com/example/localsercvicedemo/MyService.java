package com.example.localsercvicedemo;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

/**
 *
 * //面试题
 * 1. Service的生命周期
 *   # onCreate onStartCommand  onBind  onDestroy
 *   # 其中 onCreate是服务第一次创建的时候会调用，当通过startService()来再次开启服务的时候，会调用onStartCommand()方法的；当通过bindService()开启服务的时候 onBind只会在第一次创建
 *     服务的时候调用，再次开启服务会调用 ServiceConnection 中 onServiceConnected这个方法
 *   # 当调用 stopService（StopSelf ） 或者unbindService来停止或者解绑服务的时候，会调用onDestroy方法。
 * 2. Service的开启方式
 *   # startService()
 *   # bindService()
 * 3. Service与Activity的通信方式
 *   # 通过Binder：ServiceConnect 中onServiceConnected(IBinder service) 中的Binder 就是 Service中自定义的Binder 可以调用自定义Binder类中的方法，来进行通信
 *   # 通过BroadcastReceiver
 * 4. Service与IntentService的区别
 *  普通服务的缺点
 *      1. 如果要在服务中做耗时的操作，必须得自己开启一个子线程，来处理耗时的操作
 *      2. startService的服务 开启了，如果没有手动调用StopService 或者stopSelf 那么就会一直运行
 *  IntentService相比较与Service的优点
 *      1. 开启IntentService 就会自动开启一个work线程来处理Intent请求
 *      2. 所有Intent请求处理完成后，IntentService会自动停止，不需要调用stopSelf
 *      3. 为onBind提供默认的显示，默认返回null
 *      4. 为onStartCommand提供默认的实现，将请求的Intent添加到队列中。
 */
public class MyService extends Service {
    private static final String TAG = "MyService";

    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "onBind: ");
        return new MyBinder();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i(TAG, "onStart: ");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    public class MyBinder extends Binder implements MyIBinder {

        public void stopService(ServiceConnection serviceConnection) {
            unbindService(serviceConnection);

        }

        @Override
        public void invokeMethodInMyService() {
            for (int i = 0; i < 20; i++) {
                System.out.println("service is opening");
            }
        }
    }

    public interface MyIBinder {
        void invokeMethodInMyService();
    }
}
