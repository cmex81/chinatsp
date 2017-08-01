package com.chinatsp.misc;

import android.content.Intent;
import android.os.IBinder;
import android.app.Service;
import android.util.Log;
import android.os.Process;
import com.incall.proxy.ServiceNameManager;
import com.chinatsp.storage.StorageService;

public class MiscService extends Service{
	private static String TAG = "TMiscService";
	

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		Log.d(TAG, "onBind: " + Process.myPid());
		return null;
	}
	
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
		Log.d(TAG, "onStartCommand: " + Process.myPid());
    	startAllServices();
        return START_NOT_STICKY;
    }
	
    private void startAllServices(){
    	StorageService.addServiceToServiceManager(ServiceNameManager.SYSTEM_STORAGE_SERVICENAME);
    	
    	for(String action:ServiceNameManager.getAllStartServiceActions()){
    		Log.i(TAG, "start service: "+ action);
    		startService(new Intent(action));
    	}
    }
}
