package com.incall.proxy.update;


public final class UpdateManagerUtil {
    public static final String STR_OS_TARGET_PATH = "/cache/update.zip";
    public static final String STR_LOGO_PATH = "/cache/logo.bmp";
    public static final String STR_MCU_SOURCE_PATH = "/storage/udisk/McuAppUpdate.img";
    public static final String MD5_OF_MCU_SOURCE_PATH = "/storage/udisk/CoagentUpgrade/MCU/McuAppUpdate.md5";
    public static final String MD5_OF_SYSTEM_SOURCE_PATH = "/storage/udisk/CoagentUpgrade/OS/update.md5";
    public static final String PATH_OF_MCU_SOURCE = "/storage/udisk/CoagentUpgrade/MCU/McuAppUpdate.img";
    public static final String PATH_OF_SYSTEM_SOURCE = "/storage/udisk/CoagentUpgrade/OS/update.zip";
    public static final String STR_GPS_TARGET_PATH = "/storage/emulated/0/autonavidata";
    public static final String STR_TW8836_SOURCE_PATH = "/storage/udisk/TW8836FlashUpdate.BIN";
    public static final byte EXCEPTION_USB_NO = 0;
    public static final byte EXCEPTION_OS_NO = 1;
    public static final byte EXCEPTION_MCU_NO = 2;
    public static final byte EXCEPTION_GPS_NO = 3;
    public static final byte EXCEPTION_TW8836_NO = 4;
    public static final byte EXCEPTION_MCU_READ = 5;
    public static final byte EXCEPTION_TW8836_READ = 6;
    public static final byte EXCEPTION_SDCARD_NO_FREE = 7;
    public static final byte UPDATE_STATE_OS = 0;
    public static final byte UPDATE_STATE_MCU = 1;
    public static final byte UPDATE_STATE_GPS = 2;
    public static final byte UPDATE_STATE_TW8836 = 3;
    public static final byte UPDATE_STATE_LCD927 = 4;

    public UpdateManagerUtil() {
    }
}
