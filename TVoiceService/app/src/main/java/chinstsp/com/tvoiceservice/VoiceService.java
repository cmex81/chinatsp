package chinstsp.com.tvoiceservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.support.annotation.Nullable;

import com.incall.proxy.binder.callback.IVoiceCallBackInterface;
import com.incall.proxy.binder.service.IVoiceClientInterface;

/**
 * Created by ryan on 02/08/2017.
 */
public class VoiceService extends Service{
    private RemoteCallbackList<IVoiceCallBackInterface> remoteCallbackList = new RemoteCallbackList<>();
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    public IVoiceClientInterface.Stub mBinder = new IVoiceClientInterface.Stub() {
        @Override
        public void registerCallBack(IVoiceCallBackInterface iVoiceCallBackInterface) throws RemoteException {
            remoteCallbackList.register(iVoiceCallBackInterface);
        }

        @Override
        public void unRegisterCallBack(IVoiceCallBackInterface iVoiceCallBackInterface) throws RemoteException {
            remoteCallbackList.unregister(iVoiceCallBackInterface);
        }

        @Override
        public int getEngineType() throws RemoteException {
            return 0;
        }

        @Override
        public void speak(String s) throws RemoteException {

        }

        @Override
        public void speakOther(String s) throws RemoteException {

        }

        @Override
        public void pauseOtherSpeak() throws RemoteException {

        }

        @Override
        public void stopOtherSpeak() throws RemoteException {

        }

        @Override
        public void resumeOtherSpeak() throws RemoteException {

        }

        @Override
        public void stopSpeak() throws RemoteException {

        }

        @Override
        public void pauseSpeak() throws RemoteException {

        }

        @Override
        public void resumeSpeak() throws RemoteException {

        }

        @Override
        public void setXFMode(int i) throws RemoteException {

        }

        @Override
        public void uploadMusicData(int i, String[] strings) throws RemoteException {

        }

        @Override
        public boolean sendElfData(int i, String s) throws RemoteException {
            return false;
        }

        @Override
        public void requestAudioFocus(boolean b) throws RemoteException {

        }

        @Override
        public boolean startSpeechClient(int i, String s) throws RemoteException {
            return false;
        }

        @Override
        public int sendManualInteract(String s, String s1, String s2, boolean b) throws RemoteException {
            return 0;
        }

        @Override
        public int closeManualInteract(String s, String s1, String s2) throws RemoteException {
            return 0;
        }

        @Override
        public int getTtsStatus() throws RemoteException {
            return 0;
        }
    };



}
