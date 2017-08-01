
package com.incall.proxy.setting;

import android.os.Parcel;
import android.os.Parcelable;

/** 设置相关信息 */
public class SettingsInfo implements Parcelable {

    public static final int BRIGHTNESS_MAX = 100;

    /** 当前系统亮度 */
    public int currentBrigness;
    /** 主机开机时的时间（RTC） */
    public int currentRTC;
    /** 亮度调节模式 （自动/手动） */
    public boolean isBrignessAuto;
    /** HOST USB 过流 */
    public boolean isHostUsbOverCurrent;
    /** 静音（打开/关闭） */
    public boolean isMute;
    /** OTG USB 过流 */
    public boolean isOtgUsbOverCurrent;
    /** 音量条是否要显示（方控和中控按键调节需要显示，主机界面调节不需要显示） */
    public boolean isShow;
    /** 温度单位 0：华氏温度 1：摄氏温度 */
    public boolean isTemperatureModel;
    /** 按键音模式 0: Beep closed. 1: Beep Lowering. 2: Beep Melodious */
    public int mBeepModel;
    /** 当前系统蓝牙音量 */
    public int mBeepVolume;// 当前系统蓝牙音量
    /** 当前音量值 */
    public int mCurrentVolume;
    /** 当前音量类型 */
    public int mCurrentVolumeIndex;
    /** 当前MCU版本号 */
    public String mMcuVersion;
    /** 当前系统多媒体音量 */
    public int mMediaVolume;
    /** 当前系统导航音量 */
    public int mNaviVolume;
    /** 当前系统蓝牙音量 */
    public int mPhoneVolume;
    /** 当前系统蓝牙音量 */
    public int mRadarVolume;

    public static final Parcelable.Creator<SettingsInfo> CREATOR = new Parcelable.Creator<SettingsInfo>() {

        @Override
        public SettingsInfo createFromParcel(Parcel source) {
            return new SettingsInfo(source);
        }

        @Override
        public SettingsInfo[] newArray(int size) {
            return new SettingsInfo[size];
        }

    };

    public SettingsInfo() {
    }

    public SettingsInfo(int currentBrigness, int currentVolumeIndex,
            int currentVolume, int MediaVolume, int PhoneVolume,
            int NaviVolume, int mBeepVolume, int mRadarVolume,
            java.lang.String mcuVersion, boolean BrignessAuto, int rtc,
            boolean mute, int beepModel, boolean isShow,
            boolean TemperatureModel, boolean hostUsbOverCurrent,
            boolean otgUsbOverCurrent) {
        this.currentBrigness = currentBrigness;
        this.mCurrentVolumeIndex = currentVolumeIndex;
        this.mCurrentVolume = currentVolume;
        this.mMediaVolume = MediaVolume;
        this.mPhoneVolume = PhoneVolume;
        this.mNaviVolume = NaviVolume;
        this.mBeepVolume = mBeepVolume;
        this.mRadarVolume = mRadarVolume;
        this.mMcuVersion = mcuVersion;
        this.isBrignessAuto = BrignessAuto;
        this.currentRTC = rtc;
        this.isMute = mute;
        this.mBeepModel = beepModel;
        this.isShow = isShow;
        this.isTemperatureModel = TemperatureModel;
        this.isHostUsbOverCurrent = hostUsbOverCurrent;
        this.isOtgUsbOverCurrent = otgUsbOverCurrent;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(currentBrigness);// 当前系统亮度
        dest.writeInt(currentRTC);// 主机开机时的时间（RTC）
        dest.writeInt(mBeepModel);// 按键音模式 0: Beep closed. 1: Beep Lowering. 2:
                                  // Beep Melodious
        dest.writeInt(mBeepVolume);// 当前系统蓝牙音量
        dest.writeInt(mCurrentVolume);
        dest.writeInt(mCurrentVolumeIndex);
        dest.writeInt(mMediaVolume);// 当前系统多媒体音量
        dest.writeInt(mNaviVolume);// 当前系统导航音量
        dest.writeInt(mPhoneVolume);// 当前系统蓝牙音量
        dest.writeInt(mRadarVolume);// 当前系统蓝牙音量

        dest.writeByte((byte) (isBrignessAuto ? 1 : 0));// 亮度调节模式 （自动/手动）
        dest.writeByte((byte) (isHostUsbOverCurrent ? 1 : 0));
        dest.writeByte((byte) (isMute ? 1 : 0));
        dest.writeByte((byte) (isOtgUsbOverCurrent ? 1 : 0));
        dest.writeByte((byte) (isShow ? 1 : 0));
        dest.writeByte((byte) (isTemperatureModel ? 1 : 0));

        dest.writeString(mMcuVersion);// 当前MCU版本号
    }

    private SettingsInfo(Parcel source) {
        readFromParcel(source);
    }

    public void readFromParcel(Parcel source) {
        currentBrigness = source.readInt();// 当前系统亮度
        currentRTC = source.readInt();// 主机开机时的时间（RTC）
        mBeepModel = source.readInt();// 按键音模式 0: Beep closed. 1: Beep Lowering.
                                      // 2: Beep Melodious
        mBeepVolume = source.readInt();// 当前系统蓝牙音量
        mCurrentVolume = source.readInt();
        mCurrentVolumeIndex = source.readInt();
        mMediaVolume = source.readInt();// 当前系统多媒体音量
        mNaviVolume = source.readInt();// 当前系统导航音量
        mPhoneVolume = source.readInt();// 当前系统蓝牙音量
        mRadarVolume = source.readInt();// 当前系统蓝牙音量

        isBrignessAuto = (source.readByte() != 0);// 亮度调节模式 （自动/手动）
        isHostUsbOverCurrent = (source.readByte() != 0);// HOST USB 过流
        isMute = (source.readByte() != 0);// 静音（打开/关闭）
        isOtgUsbOverCurrent = (source.readByte() != 0);// OTG USB 过流
        isShow = (source.readByte() != 0);
        isTemperatureModel = (source.readByte() != 0);// 温度单位 0：华氏温度 1：摄氏温度

        mMcuVersion = source.readString();// 当前MCU版本号
    }

    /** 打印信息 */
    public void mtoString() {

    }

}
