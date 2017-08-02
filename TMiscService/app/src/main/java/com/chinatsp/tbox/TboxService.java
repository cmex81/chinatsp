package com.chinatsp.tbox;

import android.os.RemoteException;

import com.incall.proxy.binder.callback.ITboxCallBackInterface;
import com.incall.proxy.binder.service.ITboxInterface;

/**
 * Created by ryan on 31/07/2017.
 */
public class TboxService extends ITboxInterface.Stub{

    public TboxService() {

    }
//    public static TboxService getInstance(){
//        return TboxServiceSingletonHolder.sInstance;
//    }
//    private static class TboxServiceSingletonHolder{
//        private static final TboxService sInstance = new TboxService();
//    }

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
}
