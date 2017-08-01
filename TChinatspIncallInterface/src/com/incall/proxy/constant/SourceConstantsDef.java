package com.incall.proxy.constant;

public class SourceConstantsDef {
	public static final java.lang.String ACTION_EXIT = "com.intent.action.EXIT";
	public static final java.lang.String COAGENT_ACTION_ACC_CHANGED = "com.coagent.intent.action.ACC_CHANGED";
	public static final java.lang.String COAGENT_ACTION_READY = "com.coagent.intent.action.READY";
	public static final java.lang.String COAGENT_ACTION_SOURCE_CHANGED = "com.coagent.intent.action.SOURCE_CHANGED"; 
	public static final java.lang.String COAGENT_COMPLETED = "com.coagent.intent.action.BOOT_COMPLETED";
	public static final java.lang.String EXTRA_ACC_STATE = "acc_state";
	public static final java.lang.String EXTRA_SOURCE_FROM = "extra_from";
	public static final java.lang.String EXTRA_SOURCE_TO = "extra_to";

	public static final int HOST_DEVICE_STATE_NORMAL = 0;
	public static final int HOST_DEVICE_STATE_UNREADY = 1;
	public static final int HOST_DEVICE_STATE_WARNNING = 2;
	public static final int HOST_DEVICE_STATE_REVERSE= 4;
	public static final int HOST_DEVICE_STATE_STANDBY = 8;
	public static final int HOST_DEVICE_STATE_BLACKOUT = 16;
	public static final int HOST_DEVICE_STATE_ACCOFF = 32;
	public static final int HOST_DEVICE_STATE_MUTE = 64;
	public static final int HOST_DEVICE_STATE_ILLUMI = 128;
	public static final int HOST_DEVICE_STATE_VIDEO_DISABLE = 256;
	public static final int HOST_DEVICE_STATE_INTERRUPTED = 512;
	public static final int HOST_DEVICE_STATE_COLD_START = 1024;
	public static final int HOST_DEVICE_STATE_BLINDAREA = 2048;
	public static final int HOST_DEVICE_STATE_USB_HOST_OVER_CURRENT = 4096;
	public static final int HOST_DEVICE_STATE_USB_OTG_OVER_CURRENT = 8192;
	public static final int HOST_DEVICE_STATE_REVERSE_CHANL_STATUS = 16384;
	public static final int HOST_DEVICE_STATE_BLINDAREA_CHANL_STATUS = 32768;
	public static final int HOST_DEVICE_STATE_DIVIDE_VIDEOSIGNAL_LOCKED = 65536;
	public static final int HOST_DEVICE_STATE_AVM_MODE = 131072;

	public static enum SEAT_TYPE {
		front_seat, back_seat;
	}

	public static enum SourceID {
		TUNER, TUNER_ONLINE, DVD, CDBOX, ATV, NAVI, R_AUX, DTV, SD, XM, IPOD, HDD, USB, USB_EXT, CARLIFE, CAMERA, F_AUX, SIRIUS, HDRADIO, PANDORA, APP, AVOFF, MHL, MIRROR_LINK, SYS_STANDBY, TBOX, RES_IN, HDD_VIDEO, DISC_AUDIO, DISC_VIDEO, DISC_IMAGE, IPOD_AUDIO, IPOD_VIDEO, USB_VIDEO, USB_EXT_VIDEO, SD_AUDIO, SD_VIDEO, SD_IMAGE, CAR_SYNC, CAR_MEDIA, CAR_CDC, CAR_TUNER, ON_STAR, NUM, RDS, MUSIC, MUSIC_ONLINE, MUSIC_FAVORITE, VIDEO, BTRINGTONE, BTPHONE, BTAUDIO, AUX, DVR, BTUIPHONE, BTUIAUDIO, SWC, REAR, EQ, SETTINGS, BLACKOUT, VOICE, TTS, NEWS, MULTI_MEDIA, MULTI_MEDIA_VIDEO, MEDIA_KEY, SOURCE_OFF, NULL, OTHER_APP;

		public byte index;
	}
}
