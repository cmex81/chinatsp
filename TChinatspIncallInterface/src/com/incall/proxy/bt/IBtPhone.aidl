package com.incall.proxy.bt;

import com.incall.proxy.bt.A2dpMetaData;
import com.incall.proxy.bt.Contact;
import com.incall.proxy.bt.BtDevice;
import com.incall.proxy.bt.CallLog;
import com.incall.proxy.bt.Sms;
import com.incall.proxy.bt.IBtPhoneCallback;

interface IBtPhone {
	void setBtPower(boolean enable);

	boolean getBtPowerSetting();

	boolean musicPlay();

	boolean musicPause();

	boolean musicStop();

	boolean musicPrev();

	boolean musicNext();

	boolean musicSeekTo(int seconds);

	A2dpMetaData getMetaData();

	boolean isHfpConnected();

	boolean isA2dpConnected();

	boolean dialByTel(java.lang.String tel);

	boolean sendDtmf(java.lang.String dtmf);

	boolean pickUpCall();

	boolean pickUpWaitingCallAndHangUpCurrentCall();

	boolean switchHeldCall();

	boolean hangUpCurrentCall();

	boolean hangUpIncomingCall();

	boolean hangUpWaitingCall();

	boolean setAudioInHost(boolean host);

	boolean isAudioInHost();

	List<Contact> getCurrentCallContacts();

	String getFriendlyName();

	boolean setFriendlyName(java.lang.String name);

	BtDevice getConnectedDevice();

	List<BtDevice> getPairedDeviceList();

	void deletePairedDevice(java.lang.String address);

	String getLastConnectAddress();

	int getDeviceMaxNum();

	void setDeviceMaxNum(int maxNum);

	boolean setDiscoverable(boolean enable);

	boolean isDiscoverable();

	boolean acceptPair(String address, String pin_code, boolean bSSP);

	boolean rejectPair(String address, boolean bSSP);

	boolean acceptConnectRequest(int nProfileType, int nIndex);

	boolean rejectConnectRequest(int nProfileType, int nIndex);

	boolean reqPairedDeviceConnection(String address, boolean connection);

	String getConnectingConnectedDisconnectingDeviceAddress();

	int getHfpConnectStatus();

	int getA2dpConnectStatus();

	void setSyncContacts(boolean enable);

	boolean getSyncContacts();

	void setSyncSms(boolean enable);

	boolean getSyncSms();

	void downLoadAllPhoneBook();

	void downLoadUserPhoneBook();

	void downLoadCallLog(int count);

	void downLoadSms(int count);

	boolean isPhonebookOrSmsDownloading();
	boolean isPhonebookDownloading();

	boolean isContactsSyncComplete();

	boolean isContactsSyncSuccess();

	boolean isCallLogsSyncComplete();

	boolean isCallLogsSyncSuccess();

	boolean isSmsesSyncComplete();

	boolean isSmsesSyncSuccess();
	int getCallCount();
	int getContactsTotal();
    List<Contact> getContacts();
    List<Contact> getContacts_ext(int start, int end);
    
	List<CallLog> getCallLogs();

	List<Sms> getSmses();

	boolean setSmsRead( in Sms sms);

	boolean sendSMS(String tel, String sms);

	String HanziToPinyin(String hanzi);

	void registerCallback(IBtPhoneCallback cb);

	void unregisterCallback(IBtPhoneCallback cb);

	String getSDKVersion();

	void setAutoConnection(boolean auto);

	boolean isAutoConnection();

	boolean reDial();

	void setEnableSdkLog(boolean enable);

	int getSignalQuality();

	int getBatteryCharge();

	String getOperatorInfo();

	boolean startInquiryDevice();

	boolean stopInquiryDevice();

	boolean startPair(String address, String pin_code, int cod);

	int[] getPlayerRepeatModeRange();

	int[] getPlayerShuffleModeRange();

	boolean setPlayerRepeatMode(int mode);

	boolean setPlayerShuffleMode(int mode);

	boolean musicSetMute(boolean mute);

	int getSyncContactsTotal();

	int getSyncContactsDownload();

	int getBtPowerStatus();

	boolean isContactDBReady();

	boolean setLastConnectAddress(String address);

	boolean setA2dpStreamVol(float volume);

	void setPoundKeyFirst(boolean isPoundKeyFirst);

	void stopAutoconnect();

	void setBtOorMaxtime(int time);

	void deleteSyncData();

	boolean getA2dpAudioPlayStatus();

	boolean isIncall();

	boolean reqHfpVoiceDial(boolean enable);

	int getReasonPbapDownload();

	void setStandbyChangeBtStatus(boolean enable);

	void startCallRingtone();

	void stopCallRingtone();

	boolean getBtDiscoverableSetting();

	void setCustomId(int id);

	boolean isIPhoneMobile(String address);

	int getCallLogsSyncState();

	int getContactsSyncState();
	
	int getPlayerRepeatMode();
	
	int getPlayerShuffleMode();
	
	String	getPlayingAlbum();
	
	String getPlayingArtist();
	
	String getPlayingGenre();
	
	long getPlayingPosition();
	
	String getPlayingTitle();
	
	int getPlayingTotal();
	
	long getPlayingTime();
	
	int getPlayingTrack();
	
	int getPlayStatus();
	
	String getRemoteDeviceName();
	
	boolean isA2dpAudioStreaming();
	
	boolean isSupportRepeat();

	boolean isSupportShuffle();
	boolean isBtPowerOning();
	boolean isBtPowerOffing();
	boolean isBtPowerOn();
	boolean isBtPowerOff();
	
	boolean isPairedDevice(String address);
	List<Contact> getOnGoingContacts();
	List<Contact> getInComingContacts();
	List<Contact>	getWaitingContacts();
	List<Contact>	getActivedContacts();

}