LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

$(info 999)
LOCAL_PACKAGE_NAME := yyfjnidemoo
LOCAL_MODULE_TAGS := optional
LOCAL_SRC_FILES := $(call all-java-files-under, src)

ALL_DEFAULT_INSTALLED_MODULES += $(LOCAL_PACKAGE_NAME)

include $(BUILD_PACKAGE)

include $(call all-makefiles-under, $(LOCAL_PATH))