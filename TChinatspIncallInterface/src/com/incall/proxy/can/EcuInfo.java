package com.incall.proxy.can;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

public class EcuInfo implements Parcelable {

    public static final Parcelable.Creator<EcuInfo> CREATOR = new Parcelable.Creator<EcuInfo>() {
        @Override
        public EcuInfo createFromParcel(Parcel source) {
            Bundle aBundle = (Bundle) source.readParcelable(Bundle.class.getClassLoader());
            byte[] mParam = aBundle.getByteArray("mParam");
            return new EcuInfo(mParam);
        }

        @Override
        public EcuInfo[] newArray(int size) {
            return new EcuInfo[size];
        }
    };

    private final byte[] mParam;

    public EcuInfo(byte[] aParam)
    {
        this.mParam = aParam;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        Bundle aBundle = new Bundle();
        aBundle.putByteArray("mParam", this.mParam);
        dest.writeParcelable(aBundle, flags);
    }

    /** BCM（车身控制系统） 是否可用 */
    public boolean isBCMenable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 0 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** ABS（防抱死控制系统） 是否可用 */
    public boolean isABSenable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 1 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** MIL（电喷控制系统） 是否可用 */
    public boolean isMILenable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 2 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** EPB（电子驻车系统） 是否可用 */
    public boolean isEPBenable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 3 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** Electronic Program 电子稳定程序是否可用 */
    public boolean isElectronicEnable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 4 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** ESCL(电子转向锁控制系统)是否可用 */
    public boolean isESCLEnable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 5 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** air bag System 安全气囊系统是否可用 */
    public boolean isAirBagEnable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 6 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** PEPS（无钥匙进入系统）是否可用 */
    public boolean isPEPSEnable() {
        try {
            return ((this.mParam[0] & 0xFF) >> 7 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** lamp aiming System 大灯动态调光系统是否可用 */
    public boolean isLampAimingEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 0 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** TPMS（胎压监测系统）是否可用 */
    public boolean isTPMSEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 1 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** AC（空调系统）是否可用 */
    public boolean isACEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 2 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** PAC（智能轨迹倒车系统）是否可用 */
    public boolean isPACEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 3 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** IP（仪表）是否可用 */
    public boolean isIPEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 4 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** TBOX是否可用 */
    public boolean isTBOXEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 5 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** BSD(盲区检测系统)是否可用 */
    public boolean isBSDEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 6 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** RRS(倒车雷达)是否可用  */
    public boolean isRRSEnable() {
        try {
            return ((this.mParam[1] & 0xFF) >> 7 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** EPS 助力转向是否可用 */
    public boolean isEPSEnable() {
        try {
            return ((this.mParam[2] & 0xFF) >> 0 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** EBD是否可用 */
    public boolean isEBDEnable(){
        try {
            return ((this.mParam[2] & 0xFF) >> 1 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** 发动机是否可用 */
    public boolean isEngineEnable() {
        try {
            return ((this.mParam[2] & 0xFF) >> 2 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** 冷却液温度是否可用  */
    public boolean isLQYWDEnable() {
        try {
            return ((this.mParam[2] & 0xFF) >> 3 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** 发动机转速是否可用 */
    public boolean isFDJZSEnable() {
        try {
            return ((this.mParam[2] & 0xFF) >> 4 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** 节气门位置是否可用 */
    public boolean isJQMWZEnable() {
        try {
            return ((this.mParam[2] & 0xFF) >> 5 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

    /** 加速踏板是否可用  */
    public boolean isJSTBEnable() {
        try {
            return ((this.mParam[2] & 0xFF) >> 6 & 0x1) == 0;
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return false;
    }

}
