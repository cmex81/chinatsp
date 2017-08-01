package com.incall.proxy.can;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/** 行车电脑信息 */
public class CarComputerInfo implements Parcelable {
    
    public static final android.os.Parcelable.Creator<CarComputerInfo> CREATOR = new Creator<CarComputerInfo>() {
        
        @Override
        public CarComputerInfo[] newArray(int size) {
            return new CarComputerInfo[size];
        }
        
        @Override
        public CarComputerInfo createFromParcel(Parcel source) {
            Bundle aBundle = (Bundle)source.readParcelable(Bundle.class.getClassLoader());
            byte[] mParam = aBundle.getByteArray("mParam");
            return new CarComputerInfo(mParam);
        }
    };
    
    /** 协议参数  */
    public final byte[] mParam;
    
    public CarComputerInfo(byte[] aParam){
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
    
    /** 瞬时油耗 EMS_FuelConsumption 100ms油耗 oil 每100MS油耗量 return ml/100ms */
    public float getEMS_FuelConsumption() {
        float i = 0.0F;
        try {
            i = (float) ((this.mParam[0] & 0xFF) * 0.015D);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return i;
    }
    
    /** 平均油耗 */
    public float getAvgFuelConsumption() {
        float i = 0.0F;
        try {
            i = (float) ((this.mParam[1] & 0xFF) * 0.1D);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return i;
    }
    
    /** 余油可行驶里程 IP_DTE  */
    public int getIpDte() {
        int i = 0;
        try {
            i = ((this.mParam[2] & 0xFF) << 8) | (this.mParam[3] & 0xFF);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return i;
    }
    
    /** 车速信号 ESP_VehicleSpeed  */
    public float getVehicleSpeed() {
        float i = 0.0F;
        try {
            i = (float) (((this.mParam[4] & 0xFF) << 8 | (this.mParam[5] & 0xFF)) * 0.05625D);
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return i;
    }
    
    public static String getMcuBytes2HexString(byte[] msgData) {
        StringBuilder buffer = new StringBuilder();
        try {
            for (int i = 0; i < msgData.length; i++) {
                buffer.append(" 0x");
                buffer.append((msgData[i] & 0xFF) < 16 ? "0" : "");
                buffer.append(Integer.toHexString(msgData[i] & 0xFF).toUpperCase());
            }
        } catch (Exception localException) {
            localException.printStackTrace();
        }
        return buffer.toString().trim();
    }
    
    public void mytoString(){
        Log.i("CarComputerInfo", "Param = " + getMcuBytes2HexString(this.mParam));
        Log.i("CarComputerInfo", "EMS_FuelConsumption = " + getEMS_FuelConsumption());
        Log.i("CarComputerInfo", "AvgFuelConsumption = " + getAvgFuelConsumption());
        Log.i("CarComputerInfo", "IpDte = " + getIpDte());
        Log.i("CarComputerInfo", "VehicleSpeed = " + getVehicleSpeed());
    }
}
