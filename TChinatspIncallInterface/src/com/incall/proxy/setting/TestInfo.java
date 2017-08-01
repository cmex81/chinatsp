
package com.incall.proxy.setting;

import android.R.integer;
import android.os.Parcel;
import android.os.Parcelable;

public class TestInfo implements Parcelable {

    public static enum EQ_Mode {
        /** G_EQ_MODE_Classic */
        Classic,
        /** G_EQ_MODE_Customer */
        customer,
        /** G_EQ_MODE_Human */
        Jazz,
        /** G_EQ_MODE_POP */
        Pop,
        /** G_EQ_MODE_Rock */
        Rock,
        /** G_EQ_MODE_Standard */
        standard
    }

    /** 自动老化 false：off true：on */
    public boolean Auto_Aging;
    /** 风扇控制 0：off 1：low level 2：middle level 3：High level */
    public int Fan_Control;
    /** 指标测试 0：off 1：on */
    public boolean Quality_Test;
    /** 按键测试 0：off 1：on */
    public boolean Keyboard_Test;

    public TestInfo() {
    }

    public TestInfo(int fan, boolean auto_aging, boolean quaility, boolean keyboard) {
        this.Fan_Control = fan;
        this.Auto_Aging = auto_aging;
        this.Quality_Test = quaility;
        this.Keyboard_Test = keyboard;
    }

    private TestInfo(Parcel in) {
        readFromParcel(in);
    }

    public static final Parcelable.Creator<TestInfo> CREATOR = new Parcelable.Creator<TestInfo>() {

        @Override
        public TestInfo createFromParcel(Parcel source) {
            return new TestInfo(source);
        }

        @Override
        public TestInfo[] newArray(int size) {
            return new TestInfo[size];
        }

    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(Fan_Control);
        dest.writeByte((byte) (Auto_Aging ? 1 : 0));
        dest.writeByte((byte) (Quality_Test ? 1 : 0));
        dest.writeByte((byte) (Keyboard_Test ? 1 : 0));
    }

    public void readFromParcel(Parcel source) {
        Fan_Control = source.readInt();
        Auto_Aging = (source.readByte() != 0);
        Quality_Test = (source.readByte() != 0);
        Keyboard_Test = (source.readByte() != 0);
    }

}
