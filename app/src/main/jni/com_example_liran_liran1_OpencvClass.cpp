#include "com_example_liran_liran1_OpencvClass.h"

JNIEXPORT jint JNICALL Java_com_example_liran_liran1_OpencvClass_faceDetection
  (JNIEnv *, jclass, jlong addrRgba)
  {
    Mat & frame =  *(Mat*)addrRgba;
    int isDetect;
    jint retVal;
    isDetect = detect(frame);
    retVal = (jint)isDetect;

    return retVal;

  }

JNIEXPORT jint JNICALL Java_com_example_liran_liran1_OpencvClass_bodyDetection
  (JNIEnv *, jclass, jlong addrRgba)
  {
    Mat & mRgba = *(Mat*)addrRgba;

     int isDetect;
     jint retVal;
     isDetect = detectBody(mRgba);
     retVal = (jint)isDetect;

     return retVal;

  }

  int detect(Mat& frame){
    char str_face[] = "who is it?";
    char face_txt[] = "Face detection";
    String face_cascade_name = "/mnt/sdcard/Download/haarcascade_frontalface_alt.xml";
    String eyes_cascade_name = "/mnt/sdcard/Download/haarcascade_eye_tree_eyeglasses.xml";
    CascadeClassifier face_cascade;
    CascadeClassifier eyes_cascade;



    //-- 1. Load the cascades
    if( !face_cascade.load( face_cascade_name ) ){ printf("--(!)Error loading\n");  };
    if( !eyes_cascade.load( eyes_cascade_name ) ){ printf("--(!)Error loading\n");  };

    std::vector<Rect> faces;
    Mat frame_gray;

    cvtColor( frame, frame_gray, CV_BGR2GRAY );
    equalizeHist( frame_gray, frame_gray );

    //-- Detect faces
    face_cascade.detectMultiScale( frame_gray, faces, 1.1, 2, 0|CV_HAAR_SCALE_IMAGE, Size(30, 30) );

    putText(frame, face_txt, Point2f(350,50), FONT_HERSHEY_PLAIN, 2, Scalar(150,75,255), 3,8);

    for( size_t i = 0; i < faces.size(); i++ )
    {
        Point center( faces[i].x + faces[i].width*0.5, faces[i].y + faces[i].height*0.5 );
        ellipse( frame, center, Size( faces[i].width*0.5, faces[i].height*0.5), 0, 0, 360, Scalar( 255, 0, 255 ), 4, 8, 0 );
        putText(frame, str_face, Point2f(300,200), FONT_HERSHEY_PLAIN, 2, Scalar(0,255,255), 3,8);
        Mat faceROI = frame_gray( faces[i] );
        std::vector<Rect> eyes;

        //-- In each face, detect eyes
        eyes_cascade.detectMultiScale( faceROI, eyes, 1.1, 2, 0 |CV_HAAR_SCALE_IMAGE, Size(30, 30) );

        for( size_t j = 0; j < eyes.size(); j++ )
        {
            Point center( faces[i].x + eyes[j].x + eyes[j].width*0.5, faces[i].y + eyes[j].y + eyes[j].height*0.5 );
            int radius = cvRound( (eyes[j].width + eyes[j].height)*0.25 );
            circle( frame, center, radius, Scalar( 255, 0, 0 ), 4, 8, 0 );
        }
    }

    if (faces.size() > 0)
        return 1;

    return 0;



  }

  int detectBody(Mat & frame)
  {
    String body_cascade_name = "/mnt/sdcard/Download/haarcascade_fullbody.xml";
    CascadeClassifier body_cascade;
    char str_body[] = "a person!";
    char body_txt[] = "Body detection";

    //-- 1. Load the cascades
     if( !body_cascade.load( body_cascade_name ) ){ printf("--(!)Error loading\n");  };

     std::vector<Rect> bodies;
     Mat frame_gray;

     cvtColor( frame, frame_gray, CV_BGR2GRAY );
     equalizeHist( frame_gray, frame_gray );

     //-- Detect bodies
     body_cascade.detectMultiScale( frame_gray, bodies, 1.1, 2, 0|CV_HAAR_SCALE_IMAGE, Size(30, 30) );

     putText(frame, body_txt, Point2f(300,200), FONT_HERSHEY_PLAIN, 2, Scalar(150,75,255), 3,8);

     for( size_t i = 0; i < bodies.size(); i++ )
     {
        rectangle(frame, Point(bodies[i].x, bodies[i].y),Point(bodies[i].x+bodies[i].width, bodies[i].y+bodies[i].height), Scalar(0,255,0));
        putText(frame, str_body, Point2f(100,100), FONT_HERSHEY_PLAIN, 2, Scalar(255,0,100), 3,8);
     }

     if (bodies.size() > 0)
             return 1;

     return 0;

  }