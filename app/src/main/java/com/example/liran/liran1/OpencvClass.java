package com.example.liran.liran1;

/**
 * Created by liran on 15/10/2016.
 */

public class OpencvClass {
    public native static int faceDetection(long addrRgba);
    public native static int bodyDetection(long addrRgba);

}
