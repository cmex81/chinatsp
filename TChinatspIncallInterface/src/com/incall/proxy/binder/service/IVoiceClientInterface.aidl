package com.incall.proxy.binder.service;
import com.incall.proxy.binder.callback.IVoiceCallBackInterface;

interface IVoiceClientInterface {

	/**
	* 注册回调函数
	* @param aCallbackInterface: 回调函数
	*/
    void registerCallBack(in IVoiceCallBackInterface aCallbackInterface);
    
    /**
	* 注销回调函数
	* @param aCallbackInterface: 回调函数
	 */
    void unRegisterCallBack(in IVoiceCallBackInterface aCallbackInterface);
    
    /**
     * 获取语音引擎类型
     * 0：讯飞
     * 1：nuance
     */
    int getEngineType();
    /**
     * 播放tts
     */
    void speak(in String ttsText);
    
    void speakOther(in String ttsText);
    void pauseOtherSpeak();
    void stopOtherSpeak();
    void resumeOtherSpeak();
    
    void stopSpeak();
    
    void pauseSpeak();
    
    void resumeSpeak();
    
    /**
    *设置讯飞工作模式:
    *
    */
    void setXFMode(in int type);
    
    /**
     * 给语音助理发送自定义数据，用于构建识别词典
     */
    void uploadMusicData(in int type, in String[] data);
    
    /**
     * 第三方应用通过该接口传递小精灵数据
     */
    boolean sendElfData(in int type, in String data);
    
    /**
    *  应用请求录音通道的焦点. 应用再使用完录音通道之后必须释放焦点
    *  hasFocus  true 获取焦点    false 释放焦点
    */
    void requestAudioFocus(in boolean hasFocus);
    
    /**
    *  启动语音助理，  可设置全屏唤醒和分屏唤醒两种模式
    *  viewType  显示模式 0表示全屏，1表示半屏
    *  fromService  调用者的包名
    */
    boolean startSpeechClient(in int viewType, in String fromService);
    
    /**
    *  发送语音主动交互消息
    *  String requestCode 交互的codeid,用于识别每次交互的id
    *  String packageName 应用包名
    *  String msg 主动交互内容，为json类型的字符串
    *  boolean requireView 是否需要界面展示
    */
    int sendManualInteract(String requestCode, String packageName, String msg, boolean requireView);
   
    /**
    *  结束语音主动交互消息， 在app退出等不需要响应语音交互的情况下，退出主动交互。
    *  String requestCode 交互的codeid,用于识别每次交互的id
    *  String packageName 应用包名
    *  String msg 主动交互内容，为json类型的字符串
    */
    int closeManualInteract(String requestCode, String packageName, String msg);
}