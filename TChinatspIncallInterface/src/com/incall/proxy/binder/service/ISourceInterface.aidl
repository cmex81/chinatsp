package com.incall.proxy.binder.service;

import com.incall.proxy.binder.callback.ISourceCallBackInterface;

interface ISourceInterface {

    boolean requestSource(in String aSource, in boolean callFromFramework);
    
    boolean releaseSource(in String aSource, in boolean callFromFramework);
    
    String getCurrentSource();
    
    int getHostDeviceState();

    void registerCallBack(in ISourceCallBackInterface aCallback);

    void unregisterCallBack(in ISourceCallBackInterface aCallback);

    void sendProtocol(byte aGroupId, byte aSubId, in byte[] aParam);

    void setAudioInCall(boolean inCall);
}