//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.incall.proxy.update;

import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import com.incall.proxy.ServiceConnection;
import com.incall.proxy.binder.service.IUpdateInterface;
import com.incall.proxy.binder.service.IUpdateInterface.Stub;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Arrays;
import com.incall.proxy.ServiceNameManager;

public class UpdateManager extends ServiceConnection<IUpdateInterface> {
    public static final String SYSTEM_UPDATE_STARTED = "coagent.action.update.update_started";
    private static final String TAG = UpdateManager.class.getSimpleName();
    private static final String SERVICE_NAME_UPDATE = ServiceNameManager.SYSTEM_UPDATE_SERVICENAME;
    private static final int UPDATE_OS = 0;
    private static final int UPDATE_MCU = 1;
    private static final int UPDATE_MAP = 2;
    private static final int UPDATE_8836 = 3;
    private static UpdateManager mUpdateManager;
    UpdateManager.IUpdateListener mIUpdateCallback = null;

    private UpdateManager() {
        super(SERVICE_NAME_UPDATE);
    }

    public static synchronized UpdateManager getInstance() {
        if(mUpdateManager == null) {
            mUpdateManager = new UpdateManager();
        }

        return mUpdateManager;
    }

    protected boolean getServiceConnection() {
        IBinder getServiceObj = this.connectService();
        Log.v("hh", "getServiceObj:" + getServiceObj);
        if(getServiceObj != null) {
            this.mService = Stub.asInterface(getServiceObj);
            return true;
        } else {
            this.mService = null;
            return false;
        }
    }

    protected void serviceReConnected() {
        this.addUpdateListener(this.mIUpdateCallback);
    }

    public void updateOS(String path) {
        Log.v("hh", "updateOS:" + this.mService);
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).updateOS(path);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public void updateMCU(String path) {
        Log.v("hh", "updateMCU:" + this.mService);
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).updateMCU(path);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public void updateTW8836(String path) {
        Log.v("hh", "updateTW8836:" + path);
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).updateTW8836(path);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public void updateMap(String path) {
        Log.v("hh", "updateGPS:" + path);
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).updateGPS(path);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }

    public void updateALL(String[] path) {
        Log.v("hh", "updateALL:" + Arrays.toString(path));
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).updateALL(path);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }
    }

    public void reboot() {
        Log.v("hh", "reboot:");
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).reboot();
            } catch (Exception var2) {
                var2.printStackTrace();
            }

        }
    }

    public void checkFile(boolean isOsFile) {
        if(this.mIUpdateCallback == null) {
            Log.d(TAG, "checkFile, the updateCallBack is null, so return");
        } else {
            String md5FilePath = "";
            String updateFilePath = "";
            final byte state;
            if(isOsFile) {
                md5FilePath = "/storage/udisk/CoagentUpgrade/OS/update.md5";
                updateFilePath = "/storage/udisk/CoagentUpgrade/OS/update.zip";
                state = 0;
            } else {
                md5FilePath = "/storage/udisk/CoagentUpgrade/MCU/McuAppUpdate.md5";
                updateFilePath = "/storage/udisk/CoagentUpgrade/MCU/McuAppUpdate.img";
                state = 1;
            }

            Log.d(TAG, "checkStart。。。");
            this.mIUpdateCallback.checkStart(state);
            if((new File(md5FilePath)).exists() && (new File(updateFilePath)).exists()) {
                final String md5 = this.readMd5FromFile(md5FilePath);
                final File updateFile = new File(updateFilePath);
                Log.d(TAG, "read md5File is " + md5);
                (new Thread() {
                    public void run() {
                        long stratCheckTime = SystemClock.elapsedRealtime();
                        String realMd5 = UpdateManager.this.getFileMD5(updateFile);
                        Log.d(UpdateManager.TAG, "checkMd5, last time is " + (SystemClock.elapsedRealtime() - stratCheckTime));
                        Log.d(UpdateManager.TAG, "realMd5 is " + realMd5);
                        if(!TextUtils.isEmpty(md5) && !TextUtils.isEmpty(realMd5) && md5.equals(realMd5)) {
                            Log.d(UpdateManager.TAG, "checkEnd,success...");
                            UpdateManager.this.mIUpdateCallback.checkEnd(state, true);
                        } else {
                            Log.d(UpdateManager.TAG, "checkEnd,error...");
                            UpdateManager.this.mIUpdateCallback.checkEnd(state, false);
                        }

                    }
                }).start();
            } else {
                Log.d(TAG, "checkEnd,no files,error...");
                this.mIUpdateCallback.checkEnd(state, false);
            }

        }
    }

    private String getFileMD5(File file) {
        if(!file.isFile()) {
            return null;
        } else {
            MessageDigest digest = null;
            FileInputStream in = null;
            byte[] buffer = new byte[8192];

            try {
                digest = MessageDigest.getInstance("MD5");
                in = new FileInputStream(file);

                while(true) {
                    int bigInt;
                    if((bigInt = in.read(buffer, 0, 8192)) == -1) {
                        in.close();
                        break;
                    }

                    digest.update(buffer, 0, bigInt);
                }
            } catch (Exception var6) {
                var6.printStackTrace();
                return null;
            }

            BigInteger bigInt1 = new BigInteger(1, digest.digest());
            return bigInt1.toString(16);
        }
    }

    private String readMd5FromFile(String path) {
        StringBuffer result = new StringBuffer();
        String line = "";

        try {
            File e = new File(path);
            InputStreamReader isReader = new InputStreamReader(new FileInputStream(e));
            BufferedReader brReader = new BufferedReader(isReader);

            for(line = brReader.readLine(); line != null; line = brReader.readLine()) {
                result.append(line);
            }
        } catch (FileNotFoundException var7) {
            var7.printStackTrace();
        } catch (IOException var8) {
            var8.printStackTrace();
        }

        return result.toString();
    }

    public void addUpdateListener(UpdateManager.IUpdateListener mIUpdateCallback) {
        Log.v("hh", "addCallBack:" + mIUpdateCallback);
        this.mIUpdateCallback = mIUpdateCallback;
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).addCallBack(new UpdateManager.UpdateCallBack(mIUpdateCallback));
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public void updateLcd_927(String path) {
        if(this.mService != null) {
            try {
                ((IUpdateInterface)this.mService).updateLcd_927(path);
            } catch (Exception var3) {
                var3.printStackTrace();
            }

        }
    }

    public static String getMfdVersion() {
        String filename = "/sys/class/lcdtsp_mcu/lcdtsp_mcu/mfd_version";

        try {
            BufferedReader e = new BufferedReader(new FileReader(filename), 256);

            String var3;
            try {
                var3 = e.readLine();
            } finally {
                e.close();
            }

            return var3;
        } catch (IOException var7) {
            var7.printStackTrace();
            return "";
        }
    }

    public interface IUpdateListener {
        void updateStart(int var1);

        void updateEnd(int var1);

        void updateProgerss(int var1, int var2);

        void exception(int var1);

        void updateError(int var1, int var2, int var3, int var4, int var5);

        void checkStart(int var1);

        void checkEnd(int var1, boolean var2);
    }

    private static final class UpdateCallBack extends com.incall.proxy.binder.callback.IUpdateCallbackInterface.Stub {
        private UpdateManager.IUpdateListener mListener;

        public UpdateCallBack(UpdateManager.IUpdateListener mListener) {
            this.mListener = mListener;
        }

        public void updateStart(int state) throws RemoteException {
            this.mListener.updateStart(state);
        }

        public void updateEnd(int state) throws RemoteException {
            this.mListener.updateEnd(state);
        }

        public void updateProgerss(int state, int mProgerss) throws RemoteException {
            this.mListener.updateProgerss(state, mProgerss);
        }

        public void exception(int exception) throws RemoteException {
            this.mListener.exception(exception);
        }

        public void updateError(int frameTotal, int frameCrcErrorCnt, int frameCheckSumErrorCnt, int fileCheckSumErrorCnt, int fileRecycleCnt) throws RemoteException {
            this.mListener.updateError(frameTotal, frameCrcErrorCnt, frameCheckSumErrorCnt, fileCheckSumErrorCnt, fileRecycleCnt);
        }
    }
}
