
package com.incall.proxy.can;

import android.os.Parcel;
import android.os.Parcelable;

/** CarPM25Info信息 */
public class CarPM25Info implements Parcelable {

    /** Inside vehicle PM2.5 （车内 PM2.5 数值） */
    public final int InsideVehicle;
    /** Outside vehicle PM2.5 （车外 PM2.5 数值） */
    public final int OutsideVehicle;
    /** Inside PM2.5 level 车内 PM2.5 等级 */
    public final int InsidePM2_5Level;
    /** Outside PM2.5 level 车外 PM2.5 等级 */
    public final int OutsidePM2_5Level;

    public static final Parcelable.Creator<CarPM25Info> CREATOR = new Parcelable.Creator<CarPM25Info>() {
        @Override
        public CarPM25Info createFromParcel(Parcel source) {
            return new CarPM25Info(
                    source.readInt(),
                    source.readInt(),
                    source.readInt(),
                    source.readInt());
        }

        @Override
        public CarPM25Info[] newArray(int size) {
            return new CarPM25Info[size];
        }
    };

    public CarPM25Info(int InsideVehicle, int OutsideVehicle, int InsidePM2_5Level,
            int OutsidePM2_5Level)
    {
        this.InsideVehicle = InsideVehicle;
        this.OutsideVehicle = OutsideVehicle;
        this.InsidePM2_5Level = InsidePM2_5Level;
        this.OutsidePM2_5Level = OutsidePM2_5Level;
    }

    @Override
    public int describeContents()
    {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.InsideVehicle);
        dest.writeInt(this.OutsideVehicle);
        dest.writeInt(this.InsidePM2_5Level);
        dest.writeInt(this.OutsidePM2_5Level);
    }

}
