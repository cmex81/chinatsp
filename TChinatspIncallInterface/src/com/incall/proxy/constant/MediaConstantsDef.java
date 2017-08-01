package com.incall.proxy.constant;

public class MediaConstantsDef {
	
	public static enum PLAY_MODE {
		REPEAT_ALL,
		REPEAT_FOLDER,
		REPEAT_ONE,
		SHUFFLE_ON
	}
	
	public static enum REPEAT_MODE{
		REPEAT_ALL,
		REPEAT_FOLDER,
		REPEAT_OFF,
		REPEAT_ONE
	}
	
	public static enum SHUFFLE_MODE{
		SHUFFLE_OFF,
		SHUFFLE_ON
	}
	
	public static enum MEDIA_TYPE {
		IMAGE,
		INVALID,
		MUSIC,
		VIDEO
	}
	
	public static enum SCAN_STATUS{
		IDLE,
		SCAN_FINISH,
		SCANING
	}
	
	public static enum PLAY_STATE{
		IDLE ,
		PAUSE ,
		PLAY ,
		PREPARED ,
		STOP 
	}
}
