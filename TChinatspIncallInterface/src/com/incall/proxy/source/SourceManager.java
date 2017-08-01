package com.incall.proxy.source;

import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import com.incall.proxy.ServiceConnection;
import com.incall.proxy.binder.callback.ISourceCallBackInterface;
import com.incall.proxy.binder.service.ISourceInterface;

import com.incall.proxy.constant.SourceConstantsDef.SourceID;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

public class SourceManager extends ServiceConnection<ISourceInterface> {
	public static final String SERVICE_NAME_SOURCE = "coagent.source";
	private static final String TAG = "SourceManager";
	private static SourceManager mSourceManager;
	private HashMap<SourceChangedListener, SourceChanged> mSourceChangedListener_map = new HashMap();

	private HashMap<AudioReadyListener, SourceChanged> mAudioReadyListener_map = new HashMap();

	private HashMap<ProtocolListener, SourceChanged> mProtocolListener_map = new HashMap();

	private HashMap<HostDeviceStateListener, SourceChanged> mHostDeviceStateListener_map = new HashMap();

	private static ArrayList<SourceChanged> SOURCECHANGED_LIST = new ArrayList();
	private CallBack mCallBack;

	public static synchronized SourceManager getInstance() {
		if (mSourceManager == null) {
			mSourceManager = new SourceManager();
		}
		if (mSourceManager.mService == null)
			mSourceManager.getServiceConnection();
		return mSourceManager;
	}

	private SourceManager() {
		super(SERVICE_NAME_SOURCE);
	}

	protected boolean getServiceConnection() {
		IBinder getServiceObj = connectService();
		if (getServiceObj != null) {
			mService = ISourceInterface.Stub.asInterface(getServiceObj);
			serviceReConnected();
			return true;
		}
		mService = null;
		return false;
	}

	protected void serviceReConnected() {
		synchronized (SOURCECHANGED_LIST) {
			if ((mCallBack != null) && (mService != null))
				try {
					((ISourceInterface) mService).registerCallBack(mCallBack);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}

	private void checkConnected() {
		if (mService != null)
			return;
		getServiceConnection();
	}

	public boolean requestSource(SourceID aSource) {
		Log.d("SourceManager", "======== Pid = " + Process.myPid() + " requestSource" + aSource);
		checkConnected();
		if (mService == null)
			return false;
		try {
			return ((ISourceInterface) mService).requestSource(aSource.name(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean releaseSource(SourceID aSource) {
		Log.d("SourceManager", "=========== Pid = " + Process.myPid() + " releaseSource" + aSource);
		checkConnected();
		if (mService == null){
			return false;
		}
		try {
			return ((ISourceInterface) mService).releaseSource(aSource.name(), false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean requestSource(SourceID aSource, boolean showUI) {
		Log.d("SourceManager", "========== Pid = " + Process.myPid() + " requestSource" + aSource);
		checkConnected();
		if (mService == null){
			return false;
		}
		try {
			return ((ISourceInterface) mService).requestSource(aSource.name(), !(showUI));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean releaseSource(SourceID aSource, boolean showUI) {
		Log.d("SourceManager", "============= Pid = " + Process.myPid() + " releaseSource" + aSource);
		checkConnected();
		if (mService == null){
			return false;
		}
		try {
			return ((ISourceInterface) mService).releaseSource(aSource.name(), !(showUI));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public SourceID getCurrentSource() {
		checkConnected();
		if (mService == null){
			return null;
		}
		try {
			return SourceID.valueOf(((ISourceInterface) mService).getCurrentSource());
			//SourceID id = SourceID.valueOf(((ISourceInterface) mService).getCurrentSource());
			//Log.e("SourceManager", "getCurrentSource :"+id);
			//return id;
		} catch (Exception e) {
			e.printStackTrace();
		}
		//Log.e("SourceManager", "getCurrentSource :null");

		return null;
	}

	public int getHostDeviceState() {
		checkConnected();
		if (mService == null){
			return 1;
		}
		try {
			return ((ISourceInterface) mService).getHostDeviceState();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 1;
	}

	public void sendProtocol(byte aGroupId, byte aSubId, byte[] aParam) {
		checkConnected();
		if (mService == null)
			return;
		try {
			((ISourceInterface) mService).sendProtocol(aGroupId, aSubId, aParam);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
/*
	private Vector<SourceID> getSourceStack() {
		checkConnected();
		if (mService == null)
			return null;
		try {
			String[] ids = ((ISourceInterface) mService).getSourceStack();
			if ((ids == null) || (ids.length == 0))
				return null;
			Vector aSourceIDs = new Vector();
			for (int i = 0; i < ids.length; ++i) {
				try {
					aSourceIDs.add(SourceID.valueOf(ids[i]));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			return aSourceIDs;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
*/
	@Deprecated
	public void setAudioInCall(boolean inCall) {
		checkConnected();
		if (mService == null){
			return;
		}
		try {
			((ISourceInterface) mService).setAudioInCall(inCall);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void addChangedListener(final SourceChangedListener aListener) {
		checkConnected();
		if ((aListener == null) || (this.mSourceChangedListener_map.containsKey(aListener)))
			return;

		SourceChanged aSourceChanged = new SourceChanged() {
			void onCurrnetInterruptChanged(boolean isInterrupted) {
				//aListener.onCurrnetInterruptChanged(isInterrupted);
			}
		};

		synchronized (SOURCECHANGED_LIST) {
			SOURCECHANGED_LIST.add(aSourceChanged);
			this.mSourceChangedListener_map.put(aListener, aSourceChanged);
			checkCallBack();
		}
	}

	private void checkCallBack() {
		if (SOURCECHANGED_LIST.isEmpty()) {
			try {
				if ((mService != null) && (mCallBack != null))
					((ISourceInterface) mService).unregisterCallBack(mCallBack);
				mCallBack = null;
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (mCallBack == null) {
			mCallBack = new CallBack();
			try {
				if (mService == null)
					return;
				((ISourceInterface) mService).registerCallBack(mCallBack);
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	public void removeChangedListener(SourceChangedListener aListener) {
		checkConnected();
		if (aListener == null) {
			return;
		}
		synchronized (SOURCECHANGED_LIST) {
			SourceChanged aSourceChanged = (SourceChanged) this.mSourceChangedListener_map.remove(aListener);
			SOURCECHANGED_LIST.remove(aSourceChanged);
			checkCallBack();
		}
	}

	@Deprecated
	public void addAudioChangedListener(final AudioReadyListener aListener) {
		checkConnected();
		if ((aListener == null) || (this.mAudioReadyListener_map.containsKey(aListener)))
			return;


		SourceChanged aSourceChanged = new SourceChanged() {
			void onAudioReadyChanged() {
				aListener.onAudioReadyChanged();
			}
		};

		synchronized (SOURCECHANGED_LIST) {
			SOURCECHANGED_LIST.add(aSourceChanged);
			this.mAudioReadyListener_map.put(aListener, aSourceChanged);
			checkCallBack();
		}
	}

	@Deprecated
	public void removeAudioChangedListener(AudioReadyListener aListener) {
		checkConnected();
		if (aListener == null) {
			return;
		}
		synchronized (SOURCECHANGED_LIST) {
			SourceChanged aSourceChanged = (SourceChanged) this.mAudioReadyListener_map.remove(aListener);
			SOURCECHANGED_LIST.remove(aSourceChanged);
			checkCallBack();
		}
	}

	public void addProtocolListener(final ProtocolListener aListener) {
		checkConnected();
		if ((aListener == null) || (this.mProtocolListener_map.containsKey(aListener)))
			return;


		SourceChanged aSourceChanged = new SourceChanged() {
			void onProtocolChanged(byte[] aProtocol) {
				aListener.onProtocolChanged(aProtocol);
			}
		};

		synchronized (SOURCECHANGED_LIST) {
			SOURCECHANGED_LIST.add(aSourceChanged);
			this.mProtocolListener_map.put(aListener, aSourceChanged);
			checkCallBack();
		}
	}

	public void removeProtocolListener(ProtocolListener aListener) {
		checkConnected();
		if (aListener == null) {
			return;
		}
		synchronized (SOURCECHANGED_LIST) {
			SourceChanged aSourceChanged = (SourceChanged) this.mProtocolListener_map.remove(aListener);
			SOURCECHANGED_LIST.remove(aSourceChanged);
			checkCallBack();
		}
	}

	public void addHostDeviceStateListener(final HostDeviceStateListener aListener) {
		checkConnected();
		if ((aListener == null) || (this.mHostDeviceStateListener_map.containsKey(aListener)))
			return;

		SourceChanged aSourceChanged = new SourceChanged() {
			void onHostDeviceStateChanged(int prevState, int currentState) {
				aListener.onStateChanged(prevState, currentState);
			}
		};

		synchronized (SOURCECHANGED_LIST) {
			SOURCECHANGED_LIST.add(aSourceChanged);
			this.mHostDeviceStateListener_map.put(aListener, aSourceChanged);
			checkCallBack();
		}
	}

	public void removeHostDeviceStateListener(HostDeviceStateListener aListener) {
		checkConnected();
		if (aListener == null) {
			return;
		}
		synchronized (SOURCECHANGED_LIST) {
			SourceChanged aSourceChanged = (SourceChanged) this.mHostDeviceStateListener_map.remove(aListener);
			SOURCECHANGED_LIST.remove(aSourceChanged);
			checkCallBack();
		}
	}

	public String getTuid(){
		return "";
	}
	
	@Deprecated
	public static abstract interface AudioReadyListener {
		public abstract void onAudioReadyChanged();
	}

	private static final class CallBack extends ISourceCallBackInterface.Stub {
		private Handler mHandler;

		CallBack() {
			mHandler = new Handler(Looper.getMainLooper());
		}

		public void onCurrnetInterruptNotify(final boolean isInterrupted) throws RemoteException {
			Log.e("coagent.source", " Pid = " + Process.myPid() + " onCurrnetInterruptNotify#isInterrupted = "
					+ isInterrupted);
			mHandler.post(new Runnable() {
				public void run() {
					synchronized (SourceManager.SOURCECHANGED_LIST) {
						for (SourceManager.SourceChanged mListener : SourceManager.SOURCECHANGED_LIST)
						{							
							//mListener.onCurrnetInterruptChanged(isInterrupted);
						}
					}
				}
			});
		}

		@Deprecated
		public void onAudioReadyNotify() throws RemoteException {
			mHandler.post(new Runnable() {
				public void run() {
					synchronized (SourceManager.SOURCECHANGED_LIST) {
						for (SourceManager.SourceChanged mListener : SourceManager.SOURCECHANGED_LIST)
							mListener.onAudioReadyChanged();
					}
				}
			});
		}

		public void onProtocolNotify(final byte[] aProtocol) throws RemoteException {
			mHandler.post(new Runnable() {
				public void run() {
					synchronized (SourceManager.SOURCECHANGED_LIST) {
						for (SourceManager.SourceChanged mListener : SourceManager.SOURCECHANGED_LIST)
							mListener.onProtocolChanged(aProtocol);
					}
				}
			});
		}

		public void onHostDeviceStateNotify(final int prevState,final int currentState) throws RemoteException {
			mHandler.post(new Runnable() {
				public void run() {
					synchronized (SourceManager.SOURCECHANGED_LIST) {
						for (SourceManager.SourceChanged mListener : SourceManager.SOURCECHANGED_LIST)
							mListener.onHostDeviceStateChanged(prevState, currentState);
					}
				}
			});
		}
	}

	public static abstract interface HostDeviceStateListener {
		public abstract void onStateChanged(int paramInt1, int paramInt2);
	}

	public static abstract interface ProtocolListener {
		public abstract void onProtocolChanged(byte[] data);
	}

	private static class SourceChanged {

		void onCurrnetInterruptChanged(boolean isInterrupted) {
		}

		void onAudioReadyChanged() {
		}

		void onProtocolChanged(byte[] aProtocol) {
		}

		void onHostDeviceStateChanged(int prevState, int currentState) {
		}
	}

	public static abstract interface SourceChangedListener {
		public abstract void onCurrnetInterruptChanged(boolean interrupt);
	}
}
