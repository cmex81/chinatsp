package com.incall.proxy;

/**
 * 
 * @author longbuxin
 *
 */
public class ServiceNameManager {

	public static final String SETTING_MANAGER_SERVICENAME = "com.carui.iset._set_UI_SERVICE";
	public static final String MEDIA_MANAGER_SERVICENAME = "coagent.media.base";
    public static final String RADIO_MANAGER_SERVICENAME = "com.carui.iradio._radio_UI_SERVICE";
    public static final String BT_MANAGER_SERVICENAME = "com.carui._bluetooth_ui_service";
    public static final String SYSTEM_UPDATE_SERVICENAME = "com.carui.update.ui_service";
	public static final String SYSTEM_VOICE_SERVICENAME = "com.carui.voice.ui_service";
    public static final String SYSTEM_STORAGE_SERVICENAME = "com.carui.storage.ui_service";
	public static final String SYSTEM_SOURCE_SERVICENAME = "com.carui.source.ui_service";
	public static final String SYSTEM_BACKSTAGE_SERVICENAME = "com.carui.backstage.ui_service";
	public static final String SYSTEM_TBOX_SERVICENAME = "com.carui.tbox.ui_service";
	
	private static String mAllStartServiceActions[] = {
		"com.chinatsp.startServiceAction.source",
		"com.chinatsp.startServiceAction.music",
		"com.chinatsp.startServiceAction.setting",
		"com.chinatsp.startServiceAction.bluetooth",
		"com.chinatsp.startServiceAction.radio",
		"com.chinatsp.startServiceAction.mcu",
		"com.chinatsp.startServiceAction.daul"
	};
	
	public static String[] getAllStartServiceActions(){
		return mAllStartServiceActions;
	}

}
