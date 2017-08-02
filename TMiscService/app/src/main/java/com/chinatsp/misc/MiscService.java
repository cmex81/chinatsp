package com.chinatsp.misc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Process;
import android.util.Log;

public class MiscService extends Service {
    private static String TAG = "TMiscService";
    private MiscServiceAdapter miscServiceAdapter;

    @Override
    public void onCreate() {
        super.onCreate();
        initService();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        Log.d(TAG, "onBind: " + Process.myPid());
        initService();
        return miscServiceAdapter;
    }

    private void initService() {
        if (miscServiceAdapter == null) {
            miscServiceAdapter = new MiscServiceAdapter(this);
        }
//		ServiceManager.addService(ServiceNameManager.MISCSERVICE, mBinder);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + Process.myPid());
        initService();
        return START_NOT_STICKY;
    }
}
