package com.incall.proxy.constant;

public class SettingsConstantsDef {
    
    /**
     * 电量值 BYTE
     * <p>物理值0~100%， 0x65-0xFE： Reserved, 0x7F: Invalid
     */
    public static final String CAR_EMS_SOC = "Car_Ems_soc";
    /**
     * Disp_low_power （提示框） BYTE
     * <p> 0x00：取消弹框
     * <p> 0x01：蓄电池电量低，电量XX%
     * <p> 0x02：蓄电池电量低，系统将在2分钟内关闭，请启动发动机
     * <p> 0x04：电量消耗过多，电源即将关闭
     */
    public static final String CAR_LOW_POWER = "Disp_low_power";
    /** 电源状态改变显示 广播 ACTION */
    public static final String COAGENT_ACTION_CAR_POWER_SETTE = "caogent.car.power.state";
    /** 按键广播action */
    public static final String COAGENT_ACTION_KEY_CHANGED = "com.coagent.intent.action.KEY_CHANGED";
    /** 按键测试广播action */
    public static final String COAGENT_ACTION_KEY_CHANGED_TEST_MODE = "com.coagent.intent.action.KEY_CHANGED_TEST_MODE";
    public static final String COAGENT_ACTION_OF_LINE = "com.coagent.intent.action.OF_LINE";
    /** 工厂自动测试模式 */
    public static final String COAGENT_AUTO_TEST_MODE = "coagent.start.testModel";
    /** Coagent theme 属性参数(String) */
    public static final String COAGENT_KEY_THEME = "coagent_theme";
    /** 
     * 长安主题
     * <p> 0 - DaytimeTheme 白天
     * <p> 1 - NightTheme 黑夜
     */
    public static final String[] COAGENT_THEME_CHANGAN = new String[] {
            "DaytimeTheme", "NightTheme"
    };
    /**
     * Geely主题
     * <p> 0 - crystal_blue 水晶蓝
     * <p> 1 - sport_red 运动红
     * <p> 2 - rural_rich_gold 土豪金
     */
    public static final String[] COAGENT_THEME_GEELY = new String[] {
            "crystal_blue", "sport_red", "rural_rich_gold"
    };
    /** 按键广播中带的按键值参数 */
    public static final String EXTRA_KEY_CODE = "Key_code";
    /** 按键广播中带的按键状态参数 */
    public static final String EXTRA_KEY_STATE = "Key_state";
    /** 提示音KEY键 */
    public static final String KEY_NOTIFY_VOLUME = "notify.volume";
    /**
     * 默认导航的intent settingprovider中的key值 用于获取默认导航的Intent 如:
     * <p> String naviString = Settings.System.getString(mContext.getContentResolver(), SettingsConstantsDef.SETTINGS_KEY_INTENT_NAVI);
     * <p> if(naviString != null){
     * <p> try {
     * <p> return Intent.parseUri(naviString, 0);
     * <p> catch (Exception e) {
     * <p> e.printStackTrace();
     * <p> }
     * <p> 修改默认导航的Intent 如：
     * <p> Settings.System.putString(mContext.getContentResolver(), SettingsConstantsDef.SETTINGS_KEY_INTENT_NAVI, naviIntent.toUri(0));
     * <p> 其中参数naviIntent为可以启动导航主Activity的Intent 
     */
    public static final String SETTINGS_KEY_INTENT_NAVI = "coagent_intent_navi";
    /** 测试模式，settingprovider中的key值 */
    public static final String SETTINGS_KEY_TEST_MODE = "coagent_settings_key_test_mode";

    /** 按键音级别  */
	public static enum KEY_VOLUME_LEVEL {
		HIGH,
		LOW,
		MIDDLE
	}
	
	/** 收音波段 */
    public static enum RadioBand {
        AM1,
        AM2,
        FM1,
        FM2,
        FM3
    }
	
	/** 按键值 */
    public static enum KeyCode {
        AM,
        AS,
        AUDIO,
        AUX,
        /** 全景按钮 */
        AVM,
        BACK,
        /** 盲区按钮 */
        BLD,
        CAMERA,
        CLK,
        DELETE,
        DEST,
        DISC,
        DISP,
        DOWN,
        EJECT,
        FB,
        FF,
        FM,
        FMAM,
        FOLD_DOWN,
        FOLD_UP,
        GAME,
        /** 挂电话 */
        HANDUP,
        HDD,
        /** 浏览器 */
        IE,
        /** 信息 */
        INFO,
        IPOD,
        JING,
        LEFT,
        MAIN,
        MAP,
        MEDIA,
        MENU,
        MUTE,
        NAVI,
        /** 一键导航 */
        NAVI_ONEKEY,
        NEXT,
        NUM0,
        NUM1,
        NUM2,
        NUM3,
        NUM4,
        NUM5,
        NUM6,
        NUM7,
        NUM8,
        NUM9,
        OK,
        OPTION,
        PAUSE,
        PLAY,
        PLAYPAUSE,
        POWER,
        PRE,
        RDM,
        RIGHT,
        RPT,
        SCAN,
        SD,
        SEEK_DOWN,
        SEEK_UP,
        SEL_LEFT,
        SEL_RIGHT,
        SETUP,
        SOS,
        SRC,
        STAR,
        STOP,
        TEL,
        TV,
        UP,
        USB,
        VOLDOWN,
        VOLUP,
        /** 语音识别 */
        VR
    }

    /** 按键状态 */
    public static enum KeyState {
        /** 按键按下 */
        DOWN,
        /** 按键按键如果按下超过1秒钟没有抬起将发送长按事件 */
        LONG_EVENT,
        /** 按键如果已经发送过长按事件后抬起按键时将发送此状态 */
        LONG_UP,
        /** 表示需要立即响应 */
        NONE,
        /** 按键抬起 */
        UP
    }
}
