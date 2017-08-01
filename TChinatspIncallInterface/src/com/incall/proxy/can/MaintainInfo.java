
package com.incall.proxy.can;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/** 保养信息 */
public class MaintainInfo implements Parcelable {
    public static final Parcelable.Creator<MaintainInfo> CREATOR = new Parcelable.Creator<MaintainInfo>() {
        @Override
        public MaintainInfo createFromParcel(Parcel source) {
            Bundle aBundle = (Bundle) source.readParcelable(null);
            byte[] mParam = aBundle.getByteArray("mParam");
            return new MaintainInfo(mParam);
        }

        @Override
        public MaintainInfo[] newArray(int size) {
            return new MaintainInfo[size];
        }
    };

    private final byte[] mParam;

    public MaintainInfo(byte[] aParam)
    {
        this.mParam = aParam;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        Bundle aBundle = new Bundle();
        aBundle.putByteArray("mParam", this.mParam);
        dest.writeParcelable(aBundle, flags);
    }

    /** get Totality mileage information 获取总里程 */
    public int getTotalityMileageInformation() {
        int i = 0;
        try {
            i = this.mParam[9] << 16 | this.mParam[8] << 8 | this.mParam[7]; // TODO
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return i;
    }

    /** Remainder maintain mileage information(unit :1KM) 剩余保养里程信号（单位：1KM）： */
    public int getRemainderMaintainMileageInformation() {
        int i = 0;
        try {
            i = this.mParam[15] << 8 | this.mParam[14];
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return i;
    }

    /** 剩余保养时间 （天） */
    public int getLeaveMaintainDay() {
        int i = 0; // TODO
        
        return i;
    }

}
