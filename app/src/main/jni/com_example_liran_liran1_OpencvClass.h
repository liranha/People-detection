/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
#include "opencv2/opencv.hpp"
#include "opencv2/highgui.hpp"


/* Header for class com_example_liran_liran1_OpencvClass */

using namespace cv;

#ifndef _Included_com_example_liran_liran1_OpencvClass
#define _Included_com_example_liran_liran1_OpencvClass
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     com_example_liran_liran1_OpencvClass
 * Method:    faceDetection
 * Signature: (J)V
 */
 int detect(Mat & frame);
 int detectBody(Mat & frame);


JNIEXPORT jint JNICALL Java_com_example_liran_liran1_OpencvClass_faceDetection
  (JNIEnv *, jclass, jlong);
 JNIEXPORT jint JNICALL Java_com_example_liran_liran1_OpencvClass_bodyDetection
    (JNIEnv *, jclass, jlong);

#ifdef __cplusplus
}
#endif
#endif
