package com.incall.proxy.binder.callback;

interface IStorageCallBackInterface {
    void onUsbStatusNotify(boolean usbStatus);
    void onUsbExtStatusNotify(boolean usbStatus);
    void onSdcardStatusNotify(boolean usbStatus);
}