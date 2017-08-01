
package com.incall.proxy.can;

import android.os.Parcel;
import android.os.Parcelable;

/** 车门信息 */
public class DoorInfo implements Parcelable {
    public static final Parcelable.Creator<DoorInfo> CREATOR = new Parcelable.Creator<DoorInfo>() {
        @Override
        public DoorInfo createFromParcel(Parcel source) {
            return new DoorInfo(
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1,
                    source.readByte() == 1);
        }

        @Override
        public DoorInfo[] newArray(int size) {
            return new DoorInfo[size];
        }
    };
    
    /** 前左车门状态 */
    public final boolean isFrontLeftOpened;
    /** 前右车门状态 */
    public final boolean isFrontRightOpened;
    /** 后左车门状态 */
    public final boolean isRearLeftOpened;
    /** 后右车门状态 */
    public final boolean isRearRightOpend;
    /** 后备箱门状态 */
    public final boolean isTrunkOpened;
    /** 发动机仓门状态 */
    public final boolean isEngineOpened;
    /** 天窗状态 */
    public final boolean isSunroofOpened;

    public DoorInfo(boolean isFrontLeftOpened, boolean isFrontRightOpened,
            boolean isRearLeftOpened, boolean isRearRightOpend, boolean isTrunkOpened,
            boolean isEngineOpened, boolean isSunroofOpened)
    {
        this.isFrontLeftOpened = isFrontLeftOpened;
        this.isFrontRightOpened = isFrontRightOpened;
        this.isRearLeftOpened = isRearLeftOpened;
        this.isRearRightOpend = isRearRightOpend;
        this.isTrunkOpened = isTrunkOpened;
        this.isEngineOpened = isEngineOpened;
        this.isSunroofOpened = isSunroofOpened;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (this.isFrontLeftOpened ? 1 : 0));
        dest.writeByte((byte) (this.isFrontRightOpened ? 1 : 0));
        dest.writeByte((byte) (this.isRearLeftOpened ? 1 : 0));
        dest.writeByte((byte) (this.isRearRightOpend ? 1 : 0));
        dest.writeByte((byte) (this.isTrunkOpened ? 1 : 0));
        dest.writeByte((byte) (this.isEngineOpened ? 1 : 0));
        dest.writeByte((byte) (this.isSunroofOpened ? 1 : 0));
    }

}
