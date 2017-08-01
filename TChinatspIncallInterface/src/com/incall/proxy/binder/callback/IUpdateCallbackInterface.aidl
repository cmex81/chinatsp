package com.incall.proxy.binder.callback;

interface IUpdateCallbackInterface {
    void updateStart(int state);
	void updateEnd(int state);
	void updateProgerss(int state,int mProgerss);
	void exception(int exception);
	void updateError(int frameTotal, int frameCrcErrorCnt, int frameCheckSumErrorCnt, int fileCheckSumErrorCnt, int fileRecycleCnt);
}