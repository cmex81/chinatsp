package com.incall.proxy.constant;

public final class DiscConstantsDef {
    public static final int DVD_FLN_TYPMP = 1;
    public static final int DVD_FLN_TYPDA = 2;
    public static final int DVD_FLN_TYPDI = 3;
    public static final int DVD_FLN_TYPM4 = 4;
    public static final int DVD_FLN_TYPJP = 5;
    public static final int DVD_FLN_TYPME = 6;
    public static final int DVD_FLN_TYPWM = 7;
    public static final int DVD_FLN_TYPMG = 9;
    public static final int DVD_FLN_TYPJE = 10;
    public static final int DVD_FLN_TYPCD = 15;
    public static final int DVD_FLN_TYPAV = 18;
    public static final int DVD_FLN_TYPMP4 = 47;
    public static final int DVD_FLN_TYPUN = 255;
    public static final int UPDATE_SUCCESS = 1000;
    public static final int USB_NOT_INSERT = 1001;
    public static final int FILE_NOT_EXIST = 1002;
    public static final int DEVICE_NOT_CONNECT = 1003;
    public static final int END_UPDATE = 0;
    public static final int START_UPDATE = 1;
    public static final String DISC_ACTION = "com.coagent.disc.ACTION";
    public static final byte DVD_PST_DVD = 1;
    public static final byte DVD_PST_CD = 2;
    public static final int DVD_IR_PLAYPAUSE = 21;
    public static final int DVD_IR_NEXT = 25;
    public static final int DVD_IR_PREV = 29;
    public static final int DVD_IR_EJECT = 45;
    public static final int REPEAT_ONE = 0;
    public static final int REPEAT_ALL = 1;
    public static final int RANDOM_ALL = 2;
    public static final int CONNECT_SUCCESS = 0;
    public static final int CONNECT_DIS = 1;

    private DiscConstantsDef() {
    }

    public static enum DeviceState {
        No_Disc((byte)0),
        Disc_ejecting((byte)1),
        Disc_loading((byte)2),
        Disc_out((byte)3),
        Disc_in((byte)4),
        Error((byte)5),
        Time_up((byte)6);

        public final byte index;

        private DeviceState(byte aIndex) {
            this.index = aIndex;
        }

        public static DiscConstantsDef.DeviceState getByIndex(byte index) {
            DiscConstantsDef.DeviceState[] var4;
            int var3 = (var4 = values()).length;

            for(int var2 = 0; var2 < var3; ++var2) {
                DiscConstantsDef.DeviceState aDeviceState = var4[var2];
                if(aDeviceState.index == index) {
                    return aDeviceState;
                }
            }
            return null;
        }
    }
}
