package com.example.liran.liran1;

/**
 * Created by liran on 21/10/2016.
 */

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.widget.Toast;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.CvType;
import org.opencv.core.Mat;


public class BodyDetection  extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    private Mat mRgba;
    private static String TAG = "BodyDetection";
    private JavaCameraView javaCameraView;
    private MediaPlayer mp;
    private int sound_type;
    private boolean flag_body;


    static{
        System.loadLibrary("MyOpencvLibs");
    }

    BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(this) {
        @Override
        public void onManagerConnected(int status) {
            switch(status) {
                case BaseLoaderCallback.SUCCESS:
                    javaCameraView.enableView();
                    break;
                default:
                    super.onManagerConnected(status);
                    break;
            }

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.body_detect);

        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        int savedRadioIndex = sharedPrefs.getInt("music", R.id.option1);
        flag_body = false;
        Log.i("Body ", "music" + savedRadioIndex);
        if (savedRadioIndex == R.id.option1)
            sound_type = R.raw.alarm;
        else if (savedRadioIndex == R.id.option2)
            sound_type = R.raw.detection;
        else
            sound_type = 0;

        Log.i("Main ", "mn" + sound_type);

        javaCameraView = (JavaCameraView)findViewById(R.id.java_camera_view);
        javaCameraView.setVisibility(View.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (javaCameraView != null)
            javaCameraView.disableView();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (javaCameraView != null)
            javaCameraView.disableView();
    }

    @Override
    protected void onResume(){
        super.onResume();
        if (OpenCVLoader.initDebug()){
            Log.i(TAG, "opencv loaded successfully");
            mLoaderCallback.onManagerConnected(LoaderCallbackInterface.SUCCESS);
        }
        else{
            Log.i(TAG,"opencv is not loaded ");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_3_1_0,this,mLoaderCallback);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRgba = new Mat(height, width, CvType.CV_8UC4);

    }

    @Override
    public void onCameraViewStopped() {
        mRgba.release();

    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRgba = inputFrame.rgba();
        int res;

        res = OpencvClass.bodyDetection(mRgba.getNativeObjAddr());
        Log.i("Body ", "fbd" + sound_type);
        if (res == 1) {
            if (sound_type != 0) {
                if (flag_body == false) {
                    mp = MediaPlayer.create(BodyDetection.this, sound_type);
                    if (mp != null)
                        makeSound();
                }


            }
        }

        return mRgba;
    }

    private void makeSound() {

        mp.start();
        Thread timer = new Thread(  ){
            public void run(){
                try{
                    flag_body = true;
                    sleep(5000);

                    // stopPlaying();
                }catch (InterruptedException e){
                    e.printStackTrace();
                    //stopPlaying();
                }
                finally
                {
                    flag_body = false;
                    stopPlaying();

                }

            }
        };

        timer.start();


    }



    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

}
