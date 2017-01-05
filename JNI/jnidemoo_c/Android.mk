LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := libjnidemoo
LOCAL_SRC_FILES := jnidemoo.c
LOCAL_C_INCLUDES := jnidemoo.h
LOCAL_MODULE_TAGS := optional

ALL_DEFAULT_INSTALLED_MODULES += $(LOCAL_MODULE)

include $(BUILD_SHARED_LIBRARY)