package com.incall.proxy.setting;

import java.util.List;

import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;

import com.incall.proxy.binder.callback.ISettingCallBackInterface;
import com.incall.proxy.binder.service.ISettingInterface;
import com.incall.proxy.setting.SettingManager.AudioVolumeChangeListener;
import com.incall.proxy.setting.SettingManager.BrignessNotifyListener;
import com.incall.proxy.setting.SettingManager.SettingChangedListener;
import com.incall.proxy.setting.SettingManager.UsbOverCurrentListener;

public class SettingCallBack extends ISettingCallBackInterface.Stub {

	private static final String TAG = "SettingCallBack";
	private Handler mHandler;
	private SettingManager mSettingManager;

	private void log(String msg) {
		Log.i(TAG, msg);
	}

	public SettingCallBack(ISettingInterface service ,Handler handler) {
		try {
			service.registerCallBack(this);
			this.mHandler = handler;
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onAudioSettingsInfoChange(final AudioSettingsInfo info) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				List<SettingChangedListener> listeners = SettingManager
						.getInstance().getSettingChangedListener();
				log("onAudioSettingsInfoChange()" + listeners.size());
				for (SettingChangedListener listener : listeners) {
					listener.onAudioSettingsInfoChange(info);

				}
			}
		});
	}

	@Override
	public void onBignessNotify(final int aSignalLevel) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				List<BrignessNotifyListener> listeners = SettingManager
						.getInstance().getBrignessNotifyListeners();
				log("onBignessNotify()" + listeners.size());
				for (BrignessNotifyListener listener : listeners) {
					listener.onBrignessChanged(aSignalLevel);
				}
			}
		});
	}

	@Override
	public void onCurrentVolumeNotify(final int index, final int volume,
			final boolean needShow) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				List<AudioVolumeChangeListener> listeners = SettingManager
						.getInstance().getAudioVolumeChangeListeners();
				log("onCurrentVolumeNotify()" + listeners.size());
				for (AudioVolumeChangeListener listener : listeners) {
					listener.onAudioVolumeChange(index, volume, needShow);
				}
			}
		});
	}

	@Override
	public void onSettingsInfoChange(final SettingsInfo info) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {
				List<SettingChangedListener> listeners = SettingManager
						.getInstance().getSettingChangedListener();
				log("onSettingsInfoChange()" + listeners.size());
				for (SettingChangedListener listener : listeners) {
					listener.onSettingsInfoChange(info);
				}
			}
		});
	}

	@Override
	public void onUsbOverCurrent(final int usbIdx, final boolean isOverCurrent) {
		mHandler.post(new Runnable() {
			@Override
			public void run() {

				List<UsbOverCurrentListener> listeners = SettingManager
						.getInstance().getUsbOverCurrentListener();
				log("onUsbOverCurrent()" + listeners.size());
				for (UsbOverCurrentListener listener : listeners) {
					listener.onUsbOverCurrent(usbIdx, isOverCurrent);
				}
			}
		});
	}

}
