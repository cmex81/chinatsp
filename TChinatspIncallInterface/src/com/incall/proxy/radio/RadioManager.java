package com.incall.proxy.radio;

/**
 * Created by zhangwei on 2017/6/22.
 */

import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;

import com.incall.proxy.ServiceConnection;
import com.incall.proxy.ServiceNameManager;
import com.incall.proxy.binder.service.IRadioInterface;

import java.util.HashMap;

public class RadioManager extends ServiceConnection<IRadioInterface> {

	private final HashMap<RadioManager.RadioChangedListener, RadioManager.CallBack> mRadioChanged_map = new HashMap();

	/**
	 * 收音状态信息监听
	 */
	public static interface RadioChangedListener {
		// presetList - 预设列表
		void onPresetListChanged(int[] presetList);

		void onRadioInfoChanged(RadioInfo aRadioInfo);

		/* 当前波段（fm/am）所有的电台列表，不区分fm123,am12. */
		void onRadioListChanged(int[] radioList);

		void onSignalChanged(int aSignalLevel);
	}

	private static final Object object = new Object();
	private static RadioManager instance;

	public static RadioManager getInstance() {
		if (instance == null) {
			synchronized (object) {
				if (instance == null) {
					instance = new RadioManager();
				}
			}
		}
		return instance;
	}
	

	private RadioManager() {
		super(ServiceNameManager.RADIO_MANAGER_SERVICENAME);
	}

	protected boolean getServiceConnection() {

		IBinder binder = connectService();
		if (binder != null) {
			this.mService = IRadioInterface.Stub.asInterface(binder);
			return true;
		}
		this.mService = null;
		return false;
	}

	@Override
	protected void serviceReConnected() {
		boolean flag = getServiceConnection();
	}


	/**
	 * 双屏互动 接口
	 */
	public String[] getBandList() {
		if (this.mService != null) {
			try {
				return this.mService.getBandList();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public String getRadioName() {
		if (this.mService != null) {
			try {
				return this.mService.getRadioName();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public boolean next() {
		if (this.mService != null) {
			try {
				return this.mService.next();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean prev() {
		if (this.mService != null) {
			try {
				return this.mService.prev();
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		return false;
	}






	/**
	 * Radio Jar 包接口
	 */


	/**
	 * 添加当前频点到预设电台列表
	 *
	 * @param index
	 *            当前频点
	 */
	public void addFreqToPresetList(int index) {
		if (mService != null) {
			try {
				mService.addFreqToPresetList(index);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 添加Radio数据变化监听器
	 * 
	 * @param aListener
	 */
	public void addRadioChangedListener(
			RadioManager.RadioChangedListener aListener) {
		if (aListener != null && !this.mRadioChanged_map.containsKey(aListener)) {
//			HashMap var2 = this.mRadioChanged_map;
			synchronized (this.mRadioChanged_map) {
				this.mRadioChanged_map.put(aListener,
						new RadioManager.CallBack(aListener, this.mService));
			}
		}
	}

	/**
	 * 删除监听
	 * 
	 * @param aListener
	 *            收音状态监听
	 */
	public void removeRadioChangedListener(
			RadioManager.RadioChangedListener aListener) {
		if (aListener != null) {
//			HashMap var2 = this.mRadioChanged_map;
			synchronized (this.mRadioChanged_map) {
				RadioManager.CallBack aCallBack = this.mRadioChanged_map.remove(aListener);
				try {
					if (this.mService == null) {
						return;
					}
					this.mService.removeRadioChangedListener(aCallBack);
				} catch (Exception var5) {
					var5.printStackTrace();
				}
			}
		}
	}

	/**
	 * 循环切换收音波段，范围 (FM,AM)/(FM1,FM2,FM3,AM1,AM2)
	 */
	public void bandSwitch() {
		this.bandSwitch(0);
	}

	/**
	 * 收音波段切换。
	 * 
	 * @param band
	 *            band - = 0 循环切换收音波段； band - = 1 切换到 FM1 / FM; band - = 2 切换到
	 *            FM2; band - = 3 切换到 FM3; band - = 4 切换到 AM1 / AM; band - = 5
	 *            切换到 AM2;
	 */
	public void bandSwitch(int band) {
		if (mService != null) {
			try {
				mService.bandSwitch(band);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取预设电台列表
	 * 
	 * @return
	 */
	public int[] getPresetList() {
		if (mService != null) {
			try {
				return mService.getPresetList();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取当前的收音信息
	 */
	public RadioInfo getRadioInfo() {
		if (mService != null) {
			try {
				return mService.getRadioInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取当前波段（FM/AM）所有电台列表，不区分fm123,am12
	 * 
	 * @return
	 */
	public int[] getRadioList() {
		if (mService != null) {
			try {
				return mService.getRadioList();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 服务是否连接 return true 已连接
	 * 
	 * @return
	 */
	public boolean isServiceConnected() {
		return mService != null;
	}

	/**
	 * 播放当前预设电台列表中的一个台。
	 * 
	 * @param index
	 *            预设列表的index
	 */
	public void loadPSFromPresetList(int index) {
		if (mService != null) {
			try {
				mService.loadPSFromPresetList(index);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向下搜索下一个有效电台。
	 */
	public void nextStation() {
		if (mService != null) {
			try {
				mService.nextStation();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 暂停收音，内部其实只是做静音处理
	 */
	public void pause() {
		if (mService != null) {
			try {
				mService.pause(true);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 暂停收音，内部其实只是做静音处理。
	 * 
	 * @param b
	 *            true = 暂停; false = 播放。
	 */
	public void pause(boolean b) {
		if (mService != null) {
			try {
				mService.pause(b);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 播放收音，取消暂停状态
	 */
	public void play() {
		if (mService != null) {
			try {
				mService.play();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向上搜索上一个有效电台
	 */
	public void prevStation() {
		if (mService != null) {
			try {
				mService.prevStation();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 请求更新当前收音信息
	 */
	public void requestUpdateRadioInfo() {
		if (mService != null) {
			try {
				mService.requestUpdateRadioInfo();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 启动收音扫描模式,该模式下,收音循环搜台一遍. 搜到有效台时播放10秒,后继续搜索下一个电台,直到当前BAND的范围搜完
	 */
	public void scan() {
		if (mService != null) {
			try {
				mService.scan();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 启动收音搜索并保存模式,该模式下,收音循环搜台一遍. 搜到有效台时保存到电台列表,并继续搜索下一个电台,直到当前BAND的范围搜完
	 */
	public void search() {
		if (mService != null) {
			try {
				mService.search();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向下步进一个当前BAND的最小单位
	 */
	public void seekDownByStep() {
		if (mService != null) {
			try {
				mService.seekDownByStep();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 向上步进一个当前BAND的最小单位
	 */
	public void seekUpByStep() {
		if (mService != null) {
			try {
				mService.seekUpByStep();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 设置收音频点
	 * 
	 * @param frequency
	 *            设置的收音频点
	 */
	public void setRadioFreq(int frequency) {
		if (mService != null) {
			try {
				mService.setRadioFreq(frequency);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 停止当前搜索、扫描状态
	 */
	public void stop() {
		if (mService != null) {
			try {
				mService.stop();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	private static final class CallBack extends
			com.incall.proxy.binder.callback.IRadioChangedCallback.Stub {
		private Handler mHandler = new Handler();
		private RadioManager.RadioChangedListener mListener;

		CallBack(RadioManager.RadioChangedListener aVoiceChangedListener,
				IRadioInterface aService) {
			this.mListener = aVoiceChangedListener;

			try {
				if (aService != null) {
					aService.addRadioChangedListener(this);
				}
			} catch (Exception var4) {
				var4.printStackTrace();
			}
		}

		@Override
		public void onPresetListChanged(final int[] presetList)
				throws RemoteException {
			this.mHandler.post(new Runnable() {
				public void run() {
					CallBack.this.mListener.onPresetListChanged(presetList);//转给UI回调 
				}
			});
		}

		@Override
		public void onRadioInfoChanged(final RadioInfo aRadioInfo)
				throws RemoteException {
			this.mHandler.post(new Runnable() {
				public void run() {
					CallBack.this.mListener.onRadioInfoChanged(aRadioInfo);
				}
			});
		}

		@Override
		public void onRadioListChanged(final int[] radioList)
				throws RemoteException {
			this.mHandler.post(new Runnable() {
				public void run() {
					CallBack.this.mListener.onRadioListChanged(radioList);
				}
			});

		}

		@Override
		public void onSignalChanged(final int aSignalLevel)
				throws RemoteException {
			this.mHandler.post(new Runnable() {
				public void run() {
					CallBack.this.mListener.onSignalChanged(aSignalLevel);
				}
			});
		}

	}
}
