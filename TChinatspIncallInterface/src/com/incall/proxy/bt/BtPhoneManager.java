package com.incall.proxy.bt;

import java.util.ArrayList;
import java.util.List;

import com.incall.proxy.ServiceConnection;
import com.incall.proxy.ServiceNameManager;
import com.incall.proxy.bt.BtMusicPlayer.OnMetaDataChangedListener;
import com.incall.proxy.bt.BtMusicPlayer.OnPlayStateListener;
import com.incall.proxy.bt.IBtPhone;
import com.incall.proxy.binder.service.IRadioInterface;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.util.Log;

public class BtPhoneManager extends ServiceConnection<IBtPhone>{
	
	private static final String TAG = "BtPhoneManager";
	private static BtPhoneManager instance;
	public static final java.lang.String ACTION_SOURCE_PALYINFO_CHANGED = "com.coagent.ACTION_SOURCE_PALYINFO_CHANGED";
	public static final java.lang.String AUDIO_IN_HOST = "audio_in_host";
	public static final java.lang.String AUTO_CONNECT = "AutoConnect";
	public static final java.lang.String AUTO_CONNECT_ENABLE = "AutoConnectEnable";
	public static final java.lang.String BT_BANDRING = "BandRing";
	public static final java.lang.String BT_DISCOVERABLE = "bt_discoverable";
	public static final java.lang.String BT_OOR_MAX_TIME = "bt_oor_maxtime";
	public static final java.lang.String BT_POWER = "bt_power";
	public static final java.lang.String BT_VERSION = "bt_version";
	public static final String []CALL_STATE ={"ACTIVE","HELD","DIALING","RINGING","INCOMING","WAITING","HELD_BY_RESPONSE_AND_HOLD","TERMINATED"};
	public static final int CALL_STATE_ACTIVE = 0;
	public static final int CALL_STATE_DIALING = 2;
	public static final int CALL_STATE_HELD_BY_RESPONSE_AND_HOLD = 6;
	public static final int CALL_STATE_HELD_CALL = 1;
	public static final int CALL_STATE_INCOMING = 4;
	public static final int CALL_STATE_NO_CALL = -1;
	public static final int CALL_STATE_RINGING = 3;
	public static final int CALL_STATE_TERMINATED = 7;
	public static final int CALL_STATE_WAITING = 5;
	public static final java.lang.String CURRENT_VOL = "current_vol";
	public static final java.lang.String CUSTOM_ID = "custom_id";
	public static final java.lang.String DISCOVERABLE = "discoverable";
	public static final int DISCOVERY_MODE_DISCOVERABLE_CONNECTABLE = 3;
	public static final int DISCOVERY_MODE_DISCOVERABLE_UNCONNECTABLE = 1;
	public static final int DISCOVERY_MODE_UNDISCOVERABLE_CONNECTABLE = 2;
	public static final int DISCOVERY_MODE_UNDISCOVERABLE_UNCONNECTABLE = 0;
	public static final java.lang.String FRIENDLY_NAME = "friendly_name";
	public static final int GELLY = 0;
	public static final int GRADIENT_BASE_VOL = 10;
	public static final java.lang.String LAST_CONNECT_ADDRESS = "LastConnectAddress";
	public static final int MAX_CONTACT_NUM = 3000;
	public static final java.lang.String MSG_ACTION_BLUETOOTH_READY = "coagent.action.bt.READY";
	public static final java.lang.String MSG_ACTION_BTHFP_STATUS = "coagent.action.BT_STATUS";
	public static final java.lang.String MSG_ACTION_CALL_STATUS = "coagent.action.CALL_STATUS";
	public static final java.lang.String MSG_ACTION_CONNECT_REQUEST = "coagent.action.CONNECT_REQUEST";
	public static final java.lang.String MSG_ACTION_CONNECT_STATUS = "coagent.action.bt.CONNECT_STATUS";
	public static final java.lang.String MSG_ACTION_EASYCONN_BT_CONNECTED = "net.easyconn.bt.connected";
	public static final java.lang.String MSG_ACTION_EASYCONN_BT_OPENED = "net.easyconn.bt.opened";
	public static final java.lang.String MSG_ACTION_INCOMINGSMS = "coagent.action.INCOMINGSMS";
	public static final java.lang.String MSG_ACTION_PAIR_REQUEST = "coagent.action.PAIR_REQUEST";
	public static final java.lang.String MSG_ACTION_POWER_STATUS = "coagent.action.bt.POWER_STATUS";
	public static final java.lang.String MSG_ACTION_VOICE_DIAL_STATUS = "coagent.action.VOICE_DIAL_STATUS";
	public static final int PAIR_FAILED_DEVICE_FULL = 4;
	public static final int PAIR_FAILED_MISTAKE_PINCODE = 2;
	public static final int PAIR_FAILED_NO_DEVICE = 3;
	public static final int PAIR_FAILED_UNKNOWN_REASON = 0;
	public static final java.lang.String PAIR_PASSWORD = "pair_password";
	public static final int PAIR_SUCCEEDED = 1;
	public static final java.lang.String PAIRED_MAX_NUM = "paired_max_num";
	public static final java.lang.String POUND_KEY_FIRST = "PoundKeyFirst";
	public static final int PROFILE_AUDIO_CONTROL_CHANNEL = 3;
	public static final int PROFILE_AUDIO_STREAM_CHANNEL = 2;
	public static final int PROFILE_AVRCP_BROWSING_CHANNEL = 9;
	public static final int PROFILE_GATT_CHANNEL = 11;
	public static final int PROFILE_HF_CHANNEL = 0;
	public static final int PROFILE_HFAG_CHANNEL = 5;
	public static final int PROFILE_HID_CONTROL_CHANNEL = 6;
	public static final int PROFILE_HID_INTERRUPT_CHANNEL = 7;
	public static final int PROFILE_OPP_CHANNEL = 14;
	public static final int PROFILE_PAN_CHANNEL = 10;
	public static final int PROFILE_PHONEBOOK_CHANNEL = 12;
	public static final int PROFILE_PIMDATA_CHANNEL = 1;
	public static final int PROFILE_SCO_CHANNEL = 8;
	public static final int PROFILE_SMS_CHANNEL = 13;
	public static final int PROFILE_SPP_CHANNEL = 4;
	public static final java.lang.String STANDBY_CHANGE_BTSTATUS = "standby_changebstatus";
	public static final java.lang.String SYNC_CONTACTS = "sync_contacts";
	public static final java.lang.String SYNC_SMS = "sync_sms";
	public static final int SYNC_STATE_DELETED = 3;
	public static final int SYNC_STATE_NOSYNC = 0;
	public static final int SYNC_STATE_SYNCED = 2;
	public static final int SYNC_STATE_SYNCING = 1;

	public OnA2dpConnectedListener mOnA2dpConnectedListener;
	public OnA2dpMetaDataChangedListener mOnA2dpMetaDataChangedListener;
	public OnBatteryIndicatorChangedListener mOnBatteryIndicatorChangedListener;
	public OnCallLogChangedListener mOnCallLogChangedListener;
	public OnCallLogStateChangedListener mOnCallLogStateChangedListener;
	public OnCallStateListener mOnCallStateListener;
	public OnConnectRequestListener mOnConnectRequestListener;
	public OnContactChangedListener mOnContactChangedListener;
	public OnContactDBStateChangedListener mOnContactDBStateChangedListener;
	public OnContactDownloadProgressChangedListener mOnContactDownloadProgressChangedListener;
	public OnContactStateChangedListener mOnContactStateChangedListener;
	public OnDeviceFoundListener mOnDeviceFoundListener;
	public OnDeviceUpdateListener mOnDeviceUpdateListener;
	public OnHfpConnectedListener mOnHfpConnectedListener;
	public OnPairingRequestListener mOnPairingRequestListener;
	public OnPairingResultListener mOnPairingResultListener;
	public OnPowerChangedListener mOnPowerChangedListener;
	public OnScoChangedListener mOnScoChangedListener;
	public OnSignalStrengthChangedListener mOnSignalStrengthChangedListener;
	public OnSmsChangedListener mOnSmsChangedListener;
	OnMetaDataChangedListener mOnMetaDataChangedListener;
	OnPlayStateListener mOnPlayStateListener;
	
	
	private BtPhoneManager() {
		super(ServiceNameManager.BT_MANAGER_SERVICENAME);
	}
	
	public static BtPhoneManager getInstance() {

		if (instance == null) {
			instance = new BtPhoneManager();
		}
		return instance;
	}



	public void init() {
		LogUtil.d(TAG, "init()");
		mService = IBtPhone.Stub.asInterface(ServiceManager
				.getService("com.carui._bluetooth_ui_service"));

		if (mService != null) {
			try {
				mService.registerCallback(mIBtPhoneCallback);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void unInit() {
		if (mService != null) {
			try {
				mService.unregisterCallback(mIBtPhoneCallback);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	// public void registIBtPhoneCallback(IBtPhoneCallback callback) {
	//
	// LogUtil.d(TAG, "registIBtPhoneCallback()  btPhone==null  : "
	// + (mService == null));
	// if (mService != null) {
	// try {
	// mService.registerCallback(callback);
	// } catch (RemoteException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// }
	// }

	private IBtPhoneCallback mIBtPhoneCallback = new IBtPhoneCallback.Stub() {

		public void onCallLogStateChanged(int syncState)throws RemoteException {
			if(mOnCallLogStateChangedListener!=null){
				mOnCallLogStateChangedListener.onCallLogStateChanged(syncState);
			}
		}
		@Override
		public void onPowerChanged(int powerState) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnPowerChangedListener != null) {
				mOnPowerChangedListener.onPowerChanged(powerState);
			}
		}

		@Override
		public void onPairingRequest(String deviceName, String deviceAddress,
				String pinCode, boolean bSSP) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnPairingRequestListener != null) {
				mOnPairingRequestListener.onPairingRequest(deviceName,
						deviceAddress, pinCode, bSSP);
			}
		}

		@Override
		public void onPairingResult(String deviceAddress, int status)
				throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnPairingResultListener != null) {
				mOnPairingResultListener.onPairingResult(deviceAddress, status);
			}
		}

		@Override
		public void onConnectRequest(String deviceName, String deviceAddress,
				int profile, int index) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnConnectRequestListener != null) {
				mOnConnectRequestListener.onConnectRequest(deviceName,
						deviceAddress, profile, index);
			}
		}

		@Override
		public void onHfpConnectionChanged(int status) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnHfpConnectedListener != null) {
				mOnHfpConnectedListener.onHfpConnectionChanged(status);
			}
		}

		@Override
		public void onA2dpConnectionChanged(int status) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnA2dpConnectedListener != null) {
				mOnA2dpConnectedListener.onA2dpConnectionChanged(status);
			}
		}

		@Override
		public void onDeviceFound(BtDevice btDevice) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnDeviceFoundListener != null) {
				mOnDeviceFoundListener.onDeviceFound(btDevice);
			}
		}

		@Override
		public void onDeviceUpdate() throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnDeviceUpdateListener != null) {
				mOnDeviceUpdateListener.onDeviceUpdate();
			}
		}

		@Override
		public void onCallStateChanged(List<Contact> oldCall,
				List<Contact> newCall) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnCallStateListener != null) {
				mOnCallStateListener.onCallStateChanged(oldCall, newCall);
			}
		}

		@Override
		public void onUpdateContact() throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdateContactState(int syncState) throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdateCallLog() throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdateCallLogState(int syncState) throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpdateSms() throws RemoteException {
			// TODO Auto-generated method stub

		}

		@Override
		public void onReceivedNewSms(Sms sms) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnSmsChangedListener != null) {
				mOnSmsChangedListener.onReceivedNewSms(sms);
			}
		}

		@Override
		public void onMetaDataChanged(A2dpMetaData metaData)
				throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnA2dpMetaDataChangedListener != null) {
				mOnA2dpMetaDataChangedListener.onMetaDataChanged(metaData);
			}
			
			if(mOnMetaDataChangedListener!=null){
				mOnMetaDataChangedListener.onChanged(metaData);
			}
		}

		@Override
		public void onContactDownloadProgressChanged(int total, int downloaded)
				throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnContactDownloadProgressChangedListener != null) {
				mOnContactDownloadProgressChangedListener
						.onContactDownloadProgressChanged(total, downloaded);
			}
		}

		@Override
		public void onContactDBStateChanged(boolean ready)
				throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnContactDBStateChangedListener != null) {
				mOnContactDBStateChangedListener.onContactDBStateChanged(ready);
			}
		}

		@Override
		public void onBatteryIndicatorChanged(int currentValue, int maxValue,
				int minValue) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnBatteryIndicatorChangedListener != null) {
				mOnBatteryIndicatorChangedListener.onBatteryIndicatorChanged(
						currentValue, maxValue, minValue);
			}
		}

		@Override
		public void onSignalStrengthChanged(int currentStrength,
				int maxStrength, int minStrength) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnSignalStrengthChangedListener != null) {
				mOnSignalStrengthChangedListener.onSignalStrengthChanged(
						currentStrength, maxStrength, minStrength);
			}
		}

		@Override
		public void onScoChanged(boolean connected) throws RemoteException {
			// TODO Auto-generated method stub
			if (mOnScoChangedListener != null) {
				mOnScoChangedListener.onScoChanged(connected);
			}
		}

		@Override
		public void onMusicStop() throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onStop();
			}
		}

		@Override
		public void onMusicPlay() throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onPlay();
			}
		}

		@Override
		public void onMusicPause() throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onPause();
			}
		}

		@Override
		public void onMusicProgress(long pos, long time) throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onProgress(pos, time);
			}
		}

		@Override
		public void onStreamChanged(boolean state) throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onStreamChanged(state);
			}
		}

		@Override
		public void onRepeatModeChanged(int mode) throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onRepeatModeChanged(mode);
			}
		}

		@Override
		public void onShuffleModeChanged(int mode) throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnPlayStateListener!=null){
				mOnPlayStateListener.onShuffleModeChanged(mode);
			}
		}

		@Override
		public void onContactStateChanged(int syncState) throws RemoteException {
			// TODO Auto-generated method stub
			if(mOnContactStateChangedListener!=null){
				mOnContactStateChangedListener.onContactChanged(syncState);
			}
		}

	};

	/** A2DP连接状态监听器 */
	public static interface OnA2dpConnectedListener {
		/**
		 * status - BluetoothAdapter.STATE_DISCONNECTED = 0;
		 * BluetoothAdapter.STATE_CONNECTING = 1;
		 * BluetoothAdapter.STATE_CONNECTED = 2;
		 * BluetoothAdapter.STATE_DISCONNECTING = 3;
		 * 
		 * @param status
		 */
		public void onA2dpConnectionChanged(int status);
	}

	/** 歌曲信息监听器 */
	public static interface OnA2dpMetaDataChangedListener {
		public void onMetaDataChanged(A2dpMetaData metaData);
	}

	/** 手机电量值监听器onHfpRemoteBatteryIndicator */
	public static interface OnBatteryIndicatorChangedListener {
		void onBatteryIndicatorChanged(int currentValue, int maxValue,
				int minValue);
	}

	/** 通话记录监听器 */
	public static interface OnCallLogChangedListener {
		public void onCallLogChanged();
	}

	/** 通话记录状态监听器 */
	public static interface OnCallLogStateChangedListener {
		public void onCallLogStateChanged(int syncState);
	}

	/** 通话状态监听器 */
	public static interface OnCallStateListener {
		public void onCallStateChanged(List<Contact> oldCall,
				List<Contact> newCall);
	}

	/** 连接请求监听器 */
	public static interface OnConnectRequestListener {
		public void onConnectRequest(String deviceName, String deviceAddress,
				int profile, int index);
	}

	/** 联系人监听器 */
	public static interface OnContactChangedListener {
		public void onContactChanged();
	}

	/** 电话本数据库状态监听器 */
	public static interface OnContactDBStateChangedListener {
		public void onContactDBStateChanged(boolean ready);
	}

	/** 电话本下载进度监听器 */
	public static interface OnContactDownloadProgressChangedListener {
		public void onContactDownloadProgressChanged(int total, int downloaded);
	}

	/** 联系人监听器 */
	public static interface OnContactStateChangedListener {
		public void onContactChanged(int syncState);
	}

	/** 设备查找监听器 */
	public static interface OnDeviceFoundListener {
		public void onDeviceFound(BtDevice btDevice);
	}

	/** 设备变化监听器 */
	public static interface OnDeviceUpdateListener {
		public void onDeviceUpdate();
	}

	/** HFP连接状态监听器 */
	public static interface OnHfpConnectedListener {
		public void onHfpConnectionChanged(int status);
	}

	/** 配对请求监听器 */
	public static interface OnPairingRequestListener {
		public void onPairingRequest(String deviceName, String deviceAddress,
				String pinCode, boolean bSSP);

	}

	/** 配对结果监听器 */
	public static interface OnPairingResultListener {
		public void onPairingResult(java.lang.String deviceAddress, int status);
	}

	/** 电源状态监听器 */
	public static interface OnPowerChangedListener {
		public void onPowerChanged(int powerState);
	}

	/** 状态监听器 */
	public static interface OnScoChangedListener {
		public void onScoChanged(boolean connected);
	}

	/** 手机信号强度值监听器 */
	public static interface OnSignalStrengthChangedListener {
		public void onSignalStrengthChanged(int currentStrength,
				int maxStrength, int minStrength);
	}

	/** 短信监听器 */
	public static interface OnSmsChangedListener {
		public void onReceivedNewSms(Sms sms);

		public void onSmsChanged();
	}

	/**
	 * 设置蓝牙模块电源
	 * 
	 * @param enable
	 */
	public void setBtPower(boolean enable) {
		Log.d(TAG, "----------------------->setBtPower()");

		if (mService != null) {
			try {
				mService.setBtPower(enable);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 蓝牙电源的设置
	 * 
	 * @return
	 */
	public boolean getBtPowerSetting() {
		Log.d(TAG, "----------------------->getBtPowerSetting()");

		if (mService != null) {
			try {
				return mService.getBtPowerSetting();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @return BluetoothAdapter.STATE_OFF = 10 BluetoothAdapter.STATE_TURNING_ON
	 *         = 11 BluetoothAdapter.STATE_ON = 12
	 *         BluetoothAdapter.STATE_TURNING_OFF = 13 ERROR = -1
	 */
	public int getBtPowerStatus() {
		
		Log.d(TAG, "----------------------->getBtPowerStatus()");

		if (mService != null) {
			try {
				return mService.getBtPowerStatus();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 蓝牙电源正在开启中... 此时无法和remote设备同步
	 * 
	 * @return true if 蓝牙电源正在关闭中
	 */
	public boolean isBtPowerOning() {
		Log.d(TAG, "----------------------->isBtPowerOning()");

		if (mService != null) {
			try {
				return mService.isBtPowerOning();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 蓝牙电源处于已关闭状态
	 * 
	 * @return true if 已关闭
	 */
	public boolean isBtPowerOff() {
		Log.d(TAG, "----------------------->isBtPowerOff()");
		if (mService != null) {
			try {
				return mService.isBtPowerOff();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean isBtPowerOffing(){
		Log.d(TAG, "----------------------->isBtPowerOffing()");
		if (mService != null) {
			try {
				return mService.isBtPowerOffing();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean isBtPowerOn(){
		Log.d(TAG, "----------------------->isBtPowerOn()");
		if (mService != null) {
			try {
				return mService.isBtPowerOn();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	/**
	 * 设置本机蓝牙设备名
	 * 
	 * @param name
	 * @return true if 成功
	 */
	public boolean setFriendlyName(java.lang.String name) {
		Log.d(TAG, "----------------------->setFriendlyName()");

		if (mService != null) {
			try {
				mService.setFriendlyName(name);
				return true;
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取本机蓝牙设备名
	 * 
	 * @return 本机蓝牙设备名
	 */
	public java.lang.String getFriendlyName() {
		Log.d(TAG, "----------------------->getFriendlyName()");
		if (mService != null) {
			try {
				return mService.getFriendlyName();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @return 获取蓝牙"可被发现"的值
	 */
	public boolean getBtDiscoverableSetting() {
		Log.d(TAG, "----------------------->getBtDiscoverableSetting()");
		if (mService != null) {
			try {
				return mService.getBtDiscoverableSetting();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 播放 蓝牙音乐
	 * 
	 * @return true if 成功
	 */
	public boolean musicPlay() {
		Log.d(TAG, "----------------------->musicPlay()");
		if (mService != null) {
			try {
				return mService.musicPlay();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 暂停 蓝牙音乐
	 * 
	 * @return true if 成功
	 */
	public boolean musicPause() {
		Log.d(TAG, "----------------------->musicPause()");
		if (mService != null) {
			try {
				return mService.musicPause();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 停止 蓝牙音乐
	 * 
	 * @return true if 成功
	 */
	public boolean musicStop() {
		Log.d(TAG, "----------------------->musicStop()");
		if (mService != null) {
			try {
				return mService.musicStop();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 播放上一曲 蓝牙音乐
	 * 
	 * @return
	 */
	public boolean musicPrev() {
		Log.d(TAG, "----------------------->musicPrev()");
		if (mService != null) {
			try {
				return mService.musicPrev();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 播放下一曲 蓝牙音乐
	 * 
	 * @return
	 */
	public boolean musicNext() {
		Log.d(TAG, "----------------------->musicNext()");
		if (mService != null) {
			try {
				return mService.musicNext();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 设置歌曲进度位置
	 * 
	 * @param seconds
	 * @return
	 */
	public boolean musicSeekTo(int seconds) {
		Log.d(TAG, "----------------------->musicSeekTo()");
		if (mService != null) {
			try {
				return mService.musicSeekTo(seconds);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 当前歌曲信息
	 * 
	 * @return
	 */
	public A2dpMetaData getMetaData() {
		Log.d(TAG, "----------------------->getMetaData()");
		if (mService != null) {
			try {
				return mService.getMetaData();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 歌曲循环模式
	 * 
	 * @return PLAYER_REPEAT_OFF=1 PLAYER_REPEAT_SINGLE=2 PLAYER_REPEAT_ALL=3
	 *         PLAYER_REPEAT_GROUP=4
	 */
	public int[] getPlayerRepeatModeRange() {
		Log.d(TAG, "----------------------->getPlayerRepeatModeRange()");
		if (mService != null) {
			try {
				return mService.getPlayerRepeatModeRange();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 歌曲随机模式
	 * 
	 * @return PLAYER_SHUFFLE_OFF=1 PLAYER_SHUFFLE_ALL=2 PLAYER_SHUFFLE_GROUP=3
	 */
	public int[] getPlayerShuffleModeRange() {
		Log.d(TAG, "----------------------->getPlayerShuffleModeRange()");
		if (mService != null) {
			try {
				return mService.getPlayerShuffleModeRange();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置歌曲循环模式
	 * 
	 * @param mode
	 *            PLAYER_REPEAT_OFF=1 PLAYER_REPEAT_SINGLE=2 PLAYER_REPEAT_ALL=3
	 *            PLAYER_REPEAT_GROUP=4
	 * @return
	 */
	public boolean setPlayerRepeatMode(int mode) {
		Log.d(TAG, "----------------------->setPlayerRepeatMode()");
		if (mService != null) {
			try {
				return mService.setPlayerRepeatMode(mode);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 设置歌曲随机模式
	 * 
	 * @param mode
	 *            PLAYER_SHUFFLE_OFF=1 PLAYER_SHUFFLE_ALL=2
	 *            PLAYER_SHUFFLE_GROUP=3
	 * @return
	 */
	public boolean setPlayerShuffleMode(int mode) {
		Log.d(TAG, "----------------------->setPlayerShuffleMode()");

		if (mService != null) {
			try {
				return mService.setPlayerShuffleMode(mode);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean musicSetMute(boolean mute) {
		Log.d(TAG, "----------------------->musicSetMute()");

		if (mService != null) {
			try {
				return mService.musicSetMute(mute);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public int getSyncContactsTotal() {
		Log.d(TAG, "----------------------->getSyncContactsTotal()");
		if (mService != null) {
			try {
				return mService.getSyncContactsTotal();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	public int getSyncContactsDownload() {
		Log.d(TAG, "----------------------->getSyncContactsDownload()");

		if (mService != null) {
			try {
				return mService.getSyncContactsDownload();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}

	/**
	 * 获取HFP连接状态
	 * 
	 * @return true if HFP已连接
	 */
	public boolean isHfpConnected() {
		Log.d(TAG, "----------------------->isHfpConnected()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "isHfpConnected()");
				return mService.isHfpConnected();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取A2DP连接状态
	 * 
	 * @return true if A2DP已连接
	 */
	public boolean isA2dpConnected() {
		Log.d(TAG, "----------------------->isA2dpConnected()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "isA2dpConnected()");
				return mService.isA2dpConnected();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 发起配对请求
	 * 
	 * @param address
	 * @param pin_code
	 *            SSP配对模式下无效
	 * @param cod
	 * @return true if 执行成功
	 */
	public boolean startPair(String address, String pin_code, int cod) {
		Log.d(TAG, "----------------------->startPair()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "startPair()");
				return mService.startPair(address, pin_code, cod);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 必须使用stopInquiryDevice结束Inquiry过程
	 * 
	 * @param listener
	 * @return
	 */
	public boolean startInquiryDevice(
			BtPhoneManager.OnDeviceFoundListener listener) {

		Log.d(TAG, "----------------------->startInquiryDevice()");
		if (mService != null) {
			mOnDeviceFoundListener = listener;
			try {
				LogUtil.d(TAG, "startInquiryDevice()");
				return mService.startInquiryDevice();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 与startInquiryDevice成对使用
	 * 
	 * @param listener
	 * @return
	 */
	public boolean stopInquiryDevice(
			BtPhoneManager.OnDeviceFoundListener listener) {

		Log.d(TAG, "----------------------->stopInquiryDevice()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "stopInquiryDevice()");
				return mService.stopInquiryDevice();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取已连接的蓝牙设备
	 * 
	 * @return BtDevice if device is connected, null otherwise
	 */
	public BtDevice getConnectedDevice() {
		Log.d(TAG, "----------------------->getConnectedDevice()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getConnectedDevice()");
				return mService.getConnectedDevice();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取已配对设备列表
	 * 
	 * @return
	 */
	public ArrayList<BtDevice> getPairedDeviceList() {
		Log.d(TAG, "----------------------->getPairedDeviceList()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPairedDeviceList()");
				return (ArrayList<BtDevice>) mService.getPairedDeviceList();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 根据MAC地址判断设备是否已配对
	 * 
	 * @param address
	 * @return true if 已配对
	 */
	public boolean isPairedDevice(String address) {
		Log.d(TAG, "----------------------->isPairedDevice()");

		if (mService != null) {
			try {

				return mService.isPairedDevice(address);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取上次连接的设备MAC地址, SDK会在每次连接后保存LastAddress,此函数可以获得LastAddress记录
	 * 
	 * @return MAC地址 if exist, null otherwise
	 */
	public String getLastConnectAddress() {
		Log.d(TAG, "----------------------->getLastConnectAddress()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getLastConnectAddress()");
				return mService.getLastConnectAddress();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置下次自动连接的设备MAC Address, SDK会在每次连接后保存Last MAC Address,此函数可以修改Last MAC
	 * Address记录
	 * 
	 * @param address
	 * @return true if success, false otherwise
	 */
	public boolean setLastConnectAddress(String address) {
		Log.d(TAG, "----------------------->setLastConnectAddress()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setLastConnectAddress()");
				return mService.setLastConnectAddress(address);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 最多允许配对的设备数
	 * 
	 * @return
	 */
	public int getDeviceMaxNum() {
		Log.d(TAG, "----------------------->getDeviceMaxNum()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getDeviceMaxNum()");
				return mService.getDeviceMaxNum();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 设置最多允许配对的设备数
	 * 
	 * @param maxNum
	 */
	public void setDeviceMaxNum(int maxNum) {
		Log.d(TAG, "----------------------->setDeviceMaxNum()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setDeviceMaxNum()");
				mService.setDeviceMaxNum(maxNum);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置设备是否可见
	 * 
	 * @param enable
	 * @return true if 成功
	 */
	public boolean setDiscoverable(boolean enable) {
		Log.d(TAG, "----------------------->setDiscoverable()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setDiscoverable()");
				return mService.setDiscoverable(enable);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取设备是否可见
	 * 
	 * @return true if 可见
	 */
	public boolean isDiscoverable() {
		Log.d(TAG, "----------------------->isDiscoverable()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isDiscoverable()");
				return mService.isDiscoverable();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 接受该地址配对
	 * 
	 * @param address
	 * @param pin_code
	 * @param bSSP
	 * @return
	 */
	public boolean acceptPair(String address, String pin_code, boolean bSSP) {
		Log.d(TAG, "----------------------->acceptPair()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "acceptPair()");
				return mService.acceptPair(address, pin_code, bSSP);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 拒绝该地址配对
	 * 
	 * @param address
	 * @param bSSP
	 * @return
	 */
	public boolean rejectPair(String address, boolean bSSP) {
		Log.d(TAG, "----------------------->rejectPair()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "rejectPair()");
				return mService.rejectPair(address, bSSP);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean acceptConnectRequest(int nProfileType, int nIndex) {
		Log.d(TAG, "----------------------->acceptConnectRequest()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "acceptConnectRequest()");
				return mService.acceptConnectRequest(nProfileType, nIndex);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean rejectConnectRequest(int nProfileType, int nIndex) {
		Log.d(TAG, "----------------------->rejectConnectRequest()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "rejectConnectRequest()");
				return mService.rejectConnectRequest(nProfileType, nIndex);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 删除该地址的配对信息 这个函数执行可能需要较长时间, 建议放到后台去处理
	 * 
	 * @param address
	 */
	public void deletePairedDevice(String address) {
		Log.d(TAG, "----------------------->deletePairedDevice()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "deletePairedDevice()");
				mService.deletePairedDevice(address);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取排除CALL_STATE_NO_CALL通话状态的Contact列表, 包括 CALL_STATE_INCOMING,
	 * CALL_STATE_ACTIVE, CALL_STATE_DIALING, CALL_STATE_RINGING,
	 * CALL_STATE_WAITING, CALL_STATE_HELD_CALL 状态
	 * 
	 * @return
	 */
	public ArrayList<Contact> getOnGoingContacts() {
		Log.d(TAG, "----------------------->getOnGoingContacts()");

		if (mService != null) {
			try {
				// LogUtil.d(TAG, "getOnGoingContacts()" );
				return (ArrayList<Contact>) mService.getOnGoingContacts();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取 CALL_STATE_INCOMING 通话状态的Contact列表
	 * 
	 * @return
	 */
	public ArrayList<Contact> getInComingContacts() {
		Log.d(TAG, "----------------------->getInComingContacts()");

		if (mService != null) {
			try {

				return (ArrayList<Contact>) mService.getInComingContacts();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取 CALL_STATE_WAITING 通话状态的Contact列表
	 * 
	 * @return
	 */
	public ArrayList<Contact> getWaitingContacts() {
		Log.d(TAG, "----------------------->getWaitingContacts()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getWaitingContacts()");
				return (ArrayList<Contact>) mService.getWaitingContacts();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取 CALL_STATE_ACTIVE 通话状态的Contact列表
	 * 
	 * @return
	 */
	public ArrayList<Contact> getActivedContacts() {
		Log.d(TAG, "----------------------->getActivedContacts()");

		if (mService != null) {
			try {
				// LogUtil.d(TAG, "getActivedContacts()" );
				return (ArrayList<Contact>) mService.getActivedContacts();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}


	/**
	 * 获取通话声音是否在本机输出
	 * 
	 * @return true if SCO connected, or false 只有在电话处于Actived状态时，返回的结果才有效
	 */
	public boolean isAudioInHost() {
		Log.d(TAG, "----------------------->isAudioInHost()");
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "isAudioInHost()");
				return mService.isAudioInHost();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 设置通话声音是否在本机输出 只有在电话处于Actived状态时，设置才有效
	 * 
	 * @param host
	 *            host - = true 声音在本机输出
	 * @return true if 成功
	 */
	public boolean setAudioInHost(boolean host) {
		Log.d(TAG, "----------------------->setAudioInHost()");
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "setAudioInHost()");
				return mService.setAudioInHost(host);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取蓝牙自动连接设置
	 * 
	 * @return
	 */
	public boolean isAutoConnection() {
		Log.d(TAG, "----------------------->isAutoConnection()");
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "setAudioInHost()");
				return mService.isAutoConnection();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 设置蓝牙自动连接
	 * 
	 * @param auto
	 *            auto - = true 蓝牙自动连接
	 */
	public void setAutoConnection(boolean auto) {
		Log.d(TAG, "----------------------->setAutoConnection()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setAudioInHost()");
				mService.setAutoConnection(auto);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据电话号码拨号
	 * 
	 * @param tel
	 *            号码
	 * @return true if 成功
	 */
	public boolean dialByTel(String tel) {
		Log.d(TAG, "----------------------->dialByTel:"+tel);
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "dialByTel()");
				return mService.dialByTel(tel);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	public boolean dialByTel(String tel,int type,Context context){
		Log.d(TAG, "----------------------->dialByTel:"+tel+","+type);
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "dialByTel()");
				return mService.dialByTel(tel);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}


	public int getDialType(Context context) {
		Log.d(TAG, "----------------------->getDialType()");
		return -1;
		/*	if (mService != null) {
		try {
				// LogUtil.d(TAG, "getDialType()" );
				return mService.getDialType(context);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}*/

	}

	/**
	 * 重拨上次的拨号
	 * 
	 * @return true if 成功
	 */
	public boolean reDial() {
		Log.d(TAG, "----------------------->reDial()");
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "reDial()");
				return mService.reDial();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 发送双音多频码
	 * 
	 * @param dtmf
	 *            只接受1个字符, 范围 "0" ~ "9", "*", "#"
	 * @return true if 成功
	 */
	public boolean sendDtmf(java.lang.String dtmf) {
		Log.d(TAG, "----------------------->sendDtmf()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "sendDtmf()");
				return mService.sendDtmf(dtmf);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 1、接听 CALL_STATE_INCOMING 状态的电话 2、接听 CALL_STATE_WAITING 状态的电话, 并保持当前的通话
	 * 
	 * @return true if 成功
	 */
	public boolean pickUpCall() {
		Log.d(TAG, "----------------------->pickUpCall()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "pickUpCall()");
				return mService.pickUpCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 接听 CALL_STATE_WAITING 状态的电话, 并挂断当前的通话
	 * 
	 * @return true if 成功
	 */
	public boolean pickUpWaitingCallAndHangUpCurrentCall() {
		Log.d(TAG, "----------------------->pickUpWaitingCallAndHangUpCurrentCall()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "pickUpWaitingCallAndHangUpCurrentCall()");
				return mService.pickUpWaitingCallAndHangUpCurrentCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 当有通话处于 CALL_STATE_HELD_CALL 状态时, 此API可以交换当前通话与Held的通话 的状态
	 * 
	 * @return
	 */
	public boolean switchHeldCall() {
		Log.d(TAG, "----------------------->switchHeldCall()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "switchHeldCall()");
				return mService.switchHeldCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 1、挂断当前处于 CALL_STATE_ACTIVE 状态的电话 2、当有来电处于 CALL_STATE_WAITING 状态时,
	 * 此API与pickUpWaitingCallAndHangUpCurrentCall()具有相同的功能
	 * 
	 * @return true if 成功
	 */
	public boolean hangUpCurrentCall() {
		Log.d(TAG, "----------------------->hangUpCurrentCall()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "hangUpCurrentCall()");
				return mService.hangUpCurrentCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 挂断处于 CALL_STATE_INCOMING 状态的来电
	 * 
	 * @return
	 */
	public boolean hangUpIncomingCall() {
		Log.d(TAG, "----------------------->hangUpIncomingCall()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "hangUpIncomingCall()");
				return mService.hangUpIncomingCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 挂断处于 CALL_STATE_WAITING 状态的来电
	 * 
	 * @return
	 */
	public boolean hangUpWaitingCall() {
		Log.d(TAG, "----------------------->hangUpWaitingCall()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "hangUpWaitingCall()");
				return mService.hangUpWaitingCall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 请求连接/断开指定MAC地址的设备
	 * 
	 * @param address
	 * @param connection
	 *            = true 连接, otherwise 断开
	 * @return
	 */
	public boolean reqPairedDeviceConnection(String address, boolean connection) {
		Log.d(TAG, "----------------------->reqPairedDeviceConnection()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "reqPairedDeviceConnection()");
				return mService.reqPairedDeviceConnection(address, connection);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 获取正在连接or已连接or正在断开的设备地址
	 * 
	 * @return
	 */
	public String getConnectingConnectedDisconnectingDeviceAddress() {
		Log.d(TAG, "----------------------->getConnectingConnectedDisconnectingDeviceAddress()");
		if (mService != null) {
			try {
				LogUtil.d(TAG,
						"getConnectingConnectedDisconnectingDeviceAddress()");
				return mService
						.getConnectingConnectedDisconnectingDeviceAddress();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @return BluetoothAdapter.STATE_DISCONNECTED = 0;
	 *         BluetoothAdapter.STATE_CONNECTING = 1;
	 *         BluetoothAdapter.STATE_CONNECTED = 2;
	 *         BluetoothAdapter.STATE_DISCONNECTING = 3; ERROR = -1
	 */
	public int getHfpConnectStatus() {
		Log.d(TAG, "----------------------->getHfpConnectStatus()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getHfpConnectStatus()");
				return mService.getHfpConnectStatus();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 
	 * @return BluetoothAdapter.STATE_DISCONNECTED = 0;
	 *         BluetoothAdapter.STATE_CONNECTING = 1;
	 *         BluetoothAdapter.STATE_CONNECTED = 2;
	 *         BluetoothAdapter.STATE_DISCONNECTING = 3; ERROR = -1
	 */
	public int getA2dpConnectStatus() {
		Log.d(TAG, "----------------------->getA2dpConnectStatus()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getA2dpConnectStatus()");
				return mService.getA2dpConnectStatus();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 设置联系人同步
	 * 
	 * @param enable
	 */
	public void setSyncContacts(boolean enable) {
		Log.d(TAG, "----------------------->setSyncContacts()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setSyncContacts()");
				mService.setSyncContacts(enable);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取联系人同步设置
	 * 
	 * @return
	 */
	public boolean getSyncContacts() {
		Log.d(TAG, "----------------------->getSyncContacts()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getSyncContacts()");
				return mService.getSyncContacts();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 设置短信同步
	 * 
	 * @param enable
	 */
	public void setSyncSms(boolean enable) {
		Log.d(TAG, "----------------------->setSyncSms()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setSyncSms()");
				mService.setSyncSms(enable);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取短信同步设置
	 * 
	 * @return
	 */
	public boolean getSyncSms() {
		Log.d(TAG, "----------------------->getSyncSms()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getSyncSms()");
				return mService.getSyncSms();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 下载联系人和通话记录
	 */
	public void downLoadAllPhoneBook() {
		Log.d(TAG, "----------------------->downLoadAllPhoneBook()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "downLoadAllPhoneBook()");
				mService.downLoadAllPhoneBook();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载联系人
	 */
	public void downLoadUserPhoneBook() {
		Log.d(TAG, "----------------------->downLoadUserPhoneBook()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "downLoadUserPhoneBook()");
				mService.downLoadUserPhoneBook();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载指定数目的通话记录
	 * 
	 * @param count
	 *            数目
	 */
	public void downLoadCallLog(int count) {
		Log.d(TAG, "----------------------->downLoadCallLog()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "downLoadCallLog()");
				mService.downLoadCallLog(count);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 下载指定数目的最近短信
	 * 
	 * @param count
	 *            数目
	 */
	public void downLoadSms(int count) {
		Log.d(TAG, "----------------------->downLoadSms()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "downLoadSms()");
				mService.downLoadSms(count);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 是否电话正在下载中
	 * 
	 * @return
	 */
	public boolean isPhonebookDownloading() {
		Log.d(TAG, "----------------------->isPhonebookDownloading()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isPhonebookDownloading()");
				return mService.isPhonebookDownloading();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}
	
	/**
	 * 是否电话/短信正在下载中
	 * 
	 * @return
	 */
	public boolean isPhonebookOrSmsDownloading() {
		Log.d(TAG, "----------------------->isPhonebookOrSmsDownloading()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "isPhonebookOrSmsDownloading()");
				return mService.isPhonebookOrSmsDownloading();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 是否联系人同步已完成 或 超时
	 * 
	 * @return
	 */
	public boolean isContactsSyncComplete() {
		Log.d(TAG, "----------------------->isContactsSyncComplete()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isContactsSyncComplete()");
				return mService.isContactsSyncComplete();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 是否联系人同步成功
	 * 
	 * @return
	 */
	public boolean isContactsSyncSuccess() {
		Log.d(TAG, "----------------------->isContactsSyncSuccess()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isContactsSyncSuccess()");
				return mService.isContactsSyncSuccess();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @return 是否通话记录同步已完成 或 超时
	 */
	public boolean isCallLogsSyncComplete() {
		Log.d(TAG, "----------------------->isCallLogsSyncComplete()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isCallLogsSyncComplete()");
				return mService.isCallLogsSyncComplete();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 是否通话记录同步已成功
	 * 
	 * @return
	 */
	public boolean isCallLogsSyncSuccess() {
		Log.d(TAG, "----------------------->isCallLogsSyncSuccess()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isCallLogsSyncSuccess()");
				return mService.isCallLogsSyncSuccess();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 
	 * @return -1 ERROR
	 */
	public int getCallLogsSyncState() {
		Log.d(TAG, "----------------------->getCallLogsSyncState()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getCallLogsSyncState()");
				return mService.getCallLogsSyncState();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 
	 * @return -1 ERROR
	 */
	public int getContactsSyncState() {
		Log.d(TAG, "----------------------->getContactsSyncState()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getContactsSyncState()");
				return mService.getContactsSyncState();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 
	 * @return 是否短信同步已完成
	 */
	public boolean isSmsesSyncComplete() {
		Log.d(TAG, "----------------------->isSmsesSyncComplete()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isSmsesSyncComplete()");
				return mService.isSmsesSyncComplete();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 是否短信同步已成功
	 * 
	 * @return
	 */
	public boolean isSmsesSyncSuccess() {
		Log.d(TAG, "----------------------->isSmsesSyncSuccess()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isSmsesSyncSuccess()");
				return mService.isSmsesSyncSuccess();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public ArrayList<Contact> getContacts(Context context, BtDevice btDevice) {
		Log.d(TAG, "----------------------->getContacts2()");
/*		if (mService != null) {
			try {
				// LogUtil.d(TAG, "getContacts()" );
				return mService.getContacts_ext2(context, btDevice);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				// e.printStackTrace();
			}
		}*/
		return null;
	}
	public int getCallCount(){
		Log.d(TAG, "----------------------->getCallCount()");
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "getContactsTotal()");
				return mService.getCallCount();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 0;
	}
	/**
	 * 获取已经同步的联系人总数
	 * 
	 * @return 联系人总数
	 */
	public int getContactsTotal() {
		Log.d(TAG, "----------------------->getContactsTotal()");
		if (mService != null) {
			try {
				//LogUtil.d(TAG, "getContactsTotal()");
				return mService.getContactsTotal();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 获取同步通讯录的结果原因
	 * 
	 * @return
	 */
	public int getReasonPbapDownload() {
		Log.d(TAG, "----------------------->getReasonPbapDownload()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getReasonPbapDownload()");
				return mService.getReasonPbapDownload();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 获取已经同步的联系人列表
	 * 
	 * @return
	 */
	public ArrayList<Contact> getContacts() {
		Log.d(TAG, "----------------------->getContacts()");
		LogUtil.d(TAG, "mService!=null :" + (mService != null));
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getContacts()");
				return (ArrayList<Contact>)mService.getContacts();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取已经同步的联系人列表
	 * 
	 * @param start
	 * @param end
	 * @return
	 */
	public ArrayList<Contact> getContacts(int start, int end) {
		
		Log.d(TAG, "----------------------->getContacts("+start+","+end+")");
		
		LogUtil.d(TAG, "mService!=null :" + (mService != null));
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getReasonPbapDownload()");
				return (ArrayList<Contact>) mService.getContacts_ext(start, end);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取已经同步的通话记录列表
	 * 
	 * @return
	 */
	public ArrayList<CallLog> getCallLogs() {
		
		Log.d(TAG, "----------------------->getCallLogs()");

		
		LogUtil.d(TAG, "mService!=null :" + (mService != null));
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getCallLogs()");
				return (ArrayList<CallLog>) mService.getCallLogs();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取已经同步的短信列表
	 * 
	 * @return
	 */
	public java.util.ArrayList<Sms> getSmses() {
		Log.d(TAG, "----------------------->getSmses()");

		if (mService != null) {
			try {
				// LogUtil.d(TAG, "getSmses()" );
				return (ArrayList<Sms>) mService.getSmses();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 设置指定短信为已读
	 * 
	 * @param sms
	 * @return
	 */
	public boolean setSmsRead(Sms sms) {
		Log.d(TAG, "----------------------->setSmsRead()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setSmsRead()");
				return mService.setSmsRead(sms);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 给指定电话号码发送短信
	 * 
	 * @param tel
	 * @param sms
	 * @return
	 */
	public boolean sendSMS(String tel, String sms) {
		Log.d(TAG, "----------------------->sendSMS()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "sendSMS()");
				return mService.sendSMS(tel, sms);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	public java.lang.String HanziToPinyin(String hanzi) {
		Log.d(TAG, "----------------------->HanziToPinyin()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "HanziToPinyin()");
				return mService.HanziToPinyin(hanzi);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getSDKVersion() {
		Log.d(TAG, "----------------------->getSDKVersion()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getSDKVersion()");
				return mService.getSDKVersion();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 
	 * @return 手机信号质量 0 ~ 100 返回 -1表示操作出错
	 */
	public int getSignalQuality() {
		Log.d(TAG, "----------------------->getSignalQuality()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getSignalQuality()");
				return mService.getSignalQuality();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 手机电量 0 ~ 100 返回 -1表示操作出错
	 * 
	 * @return
	 */
	public int getBatteryCharge() {
		Log.d(TAG, "----------------------->getBatteryCharge()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getBatteryCharge()");
				return mService.getBatteryCharge();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return -1;
	}

	/**
	 * 运营商名称
	 * 
	 * @return
	 */
	public java.lang.String getOperatorInfo() {
		Log.d(TAG, "----------------------->getOperatorInfo()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getOperatorInfo()");
				return mService.getOperatorInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 联系人数据库是否就绪
	 * 
	 * @return
	 */
	public boolean isContactDBReady() {
		Log.d(TAG, "----------------------->isContactDBReady()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isContactDBReady()");
				return mService.isContactDBReady();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * This function must be used on streaming start, otherwise return an error.
	 * 
	 * @param volume
	 *            0.0f ~ 1.0f
	 * @return true if successful
	 */
	public boolean setA2dpStreamVol(float volume) {
		Log.d(TAG, "----------------------->setA2dpStreamVol()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setA2dpStreamVol()");
				return mService.setA2dpStreamVol(volume);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 设置奇怪名字(索引为#)的联系人在列表中的显示位置
	 * 
	 * @param isPoundKeyFirst
	 *            isPoundKeyFirst - true:#在前 false:#在后
	 */
	public void setPoundKeyFirst(boolean isPoundKeyFirst) {
		Log.d(TAG, "----------------------->setPoundKeyFirst()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setPoundKeyFirst()");
				mService.setPoundKeyFirst(isPoundKeyFirst);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 停止自动连接，让用户可以手动连接
	 */
	public void stopAutoconnect() {
		Log.d(TAG, "----------------------->stopAutoconnect()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "stopAutoconnect()");
				mService.stopAutoconnect();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * time - 超时时间，单位为秒
	 * 
	 * @param time
	 */
	public void setBtOorMaxtime(int time) {
		Log.d(TAG, "----------------------->setBtOorMaxtime()");
		// if (mService != null) {
		// try {
		// LogUtil.d(TAG, "setBtOorMaxtime()" );
		// mService.setBtOorMaxtime();
		// } catch (RemoteException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }
	}

	/**
	 * 主动刷新蓝牙音乐的AVRCP状态
	 * 
	 * @return
	 */
	public boolean getA2dpAudioPlayStatus() {
		Log.d(TAG, "----------------------->getA2dpAudioPlayStatus()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getA2dpAudioPlayStatus()");
				return mService.getA2dpAudioPlayStatus();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 清除同步数据（Contacts, CallLog）
	 */
	public void deleteSyncData() {
		Log.d(TAG, "----------------------->deleteSyncData()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "deleteSyncData()");
				mService.deleteSyncData();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isIncall() {
		Log.d(TAG, "----------------------->isIncall()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isIncall()");
				return mService.isIncall();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 请求修改Voice dial 状态
	 * 
	 * @param enable
	 * @return
	 */
	public boolean reqHfpVoiceDial(boolean enable) {
		Log.d(TAG, "----------------------->reqHfpVoiceDial()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "reqHfpVoiceDial()");
				return mService.reqHfpVoiceDial(enable);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}

	/**
	 * 播放电话铃声
	 */
	public void startCallRingtone() {
		Log.d(TAG, "----------------------->startCallRingtone()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "startCallRingtone()");
				mService.startCallRingtone();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 停止电话铃声
	 */
	public void stopCallRingtone() {
		Log.d(TAG, "----------------------->stopCallRingtone()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "stopCallRingtone()");
				mService.stopCallRingtone();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setStandbyChangeBtStatus(boolean enable) {
		Log.d(TAG, "----------------------->setStandbyChangeBtStatus()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setStandbyChangeBtStatus()");
				mService.setStandbyChangeBtStatus(enable);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setCustomId(int id) {
		Log.d(TAG, "----------------------->setCustomId()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "setCustomId()");
				mService.setCustomId(id);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean isIPhoneMobile(String address) {
		Log.d(TAG, "----------------------->isIPhoneMobile()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "isIPhoneMobile()");
				return mService.isIPhoneMobile(address);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return false;
	}


	/**
	 * 添加一个listener,监听蓝牙power状态. 当你设置蓝牙power后,蓝牙将在执行完毕power操作后回调此listener.
	 * 期间应该禁止新的power
	 * 
	 * @param listener
	 */
	public void registerOnPowerChangedListener(
			BtPhoneManager.OnPowerChangedListener listener) {

		mOnPowerChangedListener = listener;
	}

	/**
	 * 
	 * @param listener
	 */
	public void unregisterOnPowerChangedListener(
			BtPhoneManager.OnPowerChangedListener listener) {
		mOnPowerChangedListener = null;
	}

	/**
	 * 设置配对请求监听器
	 * 
	 * @param listener
	 */
	public void registerOnPairingRequestListener(
			BtPhoneManager.OnPairingRequestListener listener) {
		mOnPairingRequestListener = listener;
	}

	/**
	 * 取消配对请求监听器
	 * 
	 * @param listener
	 */
	public void unregisterOnPairingRequestListener(
			BtPhoneManager.OnPairingRequestListener listener) {
		mOnPairingRequestListener = null;
	}

	/**
	 * 设置配对结果监听器
	 * 
	 * @param listener
	 */
	public void registerOnPairingResultListener(
			BtPhoneManager.OnPairingResultListener listener) {
		mOnPairingResultListener = listener;
	}

	/**
	 * 取消配对结果监听
	 * 
	 * @param listener
	 */
	public void unregisterOnPairingResultListener(
			BtPhoneManager.OnPairingResultListener listener) {
		mOnPairingResultListener = null;
	}

	/**
	 * 设置连接请求监听器
	 * 
	 * @param listener
	 */
	public void registerOnConnectRequestListener(
			BtPhoneManager.OnConnectRequestListener listener) {
		mOnConnectRequestListener = listener;
	}

	/**
	 * 取消连接请求监听器
	 * 
	 * @param listener
	 */
	public void unregisterOnConnectRequestListener(
			BtPhoneManager.OnConnectRequestListener listener) {
		mOnConnectRequestListener = null;
	}

	/**
	 * 设置HFP连接状态监听器
	 * 
	 * @param listener
	 */
	public void registerOnHfpConnectedListener(
			BtPhoneManager.OnHfpConnectedListener listener) {
		mOnHfpConnectedListener = listener;
	}

	/**
	 * 取消HFP连接状态监听器
	 */
	public void unregisterOnHfpConnectedListener(
			BtPhoneManager.OnHfpConnectedListener listener) {
		mOnHfpConnectedListener = null;
	}

	/**
	 * 设置A2DP连接状态监听器
	 * 
	 * @param listener
	 */
	public void registerOnA2dpConnectedListener(
			BtPhoneManager.OnA2dpConnectedListener listener) {
		mOnA2dpConnectedListener = listener;
	}

	/**
	 * 取消A2DP连接状态监听器
	 * 
	 * @param listener
	 */
	public void unregisterOnA2dpConnectedListener(
			BtPhoneManager.OnA2dpConnectedListener listener) {
		mOnA2dpConnectedListener = null;
	}

	/**
	 * 设置设备查找监听器
	 * 
	 * @param listener
	 */
	public void registerOnDeviceFoundListener(
			BtPhoneManager.OnDeviceFoundListener listener) {
		mOnDeviceFoundListener = listener;
	}

	/**
	 * 取消设备查找监听
	 * 
	 * @param listener
	 */
	public void unregisterOnDeviceFoundListener(
			BtPhoneManager.OnDeviceFoundListener listener) {
		mOnDeviceFoundListener = null;
	}

	/**
	 * 监听已配对设备变化
	 * 
	 * @param listener
	 */
	public void registerOnDeviceDeleteListener(
			BtPhoneManager.OnDeviceUpdateListener listener) {
		mOnDeviceUpdateListener = listener;
	}

	/**
	 * 取消监听已配对设备变化
	 * 
	 * @param listener
	 */
	public void unregisterOnDeviceDeleteListener(
			BtPhoneManager.OnDeviceUpdateListener listener) {
		mOnDeviceUpdateListener = null;
	}

	/**
	 * 监听电话状态
	 * 
	 * @param listener
	 */
	public void registerOnCallStateListener(
			BtPhoneManager.OnCallStateListener listener) {
		mOnCallStateListener = listener;
	}

	/**
	 * 取消监听电话状态
	 * 
	 * @param listener
	 */
	public void unregisterOnCallStateListener(
			BtPhoneManager.OnCallStateListener listener) {
		mOnCallStateListener = null;
	}

	/**
	 * 监听联系人变化
	 * 
	 * @param listener
	 */
	public void registerOnContactChangedListener(
			BtPhoneManager.OnContactChangedListener listener) {
		mOnContactChangedListener = listener;
	}

	/**
	 * 取消监听联系人变化
	 * 
	 * @param listener
	 */
	public void unregisterOnContactChangedListener(
			BtPhoneManager.OnContactChangedListener listener) {
		mOnContactChangedListener = null;
	}

	/**
	 * 监听联系人变化
	 * 
	 * @param listener
	 */
	public void registerOnContactStateChangedListener(
			BtPhoneManager.OnContactStateChangedListener listener) {
		mOnContactStateChangedListener = listener;
	}

	/**
	 * 取消监听联系人变化
	 * 
	 * @param listener
	 */
	public void unregisterOnContactStateChangedListener(
			BtPhoneManager.OnContactStateChangedListener listener) {
		mOnContactStateChangedListener = null;
	}

	/**
	 * 设置通话记录监听器
	 * 
	 * @param listener
	 */
	public void registerOnCallLogChangedListener(
			BtPhoneManager.OnCallLogChangedListener listener) {
		mOnCallLogChangedListener = listener;
	}

	/**
	 * 取消通话记录监听
	 * 
	 * @param listener
	 */
	public void unregisterOnCallLogChangedListener(
			BtPhoneManager.OnCallLogChangedListener listener) {
		mOnCallLogChangedListener = null;
	}

	/**
	 * 设置通话记录状态监听器
	 * 
	 * @param listener
	 */
	public void registerOnCallLogStateChangedListener(
			BtPhoneManager.OnCallLogStateChangedListener listener) {
		mOnCallLogStateChangedListener = listener;
	}

	/**
	 * 取消通话记录状态监听
	 * 
	 * @param listener
	 */
	public void unregisterOnCallLogStateChangedListener(
			BtPhoneManager.OnCallLogStateChangedListener listener) {
		mOnCallLogStateChangedListener = null;
	}

	/**
	 * 监听短信变化
	 * 
	 * @param listener
	 */
	public void registerOnSmsChangedListener(
			BtPhoneManager.OnSmsChangedListener listener) {
		mOnSmsChangedListener = listener;
	}

	/**
	 * 取消监听短信变化
	 * 
	 * @param listener
	 */
	public void unregisterOnSmsChangedListener(
			BtPhoneManager.OnSmsChangedListener listener) {
		mOnSmsChangedListener = null;
	}

	/**
	 * 监听ID3变化
	 * 
	 * @param listener
	 */
	public void registerOnA2dpMetaDataChangedListener(
			BtPhoneManager.OnA2dpMetaDataChangedListener listener) {
		mOnA2dpMetaDataChangedListener = listener;
	}

	/**
	 * 取消监听ID3变化
	 * 
	 * @param listener
	 */
	public void unregisterOnA2dpMetaDataChangedListener(
			BtPhoneManager.OnA2dpMetaDataChangedListener listener) {
		mOnA2dpMetaDataChangedListener = null;
	}

	/**
	 * 监听电话本下载进度
	 * 
	 * @param listener
	 */
	public void registerOnContactDownloadProgressChangedListener(
			BtPhoneManager.OnContactDownloadProgressChangedListener listener) {
		mOnContactDownloadProgressChangedListener = listener;
	}

	/**
	 * 取消监听电话本下载进度
	 * 
	 * @param listener
	 */
	public void unregisterOnContactDownloadProgressChangedListener(
			BtPhoneManager.OnContactDownloadProgressChangedListener listener) {
		mOnContactDownloadProgressChangedListener = listener;
	}

	/**
	 * 监听电话本数据库状态变化
	 * 
	 * @param listener
	 */
	public void registerOnContactDBStateChangedListener(
			BtPhoneManager.OnContactDBStateChangedListener listener) {
		mOnContactDBStateChangedListener = listener;
	}

	/**
	 * 取消监听电话本数据库状态变化
	 * 
	 * @param listener
	 */
	public void unregisterOnContactDBStateChangedListener(
			BtPhoneManager.OnContactDBStateChangedListener listener) {
		mOnContactDBStateChangedListener = null;
	}

	/**
	 * 监听手机电量
	 * 
	 * @param listener
	 */
	public void registerOnBatteryIndicatorChangedListener(
			BtPhoneManager.OnBatteryIndicatorChangedListener listener) {
		mOnBatteryIndicatorChangedListener = listener;
	}

	/**
	 * 取消监听手机电量
	 * 
	 * @param listener
	 */
	public void unregisterOnBatteryIndicatorChangedListener(
			BtPhoneManager.OnBatteryIndicatorChangedListener listener) {
		mOnBatteryIndicatorChangedListener = null;
	}

	/**
	 * 监听手机信号强度
	 * 
	 * @param listener
	 */
	public void registerOnSignalStrengthChangedListener(
			BtPhoneManager.OnSignalStrengthChangedListener listener) {
		mOnSignalStrengthChangedListener = listener;
	}

	/**
	 * 取消监听手机信号强度
	 * 
	 * @param listener
	 */
	public void unregisterOnSignalStrengthChangedListener(
			BtPhoneManager.OnSignalStrengthChangedListener listener) {
		mOnSignalStrengthChangedListener = null;
	}

	/**
	 * 监听sco状态
	 * 
	 * @param listener
	 */
	public void registerOnScoChangedListener(
			BtPhoneManager.OnScoChangedListener listener) {
		mOnScoChangedListener = listener;
	}

	/**
	 * 取消监听sco状态
	 * 
	 * @param listener
	 */
	public void unregisterOnScoChangedListener(
			BtPhoneManager.OnScoChangedListener listener) {
		mOnScoChangedListener = null;
	}

	protected boolean getServiceConnection() {

		IBinder binder = connectService();
		if (binder != null) {
			this.mService = IBtPhone.Stub.asInterface(binder);
			return true;
		}
		this.mService = null;
		return false;
	}

	@Override
	protected void serviceReConnected() {
		boolean flag = getServiceConnection();
	}
	
	protected int	getPlayerRepeatMode(){
		Log.d(TAG, "----------------------->getPlayerRepeatMode()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayerRepeatMode()");
				return mService.getPlayerRepeatMode();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return -1;
	}
	
	protected int	getPlayerShuffleMode(){
		Log.d(TAG, "----------------------->getPlayerShuffleMode()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayerShuffleMode()");
				return mService.getPlayerShuffleMode();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return -1;
	}
	
	protected String getPlayingAlbum(){
		Log.d(TAG, "----------------------->getPlayingAlbum()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingAlbum()");
				return mService.getPlayingAlbum();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	protected String getPlayingArtist(){
		Log.d(TAG, "----------------------->getPlayingArtist()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingArtist()");
				return mService.getPlayingArtist();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	
	protected String getPlayingGenre(){
		Log.d(TAG, "----------------------->getPlayingGenre()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingGenre()");
				return mService.getPlayingGenre();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	protected long getPlayingPosition(){
		Log.d(TAG, "----------------------->getPlayingPosition()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingPosition()");
				return mService.getPlayingPosition();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	
	protected long getPlayingTime(){
		Log.d(TAG, "----------------------->getPlayingTime()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingTime()");
				return mService.getPlayingTime();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	protected String getPlayingTitle(){
		Log.d(TAG, "----------------------->getPlayingTitle()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingTitle()");
				return mService.getPlayingTitle();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
	}
	
	protected int getPlayingTotal(){
		Log.d(TAG, "----------------------->getPlayingTotal()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingTotal()");
				return mService.getPlayingTotal();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	
	protected int getPlayingTrack(){
		
		Log.d(TAG, "----------------------->getPlayingTrack()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayingTrack()");
				return mService.getPlayingTrack();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return 0;
	}
	protected int getPlayStatus(){
		Log.d(TAG, "----------------------->getPlayStatus()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getPlayStatus()");
				return mService.getPlayStatus();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return -1;
	}
	protected String getRemoteDeviceName(){
		
		Log.d(TAG, "----------------------->getRemoteDeviceName()");
		if (mService != null) {
			try {
				LogUtil.d(TAG, "getRemoteDeviceName()");
				return mService.getRemoteDeviceName();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return null;
		
	}
	protected boolean isA2dpAudioStreaming(){
		
		Log.d(TAG, "----------------------->isA2dpAudioStreaming()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "isA2dpAudioStreaming()");
				return mService.isA2dpAudioStreaming();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	protected boolean isSupportRepeat(){
		Log.d(TAG, "----------------------->isSupportRepeat()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "isSupportRepeat()");
				return mService.isSupportRepeat();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}
	protected boolean isSupportShuffle(){
		Log.d(TAG, "----------------------->isSupportShuffle()");

		if (mService != null) {
			try {
				LogUtil.d(TAG, "isSupportShuffle()");
				return mService.isSupportShuffle();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return false;
	}

	public void setOnMetaDataChangedListener(BtMusicPlayer.OnMetaDataChangedListener listener)
	{
		mOnMetaDataChangedListener = listener;
	}
	public void setOnPlayStateListener(BtMusicPlayer.OnPlayStateListener listener)
	{
		mOnPlayStateListener = listener;
	}
	
	
}
