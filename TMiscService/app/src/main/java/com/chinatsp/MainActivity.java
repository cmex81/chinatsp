package com.chinatsp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.widget.TextView;

import com.chinatsp.misc.MiscService;
import com.chinatsp.misc.MiscServiceAdapter;
import com.incall.proxy.binder.callback.IStorageCallBackInterface;


/**
 * Created by ryan on 31/07/2017.
 * this class by personal test
 */
public class MainActivity extends Activity{
    private MiscServiceAdapter mBinder;
    private TextView usbT,sdT;
    private StorageDeviceCallback registerCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usbT = (TextView) findViewById(R.id.usb_tv);
        sdT = (TextView) findViewById(R.id.sd_tv);
        registerCallBack = new StorageDeviceCallback();
        Intent intent = new Intent(this, MiscService.class);
        bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

    }
    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
             mBinder = (MiscServiceAdapter) service;
            try {
                mBinder.registerCallBack(registerCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            try {
                mBinder.unregisterCallBack(registerCallBack);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    };

    private class StorageDeviceCallback implements IStorageCallBackInterface {

        @Override
        public void onUsbStatusNotify(boolean b) throws RemoteException {
            usbT.setText(b?"USB A口 已插入":"USB A口未插入");
        }

        @Override
        public void onUsbExtStatusNotify(boolean b) throws RemoteException {
            usbT.setText(b?"USB B口 已插入":"USB B口未插入");
        }

        @Override
        public void onSdcardStatusNotify(boolean b) throws RemoteException {
            sdT.setText(b?"SD 已插入":"SD 未插入");
        }

        @Override
        public IBinder asBinder() {
            return mBinder;
        }
    }

}
