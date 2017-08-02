package com.chinatsp.misc;

import android.content.Context;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import com.chinatsp.storage.StorageService;
import com.chinatsp.tbox.TboxService;
import com.incall.log.MjLog;
import com.incall.proxy.binder.callback.IStorageCallBackInterface;
import com.incall.proxy.binder.callback.ITboxCallBackInterface;
import com.incall.proxy.binder.service.IStorageInterface;
import com.incall.proxy.binder.service.ITboxInterface;

/**
 * Created by ryan on 31/07/2017.
 */
public class MiscServiceAdapter extends Binder implements IStorageInterface, ITboxInterface {


    private final String TAG = "MiscServiceAdapter";
    private StorageService storageService;
    private TboxService tboxService;
    private Context mContext;
    public MiscServiceAdapter(Context context) {
        MjLog.d(TAG,"MiscServiceAdapter=====");
        this.mContext = context;
        storageService = new StorageService(mContext);
        tboxService = new TboxService();
    }

    //===============ITboxInterface=====================
    @Override
    public void registerCallBack(ITboxCallBackInterface iTboxCallBackInterface) throws RemoteException {

    }

    @Override
    public void unregisterCallBack(ITboxCallBackInterface iTboxCallBackInterface) throws RemoteException {

    }

    @Override
    public boolean tboxIsExist() throws RemoteException {
        return false;
    }

    @Override
    public void getWanInfo() throws RemoteException {

    }

    @Override
    public void getMobileState() throws RemoteException {

    }

    @Override
    public void setMobileState(boolean b) throws RemoteException {

    }

    @Override
    public void getWiFiState() throws RemoteException {

    }

    @Override
    public void setWiFiState(boolean b) throws RemoteException {

    }

    @Override
    public void getWiFiSSID() throws RemoteException {

    }

    @Override
    public void setWiFiSSID(String s) throws RemoteException {

    }

    @Override
    public void getWiFiPwd() throws RemoteException {

    }

    @Override
    public void setWiFiPwd(boolean b, String s) throws RemoteException {

    }

    @Override
    public void getWiFiConnectNumber() throws RemoteException {

    }

    @Override
    public String getServicePhone(int i) throws RemoteException {
        return null;
    }

    @Override
    public String getNotifyMsg() throws RemoteException {
        return null;
    }

    @Override
    public void sendOneCallReqest(byte b) throws RemoteException {

    }

    @Override
    public String checkRegistered() throws RemoteException {
        return null;
    }

    @Override
    public String getAuthenticationPath(String s) throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return null;
    }


    //===============IStorageInterface=====================

    @Override
    public void registerCallBack(IStorageCallBackInterface iStorageCallBackInterface) throws RemoteException {
        MjLog.d(TAG,"registerCallBack=====");
        storageService.registerCallBack(iStorageCallBackInterface);
    }

    @Override
    public void unregisterCallBack(IStorageCallBackInterface iStorageCallBackInterface) throws RemoteException {
        storageService.unregisterCallBack(iStorageCallBackInterface);
    }

    @Override
    public boolean isUsbExist() throws RemoteException {
        return storageService.isUsbExist();
    }

    @Override
    public boolean isUsbExtExist() throws RemoteException {
        return storageService.isUsbExist();
    }

    @Override
    public boolean isSdcardExist() throws RemoteException {
        return storageService.isSdcardExist();
    }
}
