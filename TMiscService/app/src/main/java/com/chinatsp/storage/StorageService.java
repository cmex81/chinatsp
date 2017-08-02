package com.chinatsp.storage;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Build;
import android.os.Environment;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.util.Log;

import com.chinatsp.misc.IServiceInterface;
import com.incall.log.MjLog;
import com.incall.proxy.binder.callback.IStorageCallBackInterface;
import com.incall.proxy.binder.service.IStorageInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class StorageService extends IStorageInterface.Stub implements IServiceInterface{
    private final String TAG = "StorageService";
    private static final String USB_PATH = "/storage/udisk/";
    private static final String USB_EXT_PATH = "/storage/udisk_ext/";
    private RemoteCallbackList<IStorageCallBackInterface> remoteCallbackList = new RemoteCallbackList<IStorageCallBackInterface>();
    ;
    private Context mContext;
    private StorageReceiver mStorageReceiver;

    private StorageService() {
    }

    public static StorageService getInstance() {
        return StorageServiceSingletonHolder.sInstance;
    }


    @Override
    public void onDestroy() {
        mContext.unregisterReceiver(mStorageReceiver);
    }

    private static class StorageServiceSingletonHolder {
        private static final StorageService sInstance = new StorageService();
    }
    @Override
    public void initService(Context context) {
        this.mContext = context;
        if (mStorageReceiver == null) {
            mStorageReceiver = new StorageReceiver(new StorageReceiver.StorageReceiveCallback() {
                @Override
                public void onUsbStatusChange(boolean status) {
                    notifyUsbStatus(status);
                }

                @Override
                public void onUsbExtStatusChange(boolean status) {
                    notifyUsbExtStatus(status);
                }

                @Override
                public void onSDcardStatusChange(boolean status) {
                    notifySdCardStatus(status);
                }
            });
            IntentFilter filter = new IntentFilter();
            filter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
            filter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
            filter.addAction(UsbManager.ACTION_USB_ACCESSORY_ATTACHED);
            filter.addAction(UsbManager.ACTION_USB_ACCESSORY_DETACHED);
            mContext.registerReceiver(mStorageReceiver, filter);
        }
    }

    @Override
    public void registerCallBack(IStorageCallBackInterface iStorageCallBackInterface) throws RemoteException {
        remoteCallbackList.register(iStorageCallBackInterface);
    }

    @Override
    public void unregisterCallBack(IStorageCallBackInterface iStorageCallBackInterface) throws RemoteException {
        remoteCallbackList.unregister(iStorageCallBackInterface);
    }

    @Override
    public boolean isUsbExist() throws RemoteException {
        return getResultIsUsbExist();
    }

    @Override
    public boolean isUsbExtExist() throws RemoteException {
        return getResultIsUsbExtExist();
    }

    @Override
    public boolean isSdcardExist() throws RemoteException {
        return getResultIsSdCardExist();
    }

    /**
     * get result which the USB path exist
     *
     * @return
     */
    private boolean getResultIsUsbExist() {
        MjLog.d(TAG, "====get external path = " + Environment.getExternalStorageDirectory());
        File dir = new File(Environment.getExternalStorageDirectory() + USB_PATH);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * get result which the second USB path exist
     *
     * @return
     */
    private boolean getResultIsUsbExtExist() {
        MjLog.d(TAG, "====get external path = " + Environment.getExternalStorageDirectory());
        File dir = new File(Environment.getExternalStorageDirectory() + USB_EXT_PATH);
        if (dir.exists() && dir.isDirectory()) {
            return true;
        }
        return false;
    }

    /**
     * get result has sd card.
     *
     * @return
     */
    private boolean getResultIsSdCardExist() {
        MjLog.d(TAG, "====get SD path = " + Environment.getExternalStorageDirectory());
        return Environment.getExternalStorageState()
                .equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * notify USB status to UI layer when it has changed.
     */
    private void notifyUsbStatus(boolean status) {
        int len = remoteCallbackList.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                remoteCallbackList.getBroadcastItem(i).onUsbStatusNotify(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        remoteCallbackList.finishBroadcast();
    }

    /**
     * notify SD card status to UI layer when it has changed.
     */
    private void notifySdCardStatus(boolean status) {
        int len = remoteCallbackList.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                remoteCallbackList.getBroadcastItem(i).onSdcardStatusNotify(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        remoteCallbackList.finishBroadcast();
    }

    /**
     * notify UsbExt status to UI layer when it has changed.
     */
    private void notifyUsbExtStatus(boolean status) {
        int len = remoteCallbackList.beginBroadcast();
        for (int i = 0; i < len; i++) {
            try {
                remoteCallbackList.getBroadcastItem(i).onUsbExtStatusNotify(status);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
        remoteCallbackList.finishBroadcast();
    }


    public static boolean isUdiskExist() {
        Log.d("StorageDeviceManager", "isUdiskExist");
        String path = "/proc/mounts";
        boolean ret = false;

        try {
            String e = "GBK";
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), e);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null && !ret) {
                    String[] a = lineTxt.split(" ");
                    String str = a[0];
                    if (str.contains("/dev/block/vold") && a[1].contains("udisk")) {
                        ret = true;
                    }
                }

                read.close();
            } else {
                Log.d("StorageDeviceManager", "can\'t find file: " + path);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return ret;
    }

    public static boolean isUdiskExtExist() {
        Log.d("StorageDeviceManager", "isUdiskExtExist");
        String path = "/proc/mounts";
        boolean ret = false;

        try {
            String e = "GBK";
            File file = new File(path);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), e);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while ((lineTxt = bufferedReader.readLine()) != null && !ret) {
                    String[] a = lineTxt.split(" ");
                    String str = a[0];
                    if (str.contains("/dev/block/vold") && a[1].contains("udisk_ext")) {
                        ret = true;
                    }
                }

                read.close();
            } else {
                Log.d("StorageDeviceManager", "can\'t find file: " + path);
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        return ret;
    }
}
