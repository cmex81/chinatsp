package com.incall.proxy.binder.service;

import com.incall.proxy.setting.AudioSettingsInfo;
import com.incall.proxy.setting.SettingsInfo;
import com.incall.proxy.setting.TestInfo;

import com.incall.proxy.binder.callback.ISettingCallBackInterface;


interface ISettingInterface{

boolean get3DSound() ;
            
 AudioSettingsInfo getAudioSettingsInfo() ;
            
 int getBeepVolume() ;
 
 int getTTSVolume();
            
 int getCurrentRTC() ;
            
 SettingsInfo getSettingsInfo() ;
            
 TestInfo getTestInfo() ;
            
 void inFactoryMode() ;
            
 void outFactoryMode() ;
            
 void registerCallBack(in ISettingCallBackInterface aCallback) ;
            
 void set3DSound(boolean on) ;
            
 void setApuTestMode() ;
            
 void setAudioSetting(int index, int value) ;
            
 void setAutoAging(boolean on) ;
            
 void setBeepModel(int model) ;
            
 void setBeepVolume(int value) ;
            
 void setBrigness(int isSeekUp) ;
            
 void setBrignessAuto(boolean auto) ;
            
 void setCurrentRTC(int sec) ;
            
 void setDate(int Year, int Month, int day) ;
            
 void setFanControl(int model) ;
            
 void SetGEQ_Index(int index, int value) ;
            
 void SetGEQ(int index) ;
            
 void setKeyboardTest(boolean on) ;
            
 void setMcuFactoryMode(int mode) ;
            
 void setMediaVolume(int value) ;
            
 void setMonitor(boolean on) ;
            
 void setMute(boolean mute) ;
            
 void setNaviVolume(int value) ;
            
 void setPhoneVolume(int value) ;
            
 void setQualityTest(boolean on) ;
            
 void setRadarVolume(int value) ;
            
 void setSpeedAdjustVolume(int value) ;
            
 void setTTSVolume(int value) ;
            
 void setVolumeDown() ;
            
 void setVolumeUp() ;
 
 void setSystemFactory(in String[]excludePath);
            
 void unregisterCallBack(in ISettingCallBackInterface aCallback) ;
 
            
}
            