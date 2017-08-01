//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.incall.proxy.voice;

import android.content.ContentResolver;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemClock;
import android.os.IBinder.DeathRecipient;
import android.provider.Settings.SettingNotFoundException;
import android.provider.Settings.System;
import android.util.Log;
import com.incall.proxy.ServiceConnection;
import com.incall.proxy.binder.service.IVoiceClientInterface;
import com.incall.proxy.binder.service.IVoiceHostInterface;
import com.incall.proxy.binder.service.IVoiceHostInterface.Stub;
import com.incall.proxy.constant.VoiceConstantsDef.AirMode;
import com.incall.proxy.constant.VoiceConstantsDef.AirPower;
import com.incall.proxy.constant.VoiceConstantsDef.AirTemperatureOperate;
import com.incall.proxy.constant.VoiceConstantsDef.AirWindDirection;
import com.incall.proxy.constant.VoiceConstantsDef.AirWindSpeed;
import com.incall.proxy.constant.VoiceConstantsDef.EngineType;
import com.incall.proxy.constant.VoiceConstantsDef.MsgViewOperation;
import com.incall.proxy.constant.VoiceConstantsDef.MsgViewType;
import com.incall.proxy.constant.VoiceConstantsDef.MusicCostomDataType;
import com.incall.proxy.constant.VoiceConstantsDef.MusicCtrl;
import com.incall.proxy.constant.VoiceConstantsDef.MusicGenre;
import com.incall.proxy.constant.VoiceConstantsDef.MusicRepeat;
import com.incall.proxy.constant.VoiceConstantsDef.MusicSource;
import com.incall.proxy.constant.VoiceConstantsDef.RadioBand;
import com.incall.proxy.constant.VoiceConstantsDef.RadioType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.incall.proxy.ServiceNameManager;

public class VoiceManager extends ServiceConnection<IVoiceHostInterface> {
    public static final String SERVICE_NAME_VOICE = ServiceNameManager.SYSTEM_VOICE_SERVICENAME;
    private static VoiceManager mVoiceManager;
    private static IVoiceClientInterface mIVoiceClientInterface = null;
    private static ArrayList<VoiceManager.VoiceChangedListener> VOICECHANGEDLISTENER_LIST = new ArrayList();
    private HashMap<VoiceManager.VoiceMusicChangedListener, VoiceManager.VoiceChangedListener> mMusicChanged_map = new HashMap();
    private HashMap<VoiceManager.VoiceBtChangedListener, VoiceManager.VoiceChangedListener> mBtChanged_map = new HashMap();
    private HashMap<VoiceManager.VoiceRadioChangedListener, VoiceManager.VoiceChangedListener> mRadioChanged_map = new HashMap();
    private HashMap<VoiceManager.VoiceAirChangedListener, VoiceManager.VoiceChangedListener> mAirChanged_map = new HashMap();
    private HashMap<VoiceManager.VoiceTtsChangedListener, VoiceManager.VoiceChangedListener> mTtsChanged_map = new HashMap();
    private HashMap<VoiceManager.IflyParsingListener, VoiceManager.VoiceChangedListener> mFlyParsing_map = new HashMap();
    private HashMap<VoiceManager.IflyInteractResultListener, VoiceManager.VoiceChangedListener> mInteract_map = new HashMap();
    private VoiceManager.CallBack mCallBack;
    private DeathRecipient mDeathRecipient;
    Handler handler = new Handler(Looper.myLooper());

    public static synchronized VoiceManager getInstance() {
        if(mVoiceManager == null) {
            mVoiceManager = new VoiceManager();
        }

        return mVoiceManager;
    }

    private VoiceManager() {
        super(SERVICE_NAME_VOICE);
    }

    protected boolean getServiceConnection() {
        IBinder getServiceObj = this.connectService();
        if(getServiceObj != null) {
            this.mService = Stub.asInterface(getServiceObj);

            try {
                mIVoiceClientInterface = ((IVoiceHostInterface)this.mService).getVoiceClient();
                if(mIVoiceClientInterface == null) {
                    Log.d("VoiceManager", "VoiceServiceHost connected, but VoiceServiceClient not connect, so reconnect!");
                    getServiceObj.unlinkToDeath(this.getDeathRecipient(), 0);
                    this.mService = null;
                    this.mIsConnected = false;
                    this.serviceBinderDied();
                    return false;
                }

                if(mIVoiceClientInterface != null) {
                    mIVoiceClientInterface.asBinder().linkToDeath(this.getDeathRecipient(), 0);
                    Log.d("VoiceManager", "VoiceServiceHost&VoiceServiceClient connected!");
                }
            } catch (RemoteException var3) {
                var3.printStackTrace();
            }

            return true;
        } else {
            this.mService = null;
            mIVoiceClientInterface = null;
            return false;
        }
    }

    protected DeathRecipient getDeathRecipient() {
        if(this.mDeathRecipient == null) {
            this.mDeathRecipient = new DeathRecipient() {
                public void binderDied() {
                    VoiceManager.this.serviceBinderDied();
                }
            };
        }

        return this.mDeathRecipient;
    }

    protected void serviceReConnected() {
        if(mIVoiceClientInterface != null) {
            ArrayList var1 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                if(this.mCallBack != null && mIVoiceClientInterface != null) {
                    try {
                        mIVoiceClientInterface.registerCallBack(this.mCallBack);
                        Log.d("VoiceManager", "serviceReConnected()#registerCallback(aCallback)");
                    } catch (Exception var3) {
                        var3.printStackTrace();
                    }
                }

            }
        }
    }

    protected void serviceDied() {
        super.serviceDied();
        if(mIVoiceClientInterface != null) {
            mIVoiceClientInterface.asBinder().unlinkToDeath(this.getDeathRecipient(), 0);
            mIVoiceClientInterface = null;
        }

        Log.w("VoiceManager", "VoiceServiceClient or VoiceServiceHost is died by serviceDied!!");
    }

    public boolean isEnable() {
        return mIVoiceClientInterface != null;
    }

    public EngineType getEngineType() {
        if(mIVoiceClientInterface != null) {
            try {
                int e = mIVoiceClientInterface.getEngineType();
                if(e >= 0 && e < EngineType.values().length) {
                    return EngineType.values()[e];
                }
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

        return null;
    }

    public synchronized void playOtherTTS(final String ttsText, long playTime, ContentResolver contentResolver) {
        long time;
        try {
            time = System.getLong(contentResolver, "ttstime");
        } catch (SettingNotFoundException var9) {
            time = 0L;
        }

        long delayTime = time - SystemClock.elapsedRealtime();
        if(delayTime < 0L) {
            delayTime = 0L;
        }

        if(delayTime > 30000L) {
            delayTime = 0L;
        }

        System.putLong(contentResolver, "ttstime", SystemClock.elapsedRealtime() + playTime + delayTime);
        Log.v("test", time + " " + delayTime + " " + playTime);
        this.handler.postDelayed(new Runnable() {
            public void run() {
                if(VoiceManager.mIVoiceClientInterface != null) {
                    try {
                        VoiceManager.mIVoiceClientInterface.speakOther(ttsText);
                    } catch (Exception var2) {
                        var2.printStackTrace();
                    }
                } else {
                    Log.e("liulang", "liulang playOtherTTS#mIVoiceClientInterface == null");
                }

            }
        }, delayTime);
    }

    public void playOtherTTS(String ttsText) {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.speakOther(ttsText);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        } else {
            Log.e("liulang", "liulang playOtherTTS#mIVoiceClientInterface == null");
        }

    }

    public void pauseOtherSpeak() {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.pauseOtherSpeak();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void stopOtherSpeak() {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.stopOtherSpeak();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void resumeOtherSpeak() {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.resumeOtherSpeak();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void playNews(String ttsText) {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.speak(ttsText);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    public void stopNewsSpeak() {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.stopSpeak();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void pauseNewsSpeak() {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.pauseSpeak();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void resumeNewsSpeak() {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.resumeSpeak();
            } catch (Exception var2) {
                var2.printStackTrace();
            }
        }

    }

    public void setXFMode(int type) {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.setXFMode(type);
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

    }

    public boolean uploadMusicData(MusicCostomDataType aType, String[] data) {
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.uploadMusicData(aType.index, data);
                return true;
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return false;
    }

    public boolean sendElfData(int aType, String jsonData) {
        Log.d("VoiceManager", "sendElfData");
        if(mIVoiceClientInterface != null) {
            try {
                boolean e = mIVoiceClientInterface.sendElfData(aType, jsonData);
                return e;
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return false;
    }

    public boolean startSpeechClient(int viewType, String fromService) {
        Log.d("VoiceManager", "--startSpeechClient--viewType=" + viewType + "--fromService=" + fromService);
        if(mIVoiceClientInterface != null) {
            try {
                boolean e = mIVoiceClientInterface.startSpeechClient(viewType, fromService);
                return e;
            } catch (Exception var4) {
                var4.printStackTrace();
            }
        }

        return false;
    }

    public boolean requestAudioFocus(boolean hasFocus) {
        Log.d("VoiceManager", "--requestAudioFocus-----hasFocus=" + hasFocus);
        if(mIVoiceClientInterface != null) {
            try {
                mIVoiceClientInterface.requestAudioFocus(hasFocus);
                return true;
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        }

        return false;
    }

    public int sendManualInteract(String requestCode, String packageName, String msg, boolean requireView) {
        Log.d("VoiceManager", "--sendManualInteract-----requestCode=" + requestCode);
        if(mIVoiceClientInterface != null) {
            try {
                int e = mIVoiceClientInterface.sendManualInteract(requestCode, packageName, msg, requireView);
                return e;
            } catch (Exception var6) {
                var6.printStackTrace();
            }
        }

        return -1;
    }

    public int closeManualInteract(String requestCode, String packageName, String msg) {
        Log.d("VoiceManager", "--closeManualInteract-----requestCode=" + requestCode);
        if(mIVoiceClientInterface != null) {
            try {
                int e = mIVoiceClientInterface.closeManualInteract(requestCode, packageName, msg);
                return e;
            } catch (Exception var5) {
                var5.printStackTrace();
            }
        }

        return -1;
    }

    public void addMusicChangedListener(final VoiceManager.VoiceMusicChangedListener aListener) {
        if(aListener != null && !this.mMusicChanged_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aVoiceChangedListener = new VoiceManager.VoiceChangedListener() {
                void onMusicChanged(String id, String forworld, String title, String artist, String album, MusicSource source, MusicGenre aGenre) {
                    aListener.onMusicChanged(id, forworld, title, artist, album, source, aGenre);
                }

                void onCtrlChanged(MusicCtrl aCtrl) {
                    aListener.onCtrlChanged(aCtrl);
                }

                void onRepeatChanged(MusicRepeat aRepeat) {
                    aListener.onRepeatChanged(aRepeat);
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aVoiceChangedListener);
                this.mMusicChanged_map.put(aListener, aVoiceChangedListener);
                this.checkCallBack();
            }
        }
    }

    private void checkCallBack() {
        if(VOICECHANGEDLISTENER_LIST.isEmpty()) {
            try {
                if(mIVoiceClientInterface != null && this.mCallBack != null) {
                    mIVoiceClientInterface.unRegisterCallBack(this.mCallBack);
                }

                this.mCallBack = null;
            } catch (Exception var3) {
                var3.printStackTrace();
            }
        } else if(this.mCallBack == null) {
            this.mCallBack = new VoiceManager.CallBack();

            try {
                if(mIVoiceClientInterface != null) {
                    mIVoiceClientInterface.registerCallBack(this.mCallBack);
                }
            } catch (RemoteException var2) {
                var2.printStackTrace();
            }
        }

    }

    public void removeMusicChangedListener(VoiceManager.VoiceMusicChangedListener aListener) {
        VoiceManager.VoiceChangedListener aVoiceChangedListener = (VoiceManager.VoiceChangedListener)this.mMusicChanged_map.remove(aListener);
        if(aVoiceChangedListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aVoiceChangedListener);
                this.checkCallBack();
            }
        }

    }

    public void addBtChangedListener(final VoiceManager.VoiceBtChangedListener aListener) {
        if(aListener != null && !this.mBtChanged_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aVoiceChangedListener = new VoiceManager.VoiceChangedListener() {
                void onBtPhoneCallChanged(String number) {
                    aListener.onBtPhoneCallChanged(number);
                }

                void onBtPhoneSendMsgChanged(String number, String message) {
                    aListener.onBtPhoneSendMsgChanged(number, message);
                }

                void onBtPhoneViewMsgChanged(MsgViewOperation operation, MsgViewType msgType) {
                    aListener.onBtPhoneViewMsgChanged(operation, msgType);
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aVoiceChangedListener);
                this.mBtChanged_map.put(aListener, aVoiceChangedListener);
                this.checkCallBack();
            }
        }
    }

    public void removeBtChangedListener(VoiceManager.VoiceBtChangedListener aListener) {
        VoiceManager.VoiceChangedListener aVoiceChangedListener = (VoiceManager.VoiceChangedListener)this.mBtChanged_map.remove(aListener);
        if(aVoiceChangedListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aVoiceChangedListener);
                this.checkCallBack();
            }
        }

    }

    public void addRadioChangedListener(final VoiceManager.VoiceRadioChangedListener aListener) {
        if(aListener != null && !this.mRadioChanged_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aVoiceChangedListener = new VoiceManager.VoiceChangedListener() {
                void onRadioChanged(int aFrequency, RadioBand band, String name, RadioType radioType, String aCountry, String aProvince, String aCity) {
                    aListener.onRadioChanged(aFrequency, band, name, radioType, aCountry, aProvince, aCity);
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aVoiceChangedListener);
                this.mRadioChanged_map.put(aListener, aVoiceChangedListener);
                this.checkCallBack();
            }
        }
    }

    public void removeRadioChangedListener(VoiceManager.VoiceRadioChangedListener aListener) {
        VoiceManager.VoiceChangedListener aVoiceChangedListener = (VoiceManager.VoiceChangedListener)this.mRadioChanged_map.remove(aListener);
        if(aVoiceChangedListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aVoiceChangedListener);
                this.checkCallBack();
            }
        }

    }

    public void addAirChangedListener(final VoiceManager.VoiceAirChangedListener aListener) {
        if(aListener != null && !this.mAirChanged_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aVoiceChangedListener = new VoiceManager.VoiceChangedListener() {
                void onPowerChanged(AirPower aPower) {
                    aListener.onPowerChanged(aPower);
                }

                void onModeChanged(AirMode aMode) {
                    aListener.onModeChanged(aMode);
                }

                void onTemperatureChanged(AirTemperatureOperate aOperate, int aTemperature) {
                    aListener.onTemperatureChanged(aOperate, aTemperature);
                }

                void onSpeedChanged(AirWindSpeed aSpeed) {
                    aListener.onSpeedChanged(aSpeed);
                }

                void onDirectionChanged(AirWindDirection aDirection) {
                    aListener.onDirectionChanged(aDirection);
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aVoiceChangedListener);
                this.mAirChanged_map.put(aListener, aVoiceChangedListener);
                this.checkCallBack();
            }
        }
    }

    public void removeAirChangedListener(VoiceManager.VoiceAirChangedListener aListener) {
        VoiceManager.VoiceChangedListener aVoiceChangedListener = (VoiceManager.VoiceChangedListener)this.mAirChanged_map.remove(aListener);
        if(aVoiceChangedListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aVoiceChangedListener);
                this.checkCallBack();
            }
        }

    }

    public void addTtsChangedListener(final VoiceManager.VoiceTtsChangedListener aListener) {
        if(aListener != null && !this.mTtsChanged_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aVoiceChangedListener = new VoiceManager.VoiceChangedListener() {
                public void onTtsPlayChanged(boolean aIsEnd) {
                    aListener.onTtsPlayChanged(aIsEnd);
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aVoiceChangedListener);
                this.mTtsChanged_map.put(aListener, aVoiceChangedListener);
                this.checkCallBack();
            }
        }
    }

    public void removeTtsChangedListener(VoiceManager.VoiceTtsChangedListener aListener) {
        VoiceManager.VoiceChangedListener aVoiceChangedListener = (VoiceManager.VoiceChangedListener)this.mTtsChanged_map.remove(aListener);
        if(aVoiceChangedListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aVoiceChangedListener);
                this.checkCallBack();
            }
        }

    }

    public void addIflyParsingListener(VoiceManager.IflyParsingListener aListener) {
        if(aListener != null && !this.mFlyParsing_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aVoiceParsingListener = new VoiceManager.VoiceChangedListener() {
                public void onDoAction(String actionJson) {
                }

                public void onNLPResult(String actionJson) {
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aVoiceParsingListener);
                this.mFlyParsing_map.put(aListener, aVoiceParsingListener);
                this.checkCallBack();
            }
        }
    }

    public void removeIflyParsingListener(VoiceManager.IflyParsingListener aListener) {
        VoiceManager.VoiceChangedListener aVoiceChangedListener = (VoiceManager.VoiceChangedListener)this.mFlyParsing_map.remove(aListener);
        if(aVoiceChangedListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aVoiceChangedListener);
                this.checkCallBack();
            }
        }

    }

    public void addInteractResultListener(final VoiceManager.IflyInteractResultListener aListener) {
        if(aListener != null && !this.mInteract_map.containsKey(aListener)) {
            VoiceManager.VoiceChangedListener aInteractListener = new VoiceManager.VoiceChangedListener() {
                public void onManualInteractResult(String requestCode, String resultType, String cmdId) {
                    aListener.onManualInteractResult(requestCode, resultType, cmdId);
                }
            };
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.add(aInteractListener);
                this.mInteract_map.put(aListener, aInteractListener);
                this.checkCallBack();
            }
        }
    }

    public void removeInteractResultListener(VoiceManager.IflyInteractResultListener aListener) {
        VoiceManager.VoiceChangedListener aInteractListener = (VoiceManager.VoiceChangedListener)this.mInteract_map.remove(aListener);
        if(aInteractListener != null) {
            ArrayList var3 = VOICECHANGEDLISTENER_LIST;
            synchronized(VOICECHANGEDLISTENER_LIST) {
                VOICECHANGEDLISTENER_LIST.remove(aInteractListener);
                this.checkCallBack();
            }
        }

    }

    private static final class CallBack extends com.incall.proxy.binder.callback.IVoiceCallBackInterface.Stub {
        private Handler mHandler = new Handler(Looper.getMainLooper());

        CallBack() {
        }

        public void onMusicNotify(final String id, final String forworld, final String title, final String artist, final String album, final int source, final int aGenre) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    MusicSource s = source >= 0 && source < MusicSource.values().length?MusicSource.values()[source]:null;
                    MusicGenre g = aGenre >= 0 && aGenre < MusicGenre.values().length?MusicGenre.values()[aGenre]:null;
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var5 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var5.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var5.next();
                            mVoiceChangedListener.onMusicChanged(id, forworld, title, artist, album, s, g);
                        }

                    }
                }
            });
        }

        public void onCtrlNotify(final int aCtrl) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if(aCtrl >= 0 && aCtrl < MusicCtrl.values().length) {
                        synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                            Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                            while(var3.hasNext()) {
                                VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                                mVoiceChangedListener.onCtrlChanged(MusicCtrl.values()[aCtrl]);
                            }
                        }
                    }

                }
            });
        }

        public void onRepeatNotify(final int aRepeat) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    if(aRepeat >= 0 && aRepeat < MusicRepeat.values().length) {
                        synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                            Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                            while(var3.hasNext()) {
                                VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                                mVoiceChangedListener.onRepeatChanged(MusicRepeat.values()[aRepeat]);
                            }
                        }
                    }

                }
            });
        }

        public void onBtPhoneCallNotify(final String number) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onBtPhoneCallChanged(number);
                        }

                    }
                }
            });
        }

        public void onBtPhoneMsgNotify(final String number, final String message) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onBtPhoneSendMsgChanged(number, message);
                        }

                    }
                }
            });
        }

        public void onRadioNotify(final int aFrequency, final int band, final String name, final int radioType, final String aCountry, final String aProvince, final String aCity) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    RadioBand aBand = band >= 0 && band < RadioBand.values().length?RadioBand.values()[band]:null;
                    RadioType aRadioType = radioType >= 0 && radioType < RadioType.values().length?RadioType.values()[radioType]:null;
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var5 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var5.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var5.next();
                            mVoiceChangedListener.onRadioChanged(aFrequency, aBand, name, aRadioType, aCountry, aProvince, aCity);
                        }

                    }
                }
            });
        }

        public void onBtPhoneViewMsgNotify(final int operation, final int msgType) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    MsgViewOperation aBand = operation >= 0 && operation < MsgViewOperation.values().length?MsgViewOperation.values()[operation]:null;
                    MsgViewType aRadioType = msgType >= 0 && msgType < MsgViewType.values().length?MsgViewType.values()[msgType]:null;
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var5 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var5.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var5.next();
                            mVoiceChangedListener.onBtPhoneViewMsgChanged(aBand, aRadioType);
                        }

                    }
                }
            });
        }

        public void onAirPowerNotify(final int aPower) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AirPower aAirPower = aPower >= 0 && aPower < AirPower.values().length?AirPower.values()[aPower]:null;
                    if(aAirPower != null) {
                        synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                            Iterator var4 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                            while(var4.hasNext()) {
                                VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var4.next();
                                mVoiceChangedListener.onPowerChanged(aAirPower);
                            }

                        }
                    }
                }
            });
        }

        public void onAirModeNotify(final int aMode) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AirMode aAirMode = aMode >= 0 && aMode < AirMode.values().length?AirMode.values()[aMode]:null;
                    if(aAirMode != null) {
                        synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                            Iterator var4 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                            while(var4.hasNext()) {
                                VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var4.next();
                                mVoiceChangedListener.onModeChanged(aAirMode);
                            }

                        }
                    }
                }
            });
        }

        public void onAirTemperatureNotify(final int aOperate, final int aTemperature) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AirTemperatureOperate aAirTemperatureOperate = aOperate >= 0 && aOperate < AirTemperatureOperate.values().length?AirTemperatureOperate.values()[aOperate]:null;
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var4 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var4.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var4.next();
                            mVoiceChangedListener.onTemperatureChanged(aAirTemperatureOperate, aTemperature);
                        }

                    }
                }
            });
        }

        public void onAirSpeedNotify(final int aSpeed) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AirWindSpeed aAirWindSpeed = aSpeed >= 0 && aSpeed < AirWindSpeed.values().length?AirWindSpeed.values()[aSpeed]:null;
                    if(aAirWindSpeed != null) {
                        synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                            Iterator var4 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                            while(var4.hasNext()) {
                                VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var4.next();
                                mVoiceChangedListener.onSpeedChanged(aAirWindSpeed);
                            }

                        }
                    }
                }
            });
        }

        public void onAirDirectionNotify(final int aDirection) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    AirWindDirection aAirWindDirection = aDirection >= 0 && aDirection < AirWindDirection.values().length?AirWindDirection.values()[aDirection]:null;
                    if(aAirWindDirection != null) {
                        synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                            Iterator var4 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                            while(var4.hasNext()) {
                                VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var4.next();
                                mVoiceChangedListener.onDirectionChanged(aAirWindDirection);
                            }

                        }
                    }
                }
            });
        }

        public void onTtsPlayNotify(final boolean aIsEnd) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onTtsPlayChanged(aIsEnd);
                        }

                    }
                }
            });
        }

        public void onTtsInterrupted() throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onTtsInterrupted();
                        }

                    }
                }
            });
        }

        public void onNLPResult(final String actionJson) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onNLPResult(actionJson);
                        }

                    }
                }
            });
        }

        public void onDoAction(final String actionJson) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onDoAction(actionJson);
                        }

                    }
                }
            });
        }

        public void onManualInteractResult(final String requestCode, final String resultType, final String cmdId) throws RemoteException {
            this.mHandler.post(new Runnable() {
                public void run() {
                    synchronized(VoiceManager.VOICECHANGEDLISTENER_LIST) {
                        Iterator var3 = VoiceManager.VOICECHANGEDLISTENER_LIST.iterator();

                        while(var3.hasNext()) {
                            VoiceManager.VoiceChangedListener mVoiceChangedListener = (VoiceManager.VoiceChangedListener)var3.next();
                            mVoiceChangedListener.onManualInteractResult(requestCode, resultType, cmdId);
                        }

                    }
                }
            });
        }
    }

    public static class IflyInteractResultListener {
        public IflyInteractResultListener() {
        }

        public void onManualInteractResult(String requestCode, String resultType, String cmdId) {
        }
    }

    public static class IflyParsingListener {
        public IflyParsingListener() {
        }

        public void onNLPResult(String actionJson) {
        }

        public void onDoAction(String actionJson) {
        }
    }

    public static class VoiceAirChangedListener {
        public VoiceAirChangedListener() {
        }

        public void onPowerChanged(AirPower aPower) {
        }

        public void onModeChanged(AirMode aMode) {
        }

        public void onTemperatureChanged(AirTemperatureOperate aOperate, int aTemperature) {
        }

        public void onSpeedChanged(AirWindSpeed aSpeed) {
        }

        public void onDirectionChanged(AirWindDirection aDirection) {
        }
    }

    public static class VoiceBtChangedListener {
        public VoiceBtChangedListener() {
        }

        public void onBtPhoneCallChanged(String number) {
        }

        public void onBtPhoneSendMsgChanged(String number, String message) {
        }

        public void onBtPhoneViewMsgChanged(MsgViewOperation operation, MsgViewType msgType) {
        }
    }

    private static class VoiceChangedListener {
        private VoiceChangedListener() {
        }

        public void onTtsInterrupted() {
        }

        public void onTtsPlayChanged(boolean aIsEnd) {
        }

        void onMusicChanged(String id, String forworld, String title, String artist, String album, MusicSource source, MusicGenre aGenre) {
        }

        void onCtrlChanged(MusicCtrl aCtrl) {
        }

        void onRepeatChanged(MusicRepeat aRepeat) {
        }

        void onBtPhoneCallChanged(String number) {
        }

        void onBtPhoneSendMsgChanged(String number, String message) {
        }

        void onBtPhoneViewMsgChanged(MsgViewOperation operation, MsgViewType msgType) {
        }

        void onRadioChanged(int aFrequency, RadioBand band, String name, RadioType radioType, String aCountry, String aProvince, String aCity) {
        }

        void onPowerChanged(AirPower aPower) {
        }

        void onModeChanged(AirMode aMode) {
        }

        void onTemperatureChanged(AirTemperatureOperate aOperate, int aTemperature) {
        }

        void onSpeedChanged(AirWindSpeed aSpeed) {
        }

        void onDirectionChanged(AirWindDirection aDirection) {
        }

        void onNLPResult(String actionJson) {
        }

        void onDoAction(String actionJson) {
        }

        public void onManualInteractResult(String requestCode, String resultType, String cmdId) {
        }
    }

    public static class VoiceMusicChangedListener {
        public VoiceMusicChangedListener() {
        }

        public void onMusicChanged(String id, String forworld, String title, String artist, String album, MusicSource source, MusicGenre aGenre) {
        }

        public void onCtrlChanged(MusicCtrl aCtrl) {
        }

        public void onRepeatChanged(MusicRepeat aRepeat) {
        }
    }

    public static class VoiceRadioChangedListener {
        public VoiceRadioChangedListener() {
        }

        public void onRadioChanged(int aFrequency, RadioBand band, String name, RadioType radioType, String aCountry, String aProvince, String aCity) {
        }
    }

    public static class VoiceTtsChangedListener {
        public VoiceTtsChangedListener() {
        }

        public void onTtsInterrupted() {
        }

        public void onTtsPlayChanged(boolean aIsEnd) {
        }
    }
}
