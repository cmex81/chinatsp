//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.incall.proxy.backstage;

import android.os.IBinder;
import com.incall.proxy.ServiceConnection;
import com.incall.proxy.binder.service.IBackStage;
import com.incall.proxy.binder.service.IBackStage.Stub;
import com.incall.proxy.ServiceNameManager;


public class BackStageManager extends ServiceConnection<IBackStage> {
    private static BackStageManager mBackStageManager;

    public static synchronized BackStageManager getInstance() {
        if(mBackStageManager == null) {
            mBackStageManager = new BackStageManager();
        }

        return mBackStageManager;
    }

    protected BackStageManager() {
        super(ServiceNameManager.SYSTEM_BACKSTAGE_SERVICENAME);
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
    }

    public void enableAdb(boolean enable) {
        try {
            ((IBackStage)this.mService).enableAdb(enable);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void enableWifiAdb(boolean enable) {
        try {
            ((IBackStage)this.mService).enableWifiAdb(enable);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void enableLog(boolean enable) {
        try {
            ((IBackStage)this.mService).enableLog(enable);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void setUsbHost(boolean isHost) {
        try {
            ((IBackStage)this.mService).setUsbHost(isHost);
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public void saveLogToExt() {
        try {
            ((IBackStage)this.mService).saveLogToExt();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void startUpOriginSettings() {
        try {
            ((IBackStage)this.mService).startUpOriginSettings();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }
}
