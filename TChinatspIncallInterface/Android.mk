LOCAL_PATH:=$(call my-dir)
include $(CLEAR_VARS)
#LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_SRC_FILES := $(call all-subdir-java-files) 
LOCAL_SRC_FILES += \
    src/com/incall/proxy/bt/IBtPhone.aidl \
    src/com/incall/proxy/bt/IBtPhoneCallback.aidl \
    src/com/incall/proxy/binder/callback/ISettingCallBackInterface.aidl \
    src/com/incall/proxy/binder/service/ISettingInterface.aidl \
    src/com/incall/proxy/binder/callback/IMediaStatusCallBack.aidl \
    src/com/incall/proxy/media/IMusicPlaybackService.aidl \
    src/com/incall/proxy/binder/service/IUpdateInterface.aidl \
    src/com/incall/proxy/binder/callback/IUpdateCallbackInterface.aidl \
    src/com/incall/proxy/binder/callback/ICanCallBackInterface.aidl \
    src/com/incall/proxy/binder/service/ICanInterface.aidl \
    src/com/incall/proxy/binder/callback/IRadioChangedCallback.aidl \
    src/com/incall/proxy/binder/service/IRadioInterface.aidl \
    src/com/incall/proxy/binder/service/IBackStage.aidl \
    src/com/incall/proxy/binder/service/IStorageInterface.aidl \
    src/com/incall/proxy/binder/service/IVoiceClientInterface.aidl \
    src/com/incall/proxy/binder/callback/IStorageCallBackInterface.aidl \
    src/com/incall/proxy/binder/callback/IVoiceCallBackInterface.aidl \
    src/com/incall/proxy/binder/service/ISourceInterface.aidl \
    src/com/incall/proxy/binder/callback/ISourceCallBackInterface.aidl \
    src/com/incall/proxy/binder/service/IVoiceHostInterface.aidl \



LOCAL_MODULE_TAGS := optional
LOCAL_MODULE := ChinatspIncallInterface
LOCAL_CERTIFICATE := platform
include $(BUILD_JAVA_LIBRARY)
#include $(BUILD_STATIC_JAVA_LIBRARY)
