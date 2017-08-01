package com.incall.proxy.binder.service;
import com.incall.proxy.binder.callback.IStorageCallBackInterface;

interface IStorageInterface {    
    void registerCallBack(in IStorageCallBackInterface callback);
    void unregisterCallBack(in IStorageCallBackInterface callback);
    boolean isUsbExist();
    boolean isUsbExtExist();
    boolean isSdcardExist();
}