package com.incall.proxy.binder.service;

import com.incall.proxy.binder.callback.IUpdateCallbackInterface;

interface IUpdateInterface {
    void updateOS(String path);
    void updateMCU(String path);
    void updateGPS(String path);
    void updateTW8836(String path);
    void updateALL(in String[] path);
    void reboot();
    void addCallBack(in IUpdateCallbackInterface mIUpdateCallbackInterface);
    void updateLcd_927(String path); //add 2017.05.19
}