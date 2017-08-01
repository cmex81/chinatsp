LOCAL_PATH:=$(call my-dir)
include $(CLEAR_VARS)
include $(CLEAR_VARS)
LOCAL_JAVA_LIBRARIES := ChinatspIncallInterface
LOCAL_SRC_FILES := $(call all-java-files-under, src)
LOCAL_MODULE_TAGS := optional
LOCAL_CERTIFICATE := platform
LOCAL_PACKAGE_NAME := TMiscService
include $(BUILD_PACKAGE)
#include $(BUILD_JAVA_LIBRARY)
