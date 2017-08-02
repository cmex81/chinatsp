package com.chinatsp.storage;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.hardware.usb.UsbAccessory;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Environment;
import android.util.Log;

/**
 * Created by ryan on 28/07/2017.
 */
public class StorageReceiver extends BroadcastReceiver{
    private final String TAG = "StorageReceiver";
    public static final String ACTION_USB_STATE =
            "android.hardware.usb.action.USB_STATE";
    public static final String USB_CONNECTED = "connected";
    private StorageReceiveCallback storageReceiveCallback;

    public StorageReceiver( StorageReceiveCallback storageReceiveCallback) {
        this.storageReceiveCallback = storageReceiveCallback;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive = " + intent.getAction());
        if (storageReceiveCallback==null)return;
        switch (intent.getAction()) {
            case ACTION_USB_STATE:
                storageReceiveCallback.onUsbStatusChange(intent.getBooleanExtra(USB_CONNECTED, false));
                Log.d(TAG, "USB has change to connect =" + intent.getBooleanExtra(USB_CONNECTED, false));
                break;
            case UsbManager.ACTION_USB_DEVICE_ATTACHED:
            case UsbManager.ACTION_USB_DEVICE_DETACHED:
                UsbDevice device = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                Log.d(TAG, "USB Device`s info =" );
                break;
            case UsbManager.ACTION_USB_ACCESSORY_ATTACHED:
            case UsbManager.ACTION_USB_ACCESSORY_DETACHED:
                UsbAccessory accessory = intent.getParcelableExtra(UsbManager.EXTRA_ACCESSORY);
                Log.d(TAG, "USB accessory`s info =" );
                break;
            case Intent.ACTION_MEDIA_MOUNTED:
                storageReceiveCallback.onSDcardStatusChange(true);
                Log.d(TAG, "SD card has mounted =" + Environment.getExternalStorageDirectory());
                break;
            case Intent.ACTION_MEDIA_UNMOUNTED:
            case Intent.ACTION_MEDIA_REMOVED:
                storageReceiveCallback.onSDcardStatusChange(false);
                Log.d(TAG, "SD card has unmounted =" + Environment.getDataDirectory());
                break;
        }
    }
    interface StorageReceiveCallback{
        void onUsbStatusChange(boolean status);
        void onUsbExtStatusChange(boolean status);
        void onSDcardStatusChange(boolean status);
    }
}
