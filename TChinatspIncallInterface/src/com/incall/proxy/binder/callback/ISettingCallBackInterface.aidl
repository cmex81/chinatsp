package com.incall.proxy.binder.callback;


import com.incall.proxy.setting.AudioSettingsInfo;
import com.incall.proxy.setting.SettingsInfo;
import com.incall.proxy.setting.TestInfo;


interface ISettingCallBackInterface{

 void onAudioSettingsInfoChange(in AudioSettingsInfo info) ;
            
 void onBignessNotify(int aSignalLevel) ;
            
 void onCurrentVolumeNotify(int index, int volume, boolean needShow) ;
            
 void onSettingsInfoChange(in SettingsInfo info) ;
            
 void onUsbOverCurrent(int usbIdx, boolean isOverCurrent) ;
 }
 
