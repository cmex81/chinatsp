// IRadioInterface.aidl
package com.incall.proxy.binder.service;

// Declare any non-default types here with import statements

import com.incall.proxy.binder.callback.IRadioChangedCallback;
import com.incall.proxy.radio.RadioInfo;

interface IRadioInterface {
    void addFreqToPresetList(int index);
    void bandSwitch(int band);
    void addRadioChangedListener(in IRadioChangedCallback aListener);
 	void removeRadioChangedListener(in IRadioChangedCallback aListener);
    int[] getPresetList();
    RadioInfo getRadioInfo();
    int[] getRadioList();
    void loadPSFromPresetList(int index);
    void nextStation();
    void pause(boolean b);
 	void play();
 	void prevStation();
 	void requestUpdateRadioInfo();
 	void scan();
 	void search();
 	void seekDownByStep();
 	void seekUpByStep();
 	void setRadioFreq(int frequency);
 	void stop();

 	//双屏互动接口
    String[] getBandList();
    int getRadioFrequent();
    String getRadioName();
    boolean next();
    boolean prev();
}
