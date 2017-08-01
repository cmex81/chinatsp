
package com.incall.proxy.binder.callback;


import  com.incall.proxy.radio.RadioInfo;

interface IRadioChangedCallback {
   void onPresetListChanged(in int[] presetList);
   void onRadioInfoChanged(in RadioInfo aRadioInfo);
   void	onRadioListChanged(in int[] radioList);
   void onSignalChanged(int aSignalLevel);
}
