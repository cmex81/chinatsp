
package com.incall.proxy.can;

import android.os.Parcel;
import android.os.Parcelable;

/** 雷达信息 */
public class RadarInfo implements Parcelable {

    /** 雷达障碍物距离 */
    public static enum RadarDistance
    {
        /** 无障碍物 */
        NO_OBSTACLE((byte) 0),
        /** 距离障碍物小于 40cm */
        RISK((byte) 1),
        /** 距离障碍物在40cm-100cm之间 */
        WARN((byte) 2),
        /** 距离障碍物在100cm-150cm之间 */
        SAFE((byte) 3);

        public final byte index;

        private RadarDistance(byte idx) {
            this.index = idx;
        }

        public static RadarDistance getByIndex(byte index) {
            RadarDistance[] values = values();
            for (RadarDistance item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 倒车雷达系统状态 */
    public static enum RadarStatus
    {
        /** 没有活动 */
        Not_Active((byte) 0),
        /** 自动检查中 */
        Self_checking((byte) 1),
        /** 活动中 */
        Active((byte) 2),
        /** 无效 */
        Invalid((byte) 3);

        public final byte index;

        private RadarStatus(byte idx) {
            this.index = idx;
        }

        public static RadarStatus getByIndex(byte index) {
            RadarStatus[] values = values();
            for (RadarStatus item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return null;
        }
    }

    /** 与障碍物最近的传感器 */
    public static enum RRS_Barrier_Position
    {
        NoObstacleDetected((byte) 0),
        Rearleft((byte) 1),
        RearleftMiddle((byte) 2),
        RearRightMiddle((byte) 3),
        RearRight((byte) 4),
        FrontLeft((byte) 5),
        FrontRight((byte) 6),
        Invalid((byte) 7);

        public final byte index;

        private RRS_Barrier_Position(byte idx) {
            this.index = idx;
        }

        public static RRS_Barrier_Position getByIndex(byte index) {
            RRS_Barrier_Position[] values = values();
            for (RRS_Barrier_Position item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return null;
        }
    }

    public static final Parcelable.Creator<RadarInfo> CREATOR = new Parcelable.Creator<RadarInfo>() {
        @Override
        public RadarInfo createFromParcel(Parcel source) {
            return new RadarInfo(
                    source.readByte() == 1,
                    (RadarStatus) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RRS_Barrier_Position) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable(),
                    (RadarDistance) source.readSerializable());
        }

        @Override
        public RadarInfo[] newArray(int size) {
            return new RadarInfo[size];
        }
    };

    /** 系统失效标志 */
    public final boolean isFailure;
    /** 倒车雷达系统状态 */
    public final RadarStatus mRadarStatus;
    /** 距离最近的传感器距离 */
    public final RadarDistance nearestDistance;
    /** 与障碍物最近的传感器 */
    public final RRS_Barrier_Position mRRS_Barrier_Position;
    /** 后右中sensor 障碍物距离 */
    public final RadarDistance mRRM;
    /** 后左中sensor 障碍物距离 */
    public final RadarDistance mLRM;
    /** 右后角sensor 障碍物距离 */
    public final RadarDistance mRRC;
    /** 左后角sensor 障碍物距离 */
    public final RadarDistance mLRC;
    /** 右前角 sensor 障碍物距离 */
    public final RadarDistance mRFC;
    /** 左前角sensor 障碍物距离 */
    public final RadarDistance mLFC;
    /** 左前中sensor 障碍物距离 */
    public final RadarDistance mLFM;
    /** 右前中sensor 障碍物距离 */
    public final RadarDistance mRFM;

    public RadarInfo(boolean isFailure, RadarStatus mRadarStatus, RadarDistance nearestDistance,
            RRS_Barrier_Position mRRS_Barrier_Position, RadarDistance mRRM, RadarDistance mLRM,
            RadarDistance mRRC, RadarDistance mLRC, RadarDistance mRFC, RadarDistance mLFC,
            RadarDistance mLFM, RadarDistance mRFM)
    {
        this.isFailure = isFailure;
        this.mRadarStatus = mRadarStatus;
        this.nearestDistance = nearestDistance;
        this.mRRS_Barrier_Position = mRRS_Barrier_Position;
        this.mRRM = mRRM;
        this.mLRM = mLRM;
        this.mRRC = mRRC;
        this.mLRC = mLRC;
        this.mRFC = mRFC;
        this.mLFC = mLFC;
        this.mLFM = mLFM;
        this.mRFM = mRFM;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.isFailure ? 1 : 0));
        dest.writeSerializable(this.mRadarStatus);
        dest.writeSerializable(this.nearestDistance);
        dest.writeSerializable(this.mRRS_Barrier_Position);
        dest.writeSerializable(this.mRRM);
        dest.writeSerializable(this.mLRM);
        dest.writeSerializable(this.mRRC);
        dest.writeSerializable(this.mLRC);
        dest.writeSerializable(this.mRFC);
        dest.writeSerializable(this.mLFC);
        dest.writeSerializable(this.mLFM);
        dest.writeSerializable(this.mRFM);
    }

}
