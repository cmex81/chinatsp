package com.incall.proxy;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Process;
import android.os.ServiceManager;
import android.util.Log;

public abstract class ServiceConnection<T extends android.os.IInterface>{
	
	private final String TAG = "ServiceConnection";
	
	protected abstract boolean getServiceConnection();
	protected abstract void serviceReConnected();
	
	protected final String mServiceName; // 服务名称
	protected T mService;
	protected boolean mIsConnected = false;
	
	private Handler mHandler;
	private void log(String msg){
		Log.e(TAG,msg);
	}
	
	protected  ServiceConnection(String servicename){
		mServiceName = servicename;
		getServiceConnection();
		 HandlerThread mHandlerThread = new HandlerThread("Handler thread"){
			public void run(){
				Process.setThreadPriority(10);
				super.run();
			}
		};
		mHandlerThread.start();
		mHandler =new Handler(mHandlerThread.getLooper());
		
	}
	
	
	protected void serviceBinderDied(){
		mIsConnected = false;
		mService = null;
		log(" binder died-----servicename:"+mServiceName);
		mHandler.postDelayed(handerRunnable, 1000);
	}
	
	
	/**
	 * 绑定系统服务
	 */
	protected final IBinder connectService(){
		
			//系统服务
			IBinder servicebinder = ServiceManager.getService(mServiceName);
			if(servicebinder != null){
				mIsConnected = true;
				 IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
					@Override
					public void binderDied() {
						serviceBinderDied();
					}
				};//mDeathRecipient
				try {
					servicebinder.linkToDeath(mDeathRecipient, 0);
				} catch (Exception e) {
					// TODO: handle exception
				}
				return servicebinder;
			}//null
		return null;
	}
	
	protected boolean isServiceConnected(){
		return mIsConnected;
	}
	
	
	private final Runnable handerRunnable = new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			log(" service re connect----servicename:"+mServiceName);
			serviceReConnected();
			if(!isServiceConnected()){
				mHandler.postDelayed(this, 1000);
			}
		}
	};

	protected void serviceDied() {
        this.mService = null;
    }
	
	
}
