LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

$(info 101010)
LOCAL_MODULE := libjnidemoojni
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := com_yyf_jnidemo_PropertyUtils.c
LOCAL_SHARED_LIBRARIES := libjnidemoo
LOCAL_C_INCLUDES := $(LOCAL_PATH)/../../jnidemoo_c/

ALL_DEFAULT_INSTALLED_MODULES += $(LOCAL_MODULE) 

include $(BUILD_SHARED_LIBRARY)