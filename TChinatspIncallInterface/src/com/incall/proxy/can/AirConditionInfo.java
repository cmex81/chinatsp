package com.incall.proxy.can;

import android.os.Parcel;
import android.os.Parcelable;

/** AirConditionInfo 空调信息类 */
public class AirConditionInfo implements Parcelable {

    /** 自动模式 */
    public static enum AutoMode {
        /** 关 */
        OFF((byte) 0),
        /** auto-small模式 */
        SMALL((byte) 1),
        /** auto-big模式 */
        BIG((byte) 2),
        /** 非法 */
        INVALID((byte) 3);

        public final byte index;

        private AutoMode(byte idx) {
            this.index = idx;
        }

        public static AutoMode getByIndex(byte index) {
            AutoMode[] values = values();
            for (AutoMode item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return INVALID;
        }
    }
    
    /** 内外循环模式  */
    public static enum CircleStatus {
        /** 内循环 */
        INSIDE((byte) 0),
        /** 外循环 */
        OUTSIDE((byte) 1),
        /** 自动 */
        AUTOMATIC((byte) 2),
        /** 无效 */
        INVALID((byte) 3);

        public final byte index;

        private CircleStatus(byte idx) {
            this.index = idx;
        }

        public static CircleStatus getByIndex(byte index) {
            CircleStatus[] values = values();
            for (CircleStatus item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return INVALID;
        }
    }
    
    /** 前排自动手动切换显示 */
    public static enum FrontSwitch {
        /** 常规模式 */
        MANUAL((byte) 0),
        /** 自动模式 */
        AUTO((byte) 1),
        /** 关闭 */
        OFF((byte) 2),
        /** 无效 */
        INVALID((byte) 3);

        public final byte index;

        private FrontSwitch(byte idx) {
            this.index = idx;
        }

        public static FrontSwitch getByIndex(byte index) {
            FrontSwitch[] values = values();
            for (FrontSwitch item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return INVALID;
        }
    }
    
    /** 空调开关状态类型 */
    public static enum Switch {
        /** 关 */
        OFF((byte) 0),
        /** 开 */
        ON((byte) 1),
        /** 无效 */
        INVALID((byte) 2);
        
        public final byte index;

        private Switch(byte idx) {
            this.index = idx;
        }

        public static Switch getByIndex(byte index) {
            Switch[] values = values();
            for (Switch item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return INVALID;
        }
    }
    
    /** 自动空调温区切换显示  */
    public static enum TempRegionMode {
        /** 单区模式 */
        SINGLE((byte) 0),
        /** 双区模式 */
        DUAL((byte) 1),
        /** 三区模式 */
        TRIPLE((byte) 2),
        /** 四区模式 */
        QUADRUPLE((byte) 3);

        public final byte index;

        private TempRegionMode(byte idx) {
            this.index = idx;
        }

        public static TempRegionMode getByIndex(byte index) {
            TempRegionMode[] values = values();
            for (TempRegionMode item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return SINGLE;
        }
    }
    
    /** 自动空调温区切换显示 */
    public static enum WindMode {
        /** 吹脸 */
        FACE((byte) 0),
        /** 吹脸、吹脚 */
        FACE_FEET((byte) 1),
        /** 吹脚 */
        FEET((byte) 2),
        /** 吹脚、除霜 */
        FEET_DEFROSTER((byte) 3),
        /** 除霜 */
        DEFROSTER((byte) 4),
        /** 错误 */
        ERROR((byte) 7);

        public final byte index;

        private WindMode(byte idx) {
            this.index = idx;
        }

        public static WindMode getByIndex(byte index) {
            WindMode[] values = values();
            for (WindMode item : values) {
                if (item.index == index) {
                    return item;
                }
            }
            return ERROR;
        }
    }
    
    public static final Parcelable.Creator<AirConditionInfo> CREATOR = new Parcelable.Creator<AirConditionInfo>() {

        @Override
        public AirConditionInfo createFromParcel(Parcel source) {
            return new AirConditionInfo(
                    (Switch) source.readSerializable(),
                    (Switch) source.readSerializable(),
                    (CircleStatus) source.readSerializable(),
                    (Switch) source.readSerializable(),
                    source.readByte() == 1,
                    (FrontSwitch) source.readSerializable(),
                    (TempRegionMode) source.readSerializable(),
                    (WindMode) source.readSerializable(),
                    source.readFloat(),
                    source.readFloat(),
                    (Switch) source.readSerializable(),
                    (Switch) source.readSerializable(),
                    (AutoMode) source.readSerializable(),
                    source.readFloat(),
                    source.readFloat(),
                    source.readInt(),
                    (Switch) source.readSerializable(),
                    (Switch) source.readSerializable(),
                    (Switch) source.readSerializable(),
                    (Switch) source.readSerializable(),
                    (Switch) source.readSerializable());
        }

        @Override
        public AirConditionInfo[] newArray(int size) {
            return new AirConditionInfo[size];
        }
        
    };
    
    /** 前排空调总开关 */
    public final Switch frontMain;
    /** A/C 制冷开关状态 */
    public final Switch refrigeration;
    /** 内外循环状态 */
    public final CircleStatus circleStatus;
    /** 分区联动开关 */
    public final Switch subregion;
    /** 压缩机是否活动 */
    public final boolean isCompressorActive;
    /** 前排自动手动切换显示 */
    public final FrontSwitch frontSwitch;
    /** 自动空调温区切换显示 */
    public final TempRegionMode tempRegionMode;
    /** 前吹风模式 */
    public final WindMode windMode;
    /** 车外温度信号：-40 度~+87 度 */
    public final float outsideTemperature;
    /** 车内温度信号：-40 度~+87 度 */
    public final float insideTemperature;
    /** 前自动除霜 */
    public final Switch frontDefrost;
    /** 后自动除霜 */
    public final Switch rearDefrost;
    /** Auto */
    public final AutoMode autoMode;
    /** 主驾驶温度调节温度范围LO(-1),HI(-2),INVALID(-3),(大于0时)为温度值(范围15℃-32℃) */
    public final float leftTemperature;
    /** 副驾驶温度调节温度范围LO(-1),HI(-2),INVALID(-3),(大于0时)为温度值(范围15℃-32℃) */
    public final float rightTemperature;
    /** 风量设置(0~14 level) */
    public final int windLevel;
    /** geely MAX AC */
    public final Switch acMaxAc;
    /** geely 后空调面板状态 */
    public final Switch acRcpState;
    /** geely 后空调按键锁状态 */
    public final Switch acRearKeyLock;
    /** geely 负离子发生器状态 */
    public final Switch acIonizerState;
    /** geely 空气质量传感器请求状态 */
    public final Switch acAqsState;
    

    public AirConditionInfo(Switch frontMain, Switch refrigeration, CircleStatus circleStatus,
            Switch subregion, boolean isCompressorActive, FrontSwitch frontSwitch,
            TempRegionMode tempRegionMode, WindMode windMode, float outsideTemperature,
            float insideTemperature, Switch frontDefrost, Switch rearDefrost, AutoMode autoMode,
            float leftTemperature, float rightTemperature, int windLevel, Switch acMaxAc,
            Switch acRcpState, Switch acRearKeyLock, Switch acIonizerState, Switch acAqsState) {
        this.frontMain = frontMain;
        this.refrigeration = refrigeration;
        this.circleStatus = circleStatus;
        this.subregion = subregion;
        this.isCompressorActive = isCompressorActive;
        this.frontSwitch = frontSwitch;
        this.tempRegionMode = tempRegionMode;
        this.windMode = windMode;
        this.outsideTemperature = outsideTemperature;
        this.insideTemperature = insideTemperature;
        this.frontDefrost = frontDefrost;
        this.rearDefrost = rearDefrost;
        this.autoMode = autoMode;
        this.leftTemperature = leftTemperature;
        this.rightTemperature = rightTemperature;
        this.windLevel = windLevel;
        this.acMaxAc = acMaxAc;
        this.acRcpState = acRcpState;
        this.acRearKeyLock = acRearKeyLock;
        this.acIonizerState = acIonizerState;
        this.acAqsState = acAqsState;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.frontMain);
        dest.writeSerializable(this.refrigeration);
        dest.writeSerializable(this.circleStatus);
        dest.writeSerializable(this.subregion);
        dest.writeByte((byte)(this.isCompressorActive ? 1 : 0));
        dest.writeSerializable(this.frontSwitch);
        dest.writeSerializable(this.tempRegionMode);
        dest.writeSerializable(this.windMode);
        dest.writeFloat(this.outsideTemperature);
        dest.writeFloat(this.insideTemperature);
        dest.writeSerializable(this.frontDefrost);
        dest.writeSerializable(this.rearDefrost);
        dest.writeSerializable(this.autoMode);
        dest.writeFloat(this.leftTemperature);
        dest.writeFloat(this.rightTemperature);
        dest.writeInt(this.windLevel);
        dest.writeSerializable(this.acMaxAc);
        dest.writeSerializable(this.acRcpState);
        dest.writeSerializable(this.acRearKeyLock);
        dest.writeSerializable(this.acIonizerState);
        dest.writeSerializable(this.acAqsState);
    }
    
    @Override
    public String toString() {
        return "AirConditionInfo(frontMain=" + this.frontMain +
                ",\n refrigeration=" + this.refrigeration +
                ",\n circleStatus=" + this.circleStatus +
                ",\n subregion=" + this.subregion +
                ",\n isCompressorActive=" + this.isCompressorActive +
                ",\n frontSwitch=" + this.frontSwitch +
                ",\n tempRegionMode=" + this.tempRegionMode +
                ",\n windMode=" + this.windMode +
                ",\n outsideTemperature=" + this.outsideTemperature +
                ",\n insideTemperature=" + this.insideTemperature +
                ",\n frontDefrost=" + this.frontDefrost +
                ",\n rearDefrost=" + this.rearDefrost +
                ",\n autoMode=" + this.autoMode +
                ",\n leftTemperature=" + this.leftTemperature +
                ",\n rightTemperature=" + this.rightTemperature +
                ",\n windLevel=" + this.windLevel +
                ",\n acMaxAc=" + this.acMaxAc +
                ",\n acRcpState=" + this.acRcpState +
                ",\n acRearKeyLock=" + this.acRearKeyLock +
                ",\n acIonizerState=" + this.acIonizerState +
                ",\n acAqsState=" + this.acAqsState +
                ")";
    }
    
}
