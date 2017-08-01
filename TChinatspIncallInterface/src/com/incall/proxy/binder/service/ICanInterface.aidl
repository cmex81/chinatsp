package com.incall.proxy.binder.service;
import com.incall.proxy.can.AirConditionInfo;
import com.incall.proxy.can.DoorInfo;
import com.incall.proxy.can.EcuInfo;
import com.incall.proxy.can.MaintainInfo;
import com.incall.proxy.can.RadarInfo;
import com.incall.proxy.can.TpmsInfo;
import com.incall.proxy.binder.callback.ICanCallBackInterface;

interface ICanInterface{
	void registerCallBack(in ICanCallBackInterface aCallback);
	void unregisterCallBack(in ICanCallBackInterface aCallback);
	AirConditionInfo getAirConditionInfo();
	String getCarSettingOption(String aCarSettingOptionName);
	DoorInfo getDoorInfo();
	byte[] getEcoInfo();
	EcuInfo getEcuInfo();
	byte[] getLockInfo();
	MaintainInfo getMaintainInfo();
	String getOfLineOption(String aOfLineOptionName);
	boolean getOfLineSwitch(String aOfLineSwitchName);
	RadarInfo getRadarInfo();
	byte[] getSpeedInfo();
	TpmsInfo getTpmsInfo();
	float getWheelAngle();
	void setCarSettingOption(String aCarSettingOptionName, String aCarSettingOptionValue);
	void setOfLineOption(String aOfLineOptionName, String aOfLineOptionValue);
	void setOfLineSwitch(String aOfLineSwitchName, boolean enable);
	void setRemainderMaintainInfo(int day, int KM, int hint);
	/** 轨迹划线切换 */
	void trajectorySwitch();
}