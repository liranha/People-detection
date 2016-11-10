LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

#opencv
OPENCVROOT := C:\android\OpenCV-3.1.0-android-sdk\OpenCV-android-sdk
OPENCV_CAMERA_MODULES:=on
OPENCV_INSTALL_MODULES:=on
OPENCV_LIB_TYPE:=STATIC
include $(OPENCVROOT)/sdk/native/jni/OpenCV.mk

LOCAL_SRC_FILES := com_example_liran_liran1_OpencvClass.cpp

LOCAL_LDLIBS += -llog
LOCAL_MODULE := MyOpencvLibs

include $(BUILD_SHARED_LIBRARY)