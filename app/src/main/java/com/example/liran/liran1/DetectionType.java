package com.example.liran.liran1;

/**
 * Created by liran on 22/10/2016.
 */

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.FrameLayout;
import android.content.SharedPreferences;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.ListAdapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.view.View.OnClickListener;
import android.content.Intent;



public class  DetectionType extends AppCompatActivity {
    String classes[] = {"BodyDetection", "FaceDetection","example2", "example3","example4","example5","example6"};
    private DetectionTypeEnum detect_type;
    RadioGroup _rgGroup;
    RadioButton _option1;
    RadioButton _option2;
    Button _btnDisplay;
    Editor editor;
    private static final String TAG = "DetectionType";
    boolean flag;


    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.detection_type);
        initParams();

        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();


        flag = false;
        int savedRadioIndex = sharedPrefs.getInt("wow", R.id.option1);
        _rgGroup.check(savedRadioIndex);

        onChangedListen();
    }

    private void initParams() {
        _rgGroup = (RadioGroup) findViewById(R.id.rgGroup);
        _btnDisplay = (Button) findViewById(R.id.btnDisplay);
        _option1 = (RadioButton) findViewById(R.id.option1);
        _option2 = (RadioButton) findViewById(R.id.option2);

    }

    public void set_detect_type(DetectionTypeEnum detect_type) {
        this.detect_type = detect_type;
    }

    public  DetectionTypeEnum get_detect_type() {
        if (detect_type != null)
            return detect_type;
        return null;
    }


    private void onChangedListen() {

        _rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup buttonView, int position) {
             //   Toast.makeText(getApplicationContext(), "t", Toast.LENGTH_SHORT).show();
                switch (position){
                    case R.id.option1:
                      //  Toast.makeText(getApplicationContext(), "op det body 1", Toast.LENGTH_SHORT).show();
                        set_detect_type(DetectionTypeEnum.body);

                        //open Main.. in the next two as well
                        break;
                    case R.id.option2:
                      //  Toast.makeText(getApplicationContext(), "op det face 2", Toast.LENGTH_SHORT).show();
                        set_detect_type(DetectionTypeEnum.face);
                        break;
                    case R.id.option3:
                       // Toast.makeText(getApplicationContext(), "op det body and face 3", Toast.LENGTH_SHORT).show();
                        set_detect_type(DetectionTypeEnum.faceAndBody);
                        break;
                    default:
                        //Toast.makeText(getApplicationContext(), "ohhhhh", Toast.LENGTH_SHORT).show();
                        break;

                }

                editor.putInt("wow", position);
                editor.commit();


            }
        });

    }
/*
    public void showResult(View v) {
        if (get_detect_type() != null) {
            try {
                Class ourClass = null;
                switch (get_detect_type()) {
                    case body:
                        ourClass = Class.forName("com.example.liran.liran1.BodyDetection");
                        break;
                    case face:
                        ourClass = Class.forName("com.example.liran.liran1.FaceDetection");
                        break;
                    default:
                        break;
                }
                if (ourClass != null) {
                    Intent ourIntenet = new Intent(DetectionType.this, ourClass);
                    startActivity(ourIntenet);
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
*/
}


