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



public class MainSound extends AppCompatActivity {
    //ArrayList<Sound> sounds = new ArrayList<Sound>();
    //int savedSound;
    private RadioGroup _rgGroup;
    private RadioButton _option1;
    private RadioButton _option2;
    private Button _btnDisplay;
    private Editor editor;
    private MediaPlayer mp;
    private static final String TAG = "MainSound";
    private boolean flag;
    private int savedSound;


    /** Called when the activity is first created. */

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.sounds_definitions);
        initParams();

        SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", Context.MODE_PRIVATE);
        editor = sharedPrefs.edit();


        flag = false;
        int savedRadioIndex = sharedPrefs.getInt("music", R.id.option1);
        savedSound = savedRadioIndex;
        _rgGroup.check(savedRadioIndex);


        onChangedListen();


    }


    private void initParams() {
        _rgGroup = (RadioGroup) findViewById(R.id.rgGroup);
        _btnDisplay = (Button) findViewById(R.id.btnDisplay);
        _option1 = (RadioButton) findViewById(R.id.option1);
        _option2 = (RadioButton) findViewById(R.id.option2);
    }

    private void onChangedListen() {

        _rgGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup buttonView, int position) {

                Toast.makeText(getApplicationContext(), "t", Toast.LENGTH_SHORT).show();
                if (position == R.id.option1) {
                    Toast.makeText(getApplicationContext(), "op 1", Toast.LENGTH_SHORT).show();
                    savedSound = R.raw.alarm;



                } else if (position == R.id.option2) {
                    Toast.makeText(getApplicationContext(), "op 2", Toast.LENGTH_SHORT).show();
                    savedSound = R.raw.detection;


                } else {
                    Toast.makeText(getApplicationContext(), "ohhhhh", Toast.LENGTH_SHORT).show();
                    savedSound = 0;
                }

                editor.putInt("music", position);
                editor.commit();


            }
        });
    }




    public void showResult(View v) {
        if (flag == false)
        {
            Toast.makeText(getApplicationContext(), "yeah" , Toast.LENGTH_SHORT).show();
            if (savedSound  == R.raw.detection || savedSound  == R.raw.alarm)
                mp = MediaPlayer.create(MainSound.this, savedSound );
            if (mp != null)
                makeSound();
        }
    }

    private void makeSound() {

        mp.start();
        Thread timer = new Thread(  ){
            public void run(){
                try{
                    flag = true;
                    sleep(5000);

                   // stopPlaying();
                }catch (InterruptedException e){
                    e.printStackTrace();
                    //stopPlaying();
                }
                finally
                {
                    flag = false;
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
