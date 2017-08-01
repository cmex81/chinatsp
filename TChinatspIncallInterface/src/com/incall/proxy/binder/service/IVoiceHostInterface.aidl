package com.incall.proxy.binder.service;

import com.incall.proxy.binder.service.IVoiceClientInterface;

interface IVoiceHostInterface {    
	IVoiceClientInterface getVoiceClient();
    void setVoiceClient(in IVoiceClientInterface aVoiceClient);
}