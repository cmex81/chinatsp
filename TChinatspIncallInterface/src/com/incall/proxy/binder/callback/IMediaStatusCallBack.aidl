package  com.incall.proxy.binder.callback;
interface  IMediaStatusCallBack
{
      void completionCallBack(String msg);
      void errorCallback(String msg,int arg0,int arg1);
      void preparedCallback(String msg);
}