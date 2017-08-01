package com.chinatsp.storage;

import android.os.RemoteException;
import java.lang.reflect.Method;

import android.os.IBinder;
import com.incall.proxy.binder.callback.IStorageCallBackInterface;
import com.incall.proxy.binder.service.IStorageInterface;
public class StorageService extends IStorageInterface.Stub {

    private static StorageService mStorageService = null;
    
    private StorageService() {

    }
    
    public static StorageService getStorageService() {
        if (mStorageService == null) {
        	mStorageService = new StorageService();
        }
        return mStorageService;
    }

    public static void addServiceToServiceManager(String serviceName) {
        Object object = new Object();
        Method addService;
        StorageService serviceImpl;
        try {
            serviceImpl = getStorageService();
            addService = Class.forName("android.os.ServiceManager").getMethod("addService", String.class, IBinder.class);
            addService.invoke(object, serviceName, serviceImpl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
	@Override
	public void registerCallBack(IStorageCallBackInterface callback)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unregisterCallBack(IStorageCallBackInterface callback)
			throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isUsbExist() throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isUsbExtExist() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isSdcardExist() throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

}
