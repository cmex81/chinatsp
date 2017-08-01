package com.incall.proxy.binder.service;

 interface IBackStage {
	 void enableAdb(boolean enable);
	 void 	enableLog(boolean enable);
	 void enableWifiAdb(boolean var1);
	 void 	saveLogToExt();
	 void 	setUsbHost(boolean isHost);
	 void 	startUpOriginSettings();
}
