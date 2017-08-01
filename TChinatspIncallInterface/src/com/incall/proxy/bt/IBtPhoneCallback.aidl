package com.incall.proxy.bt;
import com.incall.proxy.bt.Sms;
import com.incall.proxy.bt.BtDevice;
import com.incall.proxy.bt.Contact;
import com.incall.proxy.bt.A2dpMetaData;

interface IBtPhoneCallback{
void onPowerChanged(int powerState);
void onPairingRequest(String deviceName,String deviceAddress,String pinCode,boolean bSSP);
void onPairingResult(String deviceAddress, int status);
void onConnectRequest(String deviceName,String deviceAddress, int profile, int index);
void onHfpConnectionChanged(int status);
void onA2dpConnectionChanged(int status);
void onDeviceFound(in BtDevice btDevice);
void onDeviceUpdate();
void onCallStateChanged(in List<Contact> oldCall,in List<Contact> newCall);
void onCallLogStateChanged(int syncState);
void onUpdateContact();
void onUpdateContactState(int syncState);
void onUpdateCallLog();
void onUpdateCallLogState(int syncState);
void onUpdateSms();
void onReceivedNewSms(in Sms sms);
void onMetaDataChanged(in A2dpMetaData metaData);
void onContactStateChanged(int syncState);
void onContactDownloadProgressChanged(int total,  int downloaded);
void onContactDBStateChanged(boolean ready);
void onBatteryIndicatorChanged(int currentValue,  int maxValue, int minValue);
void onSignalStrengthChanged(int currentStrength, int maxStrength,int minStrength);
void onScoChanged(boolean connected);
void onMusicStop();
void onMusicPlay();
void onMusicPause();
void onMusicProgress(long pos,long time);
void onStreamChanged(boolean state);
void onRepeatModeChanged(int mode);
void onShuffleModeChanged(int mode);
}