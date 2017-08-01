
package com.incall.proxy.can;

import android.os.Parcel;
import android.os.Parcelable;

/** 胎压信息 */
public class TpmsInfo implements Parcelable {

    /** 信号状态 */
    public static enum Signal {
        CURRENT((byte) 0),
        LAST((byte) 1);

        public final byte index;

        private Signal(byte idx) {
            this.index = idx;
        }

        public static Signal getByIndex(byte index)
        {
            Signal[] values = values();
            for (Signal item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 胎压警告状态 */
    public static enum TyreWarningState {
        /** 正常 */
        NO_WARN((byte) 0),
        /** 胎压过高 */
        HIGH_PRESSURE_WARN((byte) 1),
        /** 胎压过低 */
        LOW_PRESSURE_WARN((byte) 2),
        /** 快速漏气 */
        QUICK_LEAK_WARN((byte) 3),
        /** 传感器丢失 */
        LOST_SENSOR_WARN((byte) 4),
        /** 传感器电压过低 */
        BATTERY_LOW_WARN((byte) 5),
        /** 传感器失效 */
        SENSOR_FAILURE((byte) 6);

        public final byte index;

        private TyreWarningState(byte idx) {
            this.index = idx;
        }

        public static TyreWarningState getByIndex(byte index)
        {
            TyreWarningState[] values = values();
            for (TyreWarningState item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return null;
        }
    }

    public static final Parcelable.Creator<TpmsInfo> CREATOR = new Parcelable.Creator<TpmsInfo>() {
        @Override
        public TpmsInfo createFromParcel(Parcel source) {
            return new TpmsInfo(
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    (Signal) source.readSerializable(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readFloat(),
                    (TyreWarningState) source.readSerializable(),
                    (TyreWarningState) source.readSerializable(),
                    (TyreWarningState) source.readSerializable(),
                    (TyreWarningState) source.readSerializable());
        }

        @Override
        public TpmsInfo[] newArray(int size) {
            return new TpmsInfo[size];
        }
    };

    /** TPMS system Failure flag(0: OK, 1: error) */
    public final boolean systemFlag;
    /** 前左传感器温度警告 */
    public final boolean isFLT_warning;
    /** 前右传感器温度警告 */
    public final boolean isFRT_warning;
    /** 后左传感器温度警告 */
    public final boolean isRLT_warning;
    /** 后右传感器温度警告 */
    public final boolean isRRT_warning;
    /** 信号状态 */
    public final Signal mStatus;
    /** 前左胎压值(Kpa) */
    public final float pressureFL;
    /** 前右胎压值(Kpa) */
    public final float pressureFR;
    /** 后左胎压值(Kpa) */
    public final float pressureRL;
    /** 后右胎压值(Kpa) */
    public final float pressureRR;
    /** 前左胎温度值(℃) */
    public final float temperatureFL;
    /** 前右胎温度值(℃) */
    public final float temperatureFR;
    /** 后左胎温度值(℃) */
    public final float temperatureRL;
    /** 后右胎温度值(℃) */
    public final float temperatureRR;
    /** 前左胎压警告 */
    public final TyreWarningState warningFL;
    /** 前右胎压警告 */
    public final TyreWarningState warningFR;
    /** 后左胎压警告 */
    public final TyreWarningState warningRL;
    /** 后右胎压警告 */
    public final TyreWarningState warningRR;

    public TpmsInfo(boolean systemFlag, boolean isFLT_warning, boolean isFRT_warning,
            boolean isRLT_warning, boolean isRRT_warning, Signal mStatus, float pressureFL,
            float pressureFR, float pressureRL, float pressureRR, float temperatureFL,
            float temperatureFR, float temperatureRL, float temperatureRR,
            TyreWarningState warningFL, TyreWarningState warningFR, TyreWarningState warningRL,
            TyreWarningState warningRR)
    {
        this.systemFlag = systemFlag;
        this.isFLT_warning = isFLT_warning;
        this.isFRT_warning = isFRT_warning;
        this.isRLT_warning = isRLT_warning;
        this.isRRT_warning = isRRT_warning;
        this.mStatus = mStatus;
        this.pressureFL = pressureFL;
        this.pressureFR = pressureFR;
        this.pressureRL = pressureRL;
        this.pressureRR = pressureRR;
        this.temperatureFL = temperatureFL;
        this.temperatureFR = temperatureFR;
        this.temperatureRL = temperatureRL;
        this.temperatureRR = temperatureRR;
        this.warningFL = warningFL;
        this.warningFR = warningFR;
        this.warningRL = warningRL;
        this.warningRR = warningRR;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.systemFlag ? 1 : 0));
        dest.writeByte((byte) (this.isFLT_warning ? 1 : 0));
        dest.writeByte((byte) (this.isFRT_warning ? 1 : 0));
        dest.writeByte((byte) (this.isRLT_warning ? 1 : 0));
        dest.writeByte((byte) (this.isRRT_warning ? 1 : 0));
        dest.writeSerializable(this.mStatus);
        dest.writeFloat(this.pressureFL);
        dest.writeFloat(this.pressureFR);
        dest.writeFloat(this.pressureRL);
        dest.writeFloat(this.pressureRR);
        dest.writeFloat(this.temperatureFL);
        dest.writeFloat(this.temperatureFR);
        dest.writeFloat(this.temperatureRL);
        dest.writeFloat(this.temperatureRR);
        dest.writeSerializable(this.warningFL);
        dest.writeSerializable(this.warningFR);
        dest.writeSerializable(this.warningRL);
        dest.writeSerializable(this.warningRR);
    }
}
