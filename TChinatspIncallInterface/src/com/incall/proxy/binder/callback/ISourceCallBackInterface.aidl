package com.incall.proxy.binder.callback;

interface ISourceCallBackInterface {
    void onCurrnetInterruptNotify(boolean isInterrupted);
	void onAudioReadyNotify();
	void onProtocolNotify(in byte[] aProtocol);
	void onHostDeviceStateNotify(int prevState, int currentState);
}
