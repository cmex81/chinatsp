//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.incall.proxy.storage;
import java.io.File;
import android.util.Log;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.os.storage.IMountService;
import android.util.Log;
import com.incall.proxy.ServiceConnection;
import com.incall.proxy.binder.service.IStorageInterface;
import com.incall.proxy.binder.service.IStorageInterface.Stub;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import com.incall.proxy.ServiceNameManager;

public final class StorageDeviceManager extends ServiceConnection<IStorageInterface> {
    private static final String TAG = "StorageDeviceManager";
    public static final String SERVICE_NAME_STORAGE =ServiceNameManager.SYSTEM_STORAGE_SERVICENAME;
    private static final String USB_PATH = "/storage/udisk/";
    private static final String USB_EXT_PATH = "/storage/udisk_ext/";
    private static final String HDD_PATH = "/storage/emulated/0/";
    private static StorageDeviceManager mStorageDeviceManager;
    private HashMap<StorageDeviceManager.StorageDeviceChangedListener, StorageDeviceManager.CallBack> mStorageChanged_map = new HashMap();

    public static synchronized StorageDeviceManager getInstance() {
        if(mStorageDeviceManager == null) {
            mStorageDeviceManager = new StorageDeviceManager();
        }

        return mStorageDeviceManager;
    }

    private StorageDeviceManager() {
        super(SERVICE_NAME_STORAGE);
    }

    protected boolean getServiceConnection() {
        IBinder getServiceObj = this.connectService();
        if(getServiceObj != null) {
            this.mService = Stub.asInterface(getServiceObj);
            return true;
        } else {
            this.mService = null;
            return false;
        }
    }

    protected void serviceReConnected() {
        Log.e("StorageDeviceManager", "----serviceReConnected----");
        if(this.mService != null) {
            HashMap var1 = this.mStorageChanged_map;
            synchronized(this.mStorageChanged_map) {
                Collection aCollection = this.mStorageChanged_map.values();
                Iterator var4 = aCollection.iterator();

                while(var4.hasNext()) {
                    StorageDeviceManager.CallBack callBack = (StorageDeviceManager.CallBack)var4.next();

                    try {
                        if(this.mService != null) {
                            ((IStorageInterface)this.mService).registerCallBack(callBack);
                            callBack.onUsbStatusNotify(this.isUsbExist());
                            continue;
                        }
                    } catch (Exception var6) {
                        var6.printStackTrace();
                        continue;
                    }

                    return;
                }

            }
        }
    }

    protected void serviceDied() {
        super.serviceDied();
        Log.e("StorageDeviceManager", "----serviceDied----");
    }

    public void addStorageDeviceChangedListener(StorageDeviceManager.StorageDeviceChangedListener aListener) {
        if(aListener != null && !this.mStorageChanged_map.containsKey(aListener)) {
            HashMap var2 = this.mStorageChanged_map;
            synchronized(this.mStorageChanged_map) {
                this.mStorageChanged_map.put(aListener, new StorageDeviceManager.CallBack(aListener, (IStorageInterface)this.mService));
            }
        }
    }

    public void removeStorageDeviceChangedListener(StorageDeviceManager.StorageDeviceChangedListener aListener) {
        if(aListener != null) {
            HashMap var2 = this.mStorageChanged_map;
            synchronized(this.mStorageChanged_map) {
                StorageDeviceManager.CallBack aCallBack = (StorageDeviceManager.CallBack)this.mStorageChanged_map.remove(aListener);

                try {
                    if(this.mService == null) {
                        return;
                    }

                    ((IStorageInterface)this.mService).unregisterCallBack(aCallBack);
                } catch (Exception var5) {
                    var5.printStackTrace();
                }

            }
        }
    }

    public boolean getStorageState(String mountPoint) {
        try {
            IMountService rex = android.os.storage.IMountService.Stub.asInterface(ServiceManager.getService("mount"));
            String state = rex.getVolumeState(mountPoint);
            Log.w("StorageDeviceManager", "getStorageState mountState: " + state);
            return state.equals("mounted");
        } catch (Exception var4) {
            Log.w("StorageDeviceManager", "Failed to read external storage state; assuming REMOVED: " + var4);
            return false;
        }
    }

    private static boolean isSdcardExist() {
        return false;
    }

    public static String getUsbPath() {
        return "/storage/udisk/";
    }

    public static String getUsbExtPath() {
        return "/storage/udisk_ext/";
    }

    public static String getHddPath() {
        return "/storage/emulated/0/";
    }

    public static boolean isHddExist() {
        return true;
    }


    public boolean isUsbExist() {
        try 
		{
			boolean ret = false;   
			File file = new File("/storage/udisk/checkUSB.txt");
			if(!file.exists())
			{
				if (file.createNewFile())ret=true;
			}
			else
			{
				ret=true;
			}
			Log.e("ggg","ret:"+ret);	
			return ret;
            //return ((IStorageInterface)this.mService).isUsbExist();
        } catch (Exception var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public boolean isUsbExtExist() {
        try {
            return ((IStorageInterface)this.mService).isUsbExtExist();
        } catch (Exception var2) {
            var2.printStackTrace();
            return false;
        }
    }

    public static boolean isUdiskExist() {
        Log.d("StorageDeviceManager", "isUdiskExist");
        String path = "/proc/mounts";
        boolean ret = false;

        try {
            String e = "GBK";
            File file = new File(path);
            if(file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), e);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null && !ret) {
                    String[] a = lineTxt.split(" ");
                    String str = a[0];
                    if(str.contains("/dev/block/vold") && a[1].contains("udisk")) {
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
            if(file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), e);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;

                while((lineTxt = bufferedReader.readLine()) != null && !ret) {
                    String[] a = lineTxt.split(" ");
                    String str = a[0];
                    if(str.contains("/dev/block/vold") && a[1].contains("udisk_ext")) {
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

    private static final class CallBack extends com.incall.proxy.binder.callback.IStorageCallBackInterface.Stub {
        private Handler mHandler = new Handler();
        private StorageDeviceManager.StorageDeviceChangedListener mListener;

        CallBack(StorageDeviceManager.StorageDeviceChangedListener auxChangedListener, IStorageInterface aService) {
            this.mListener = auxChangedListener;

            try {
                if(aService != null) {
                    aService.registerCallBack(this);
                }
            } catch (Exception var4) {
                var4.printStackTrace();
            }

        }

        public void onUsbStatusNotify(final boolean usbStatus) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onUsbStatusChanged(usbStatus);
                }
            });
        }

        public void onSdcardStatusNotify(final boolean sdcardStatus) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onSdcardStatusChanged(sdcardStatus);
                }
            });
        }

        public void onUsbExtStatusNotify(final boolean usbExtStatus) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    CallBack.this.mListener.onUsbExtStatusChanged(usbExtStatus);
                }
            });
        }
    }

    public interface StorageDeviceChangedListener {
        void onUsbStatusChanged(boolean var1);

        void onUsbExtStatusChanged(boolean var1);

        void onSdcardStatusChanged(boolean var1);
    }
}
