package com.incall.proxy.setting;

import android.media.AudioManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.os.SystemProperties;
import android.util.Log;

import com.incall.proxy.binder.callback.ISettingCallBackInterface;
import com.incall.proxy.binder.service.ISettingInterface;
import com.incall.proxy.constant.SettingsConstantsDef;
import com.incall.proxy.ServiceConnection;
import java.util.ArrayList;
import java.util.List;

public class SettingManager extends ServiceConnection<ISettingInterface> {

	private final String TAG = "SettingManager";

	/** Set Audio index 音场均衡度调节 Balance 0~18: (F9-R9) */
	public static final int Balance = 8;
	/** Set Audio index 音场衰减度调节 Fade 0~18: (L9-R9) */
	public static final int Fade = 7;
	/** Set Audio index BASS Gain 低音值 0~14 */
	public static final int Bass_Gain = 9;
	/** Set Audio index MIDDLE Gain 中音值 0~14 */
	public static final int Middle_Gain = 10;
	/** Set Audio index Treble Gain 高音值 0~14 */
	public static final int Treble_Gain = 11;
	public static final java.lang.String SERVICE_NAME_SETTINGS = "coagent.settings";

	private List<AudioVolumeChangeListener> mAudioVolumeChangeListeners = new ArrayList<AudioVolumeChangeListener>();
	private List<BrignessNotifyListener> mBrignessNotifyListeners = new ArrayList<BrignessNotifyListener>();
	private List<SettingChangedListener> mSettingChangedListeners = new ArrayList<SettingChangedListener>();
	private List<UsbOverCurrentListener> mUsbOverCurrentListeners = new ArrayList<UsbOverCurrentListener>();
	private Handler mHandler = null;

	private SettingManager() {
		super(SERVICE_NAME_SETTINGS);
		
		log("SettingManager()");
	}

	private void log(String msg) {
		Log.i(TAG, msg);
	}

	protected List<AudioVolumeChangeListener> getAudioVolumeChangeListeners() {
		return mAudioVolumeChangeListeners;
	}

	protected List<BrignessNotifyListener> getBrignessNotifyListeners() {
		return mBrignessNotifyListeners;
	}

	protected List<SettingChangedListener> getSettingChangedListener() {
		return mSettingChangedListeners;
	}

	protected List<UsbOverCurrentListener> getUsbOverCurrentListener() {
		return mUsbOverCurrentListeners;
	}

	protected Handler getHandler() {
		return mHandler;
	}

	private static SettingManager mSettingManager = null;

	/** 获取类实例 */
	public static SettingManager getInstance() {
		if (mSettingManager == null) {
			synchronized (SettingManager.class) {
				if (mSettingManager == null) {
					mSettingManager = new SettingManager();

				}
			}
		}
		return mSettingManager;
	}

	@Override
	protected boolean getServiceConnection() {
		IBinder binder = connectService();
		if (binder != null) {
			this.mService = ISettingInterface.Stub.asInterface(binder);
			try {
				// mService.registerCallBack(mISettingCallBackInterface);
				// mService.registerCallBack(mCallBack);
				log("getServiceConnection() 获取到服务端bind代理，创建并绑定服务端回调接口");
				mHandler = new Handler(Looper.getMainLooper());
				mCallBack = new SettingCallBack(this.mService,mHandler);
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		log("getServiceConnection()  binder:"+binder+",this.mService:"+this.mService);
		this.mService = null;
		return false;
	}

	protected void serviceReConnected() {
		getServiceConnection();
	}

	private SettingCallBack mCallBack = null;// new SettingCallBack();

	public static interface AudioVolumeChangeListener {

		/**
		 * 音量大小变化 Listener
		 * 
		 * @param index
		 *            音量类型序号
		 *            <p>
		 *            0 - Media Volume
		 *            </p>
		 *            <p>
		 *            1 - Phone Volume
		 *            </p>
		 *            <p>
		 *            2 - Navi Volume
		 *            </p>
		 *            <p>
		 *            5 - Beep Volume
		 *            </p>
		 *            <p>
		 *            6 - Radar Volume
		 *            </p>
		 * @param volume
		 *            音量大小
		 * @param needShow
		 *            是否需要显示 true:显示;false:不显示
		 */
		void onAudioVolumeChange(int index, int volume, boolean needShow);
	}

	/** 亮度改变 Listener */
	public static interface BrignessNotifyListener {
		void onBrignessChanged(int aSignalLevel);
	}

	/** 设置/声音信息变化监听接口 */
	public static interface SettingChangedListener {
		void onAudioSettingsInfoChange(AudioSettingsInfo info);

		void onSettingsInfoChange(SettingsInfo info);
	}

	public static interface UsbOverCurrentListener {
		void onUsbOverCurrent(int usbIdx, boolean isOverCurrent);
	}

	public void addAudioVolumeChangeListener(
			SettingManager.AudioVolumeChangeListener aListener) {
		if (aListener != null&& !mAudioVolumeChangeListeners.contains(aListener)) {
			mAudioVolumeChangeListeners.add(aListener);
		}
	}

	public void removeAudioVolumeChangeListener(
			SettingManager.AudioVolumeChangeListener aListener) {
		if (aListener != null) {
			mAudioVolumeChangeListeners.remove(aListener);
		}
	}

	public void addBignessNotifyListener(
			SettingManager.BrignessNotifyListener aListener) {
		if (aListener != null&& !mBrignessNotifyListeners.contains(aListener)) {
			mBrignessNotifyListeners.add(aListener);
		}
	}

	public void removeBignessNotifyListener(
			SettingManager.BrignessNotifyListener aListener) {
		if (aListener != null) {
			mBrignessNotifyListeners.remove(aListener);
		}
	}

	public void addSettingsChangedListener(
			SettingManager.SettingChangedListener aListener) {
		if (aListener != null&&!mSettingChangedListeners.contains(aListener)) {
			mSettingChangedListeners.add(aListener);
		}
	}

	public void removeSettingsChangedListener(
			SettingManager.SettingChangedListener aListener) {
		if (aListener != null) {
			mSettingChangedListeners.remove(aListener);
		}
	}

	public void addUsbOverCurrentListener(
			SettingManager.UsbOverCurrentListener aListener) {
		if (aListener != null&&!mUsbOverCurrentListeners.contains(aListener)) {
			mUsbOverCurrentListeners.add(aListener);
		}
	}

	public void removeUsbOverCurrentListener(
			SettingManager.UsbOverCurrentListener aListener) {
		if (aListener != null) {
			mUsbOverCurrentListeners.remove(aListener);
		}
	}

	/** 获取3D音效 */
	public boolean get3DSound() {
		if(this.mService == null){
			log("this.mService =null");
			return false;
		}
		try {
			log("get3DSound()");
			return this.mService.get3DSound();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/** 获取当前 AudioSettingsInfo 信息，全局变量，多数用于UI数据初始化 */
	public AudioSettingsInfo getAudioSettingsInfo() {
		if(this.mService == null){
			log("this.mService =null");
			return null;
		}
		try {
			log("getAudioSettingsInfo()");
			return this.mService.getAudioSettingsInfo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 获取Beep音量 */
	public SettingsConstantsDef.KEY_VOLUME_LEVEL getBeepVolume() {
		if(this.mService == null){
			log("this.mService =null");
			return null;
		}
		try {
			log("getBeepVolume()");
			return SettingsConstantsDef.KEY_VOLUME_LEVEL.values()[this.mService
					.getBeepVolume()];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/** 获取当前亮度 */
	public int getBrigness() {
		SettingsInfo info = getSettingsInfo();
		if (info != null) {
			return info.currentBrigness;
		}
		Log.v(TAG, "settingsInfo = null");
		return 0;
	}

	/**
	 * 获取时间 RTC（秒）
	 * <p>
	 * 注：此时间是系统开机时的时间， 请开机后尽快设置到系统，避免误差太大，而且开机之后是不会再变的
	 * 
	 * @return
	 */
	public int getCurrentRTC() {
		if(this.mService == null){
			log("this.mService =null");
			return 0;
		}
		try {
			log("getCurrentRTC()");
			return mService.getCurrentRTC();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	/** 获取多媒体音量 */
	public int getMediaVolume() {
		SettingsInfo info = getSettingsInfo();
		if (info != null) {
			return info.mMediaVolume;
		}
		Log.v(TAG, "settingsInfo = null");
		return 0;
	}

	/** 获取导航音量 */
	public int getNaviVolume() {
		SettingsInfo info = getSettingsInfo();
		if (info != null) {
			return info.mNaviVolume;
		}
		Log.v(TAG, "settingsInfo = null");
		return 0;
	}

	/** 获取蓝牙音量 */
	public int getPhoneVolume() {
		SettingsInfo info = getSettingsInfo();
		if (info != null) {
			return info.mPhoneVolume;
		}
		Log.v(TAG, "settingsInfo = null");
		return 0;
	}

	/** 获取Radar音量 */
	public int getRadarVolume() {
		SettingsInfo info = getSettingsInfo();
		if (info != null) {
			return info.mRadarVolume;
		}
		Log.v(TAG, "settingsInfo = null");
		return 0;
	}

	/** 获取当前 SettingsInfo 信息，全局变量，多数用于UI数据初始化 */
	public SettingsInfo getSettingsInfo() {
		Log.e("tsp_jar","tsp_jar");
		if(this.mService == null){
			log("this.mService =null");
			return new SettingsInfo();//null;
		}
		try {
			log("getSettingsInfo()");
			return  this.mService.getSettingsInfo();
		} catch (Exception e) {
			e.printStackTrace();
			return  new SettingsInfo();
		}
		//return null;
	}

	/** 获取系统默认字体 */
	public String getSysDefaultFont() {
		return SystemProperties.get("persist.sys.usedmyfont");
	}

	/** 获取TTS音量 */
	public int getTTSVolume() {
		if(this.mService == null){
			log("this.mService =null");
			return 0;
		}
		// TODO: TTS VOLUME?
		try {
			log("getTTSVolume()");
			return this.mService.getBeepVolume();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * @deprecated 该API禁止使用,请用以下API代替
	 *             <p>
	 *             SourceManager.sendProtocol(byte aGroupId, byte aSubId, byte[]
	 *             aParam)
	 *             <p>
	 *             TestCMD
	 */
	public void sendCMD(int gId, int id, int cmd, int param) {
		throw new RuntimeException("sendCMD() is Forbidden to use!");
	}

	/** 设置3D音效 */
	public void set3DSound(boolean on) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("set3DSound()");
			this.mService.set3DSound(on);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 通知MCU，APU在测试模式。 */
	public void setApuTestMode() {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setApuTestMode()");
			this.mService.setApuTestMode();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置 Audio index : Fade、 Balance、 Bass_Gain、 Middle_Gain、 Treble_Gain
	 * 
	 * @param value
	 *            0~14
	 */
	public void setAudio(int index, int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setAudio()");
			this.mService.setAudioSetting(index, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 自动老化
	 * 
	 * @param on
	 *            true - on false off
	 *            <p>
	 *            注：生产测试使用，APP不用关注
	 */
	public void setAutoAging(boolean on) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setAutoAging()");
			this.mService.setAutoAging(on);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @deprecated 该API为反逻辑,不利于阅读代码,请用以下API代替
	 *             <p>
	 *             SettingManager.setMonitor(boolean on)
	 *             <p>
	 *             设置背光关开
	 * @param off
	 *            true为关背光, false为开背光
	 */
	public void setBackLight(boolean off) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setBackLight()");
			this.mService.setMonitor(!off);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置按键音
	 * 
	 * @param model
	 *            0: Beep closed. 1: Beep Lowering. 2: Beep Melodious
	 */
	public void setBeepModel(int model) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setBeepModel()");
			this.mService.setBeepModel(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置按键声音
	 * 
	 * @param value
	 *            按键音级别
	 */
	public void setBeepVolume(SettingsConstantsDef.KEY_VOLUME_LEVEL value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setBeepVolume()");
			this.mService.setBeepVolume(value.ordinal());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置亮度
	 * 
	 * @param brightness
	 *            0~20
	 */
	public void setBrigness(int brightness) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setBrigness()");
			this.mService.setBrigness(brightness);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置亮度模式（自动/手动s）
	 */
	public void setBrignessAuto(boolean auto) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setBrignessAuto()");
			this.mService.setBrignessAuto(auto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 设置时间 RTC（秒） */
	public void setCurrentRTC(int second) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setCurrentRTC()");
			this.mService.setCurrentRTC(second);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 风扇控制
	 * 
	 * @param model
	 *            0：off 1：low level 2：middle level 3：High level
	 */
	public void setFanControl(int model) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setFanControl()");
			this.mService.setFanControl(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置GEQ
	 * <p>
	 * indext value GrapEQBand1Gain 1 0~24 (-12~12) GrapEQBand2Gain 2 0~24
	 * (-12~12) GrapEQBand3Gain 3 0~24 (-12~12) GrapEQBand4Gain 4 0~24 (-12~12)
	 * GrapEQBand5Gain 5 0~24 (-12~12) GrapEQBand6Gain 6 0~24 (-12~12)
	 * GrapEQBand7Gain 7 0~24 (-12~12) GrapEQBand8Gain 8 0~24 (-12~12)
	 * GrapEQBand9Gain 9 0~24 (-12~12) GrapEQBand1CentreFreq 10 0~2
	 * GrapEQBand2CentreFreq 11 0~2 GrapEQBand3CentreFreq 12 0~2
	 * GrapEQBand4CentreFreq 13 0~2 GrapEQBand5CentreFreq 14 0~2
	 * GrapEQBand6CentreFreq 15 0~2 GrapEQBand7CentreFreq 16 0~2
	 * GrapEQBand8CentreFreq 17 0~2 GrapEQBand9CentreFreq 18 0~2
	 */
	public void setGEQ(int indext, int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setGEQ(" + indext + "," + value + ")");
			this.mService.SetGEQ_Index(indext, value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置GEQ
	 * 
	 * @param value
	 *            G_EQ_MODE_POP， G_EQ_MODE_ROCK， G_EQ_MODE_JAZZ，
	 *            G_EQ_MODE_CLASSICL， G_EQ_MODE_CUSTOM
	 */
	public void setGEQModel(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setGEQ(" + value + ")");
			this.mService.SetGEQ(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 按键测试
	 * 
	 * @param on
	 *            true - on false off
	 *            <p>
	 *            注：生产测试使用，APP不用关注
	 */
	public void setKeyboardTest(boolean on) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setKeyboardTest(" + on + ")");
			this.mService.setKeyboardTest(on);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param mode
	 *            1 - mcu into factory mode; 0 mcu exite factory mode
	 *            <p>
	 *            注：通知MCU进入测试模式，不需要发送心跳包进行通讯
	 */
	public void setMcuFactoryMode(int mode) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setMcuFactoryMode(" + mode + ")");
			this.mService.setMcuFactoryMode(mode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置多媒体声音
	 * 
	 * @param value
	 *            0~40
	 */
	public void setMediaVolume(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setMediaVolume(" + value + ")");
			this.mService.setMediaVolume(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置背光开关
	 * 
	 * @param on
	 *            true为开背光, false为关背光
	 */
	public void setMonitor(boolean on) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setMonitor(" + on + ")");
			this.mService.setMonitor(on);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置静音
	 * 
	 * @param mute
	 */
	public void setMute(boolean mute) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setMute(" + mute + ")");
			this.mService.setMute(mute);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置导航声音
	 * 
	 * @param value
	 *            0~40
	 */
	public void setNaviVolume(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setNaviVolume(" + value + ")");
			this.mService.setNaviVolume(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置蓝牙电话声音
	 * 
	 * @param value
	 *            0~40
	 */
	public void setPhoneVolume(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setPhoneVolume(" + value + ")");
			this.mService.setPhoneVolume(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 指标测试
	 * 
	 * @param on
	 *            true - on false off
	 *            <p>
	 *            注：生产测试使用，APP不用关注
	 */
	public void setQualityTest(boolean on) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setQualityTest(" + on + ")");
			this.mService.setQualityTest(on);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 设置Radar声音 */
	public void setRadarVolume(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setRadarVolume(" + value + ")");
			this.mService.setRadarVolume(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 设置随速调节音量 */
	public void setSpeedAdjustVolume(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setSpeedAdjustVolume(" + value + ")");
			this.mService.setSpeedAdjustVolume(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 设置系统默认字体
	 * 
	 * @param strFontType
	 *            表示字体的类型 传入参数如下cafont1，cafont2，cafont3
	 *            <p>
	 *            cafont1表示方正兰亭黑字体
	 *            <p>
	 *            cafont2表示方正喵呜字体
	 *            <p>
	 *            cafont3表示方正新楷体
	 *            <p>
	 *            目前长安只提供了上面三种字体，如果以后需加入新字体参数设置依次往后累加，如：cafont4/cafont5...
	 *            <p>
	 *            设置字体后需要重启机器后才能替换字体
	 */
	public void setSysDefaultFont(java.lang.String strFontType) {
		SystemProperties.set("persist.sys.usedmyfont", strFontType);
	}

	/** 设置提示音 */
	public void setTTSVolume(int value) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setTTSVolume(" + value + ")");
			this.mService.setTTSVolume(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 设置当前源音音量减 */
	public void setVolumeDown() {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setVolumeDown()");
			this.mService.setVolumeDown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/** 设置当前源音量加 */
	public void setVolumeUp() {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setVolumeUp()");
			this.mService.setVolumeUp();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setSystemFactory(String[] excludePath) {
		if(this.mService == null){
			log("this.mService =null");
			return ;
		}
		try {
			log("setSystemFactory(" + excludePath + ")");
			this.mService.setSystemFactory(excludePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
