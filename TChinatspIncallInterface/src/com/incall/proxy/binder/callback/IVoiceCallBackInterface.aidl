package com.incall.proxy.binder.callback;

interface IVoiceCallBackInterface {
	/**
	 * 播放歌曲变化
	 */
	void onMusicNotify(in String id, in String forworld, in String title, in String artist, 
			in String album, in int source, in int aGenre);
	/**
	 * 播放控制变化
	 */
	void onCtrlNotify(in int aCtrl);
	/**
	 * 播放模式变化
	 */
	void onRepeatNotify(in int aRepeat);
	
	/**
	 * 拨打电话
	 */
	void onBtPhoneCallNotify(in String number);
	
	/**
	 * 发送短信
	 */
	void onBtPhoneMsgNotify(in String number,in String message);
	
	/**
	 * 查看短信
	 */
	void onBtPhoneViewMsgNotify(in int operation, in int msgType);
	
	/**
	 * 播放收音频率
	 */
	void onRadioNotify(in int aFrequency, in int band, in String name, in int radioType, 
			String aCountry, String aProvince, String aCity);
	
	/**
	 * 空调开关变化
	 */
	void onAirPowerNotify(in int aPower);
	
	/**
	 * 空调模式变化
	 */
	void onAirModeNotify(in int aMode);
	
	/**
	 * 空调温度变化
	 */
	void onAirTemperatureNotify(in int aOperate, in int aTemperature);
	
	/**
	 * 空调风速变化
	 */
	void onAirSpeedNotify(in int aSpeed);
	
	/**
	 * 空调风向变化
	 */
	void onAirDirectionNotify(in int aDirection);
	
	/**
	 * tts播报状态改变
	 */
	void onTtsPlayNotify(in boolean aIsEnd);
	
	/**
	 * tts播报中断
	 */
	void onTtsInterrupted();
	/**
     * 语音通知操作其他应用程序解析的结果
     */
	void onNLPResult(String actionJson);
	

    /**
     * 语音调用系统平台，请求其执行某个动作的回调
     */
    void onDoAction(String actionJson);
    
    /**
     *  应用添加主动交互处理结果的回调
     *  String requestCode 交互的codeid,用于识别每次交互的id
     *  String resultType 主动交互结果的类型
     *  1.timeout 响应超时，自动退出，此时cmdId为null
     *  2.exit 主动点击关闭按钮退出，此时cmdId为nulll
     *  3.finish 唤醒了自定义的命令，此时cmdId为唤醒的命令id，如第一条，cmdId则为“0”
     *  cmdId 主动交互结果id,用户标识用户的选择
     */
    void onManualInteractResult(String requestCode, String resultType, String cmdId);
}