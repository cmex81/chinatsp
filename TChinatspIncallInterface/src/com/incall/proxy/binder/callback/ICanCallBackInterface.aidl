package com.incall.proxy.binder.callback;
import com.incall.proxy.can.AirConditionInfo;
import com.incall.proxy.can.CarComputerInfo;
import com.incall.proxy.can.CarPM25Info;
import com.incall.proxy.can.DoorInfo;
import com.incall.proxy.can.EcuInfo;
import com.incall.proxy.can.MaintainInfo;
import com.incall.proxy.can.RadarInfo;
import com.incall.proxy.can.TpmsInfo;

interface ICanCallBackInterface{
	void onAirConditionInfoNotify(in AirConditionInfo aAirConditionInfo);
    void onCarComputerInfoNotify(in CarComputerInfo info);
    void onCarPM25InfoNotify(in CarPM25Info carPM25Info);
    void onCarSettingsChangeNotify(String aCarSettingsOptionName, String aCarSettingsOptionValue);
    void onDoorInfoNotify(in DoorInfo aDoorInfo);
    void onEcoInfoNotify(in byte[] param);
    void onEcuInfoNotify(in EcuInfo aEcuInfo);
    void onLockInfoNotify(in byte[] param);
    void onMaintainInfoNotify(in MaintainInfo aMaintainInfo);
    void onOfLineOptionNotify(String aOfLineOptionName, String aOfLineOptionValue);
    void onOfLineSwitchNotify(String aOfLineSwitchName, boolean enable);
    void onRadarInfoNotify(in RadarInfo aRadarInfo);
    void onSpeedInfoNotify(in byte[] param);
    void onTpmsInfoNotify(in TpmsInfo aTpmsInfo);
    void onWheelAngleInfoNotify(float angle);
 }
 
