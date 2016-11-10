package com.example.liran.liran1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.graphics.Color;
import android.view.MotionEvent;


/**
 * Created by liran on 21/10/2016.
 */

public class StartMenu extends AppCompatActivity {

    Button btn_start, btn_sounds_def, btn_detection;
    private DetectionTypeEnum detect_type;
    String sound_path = "com.example.liran.liran1.MainSound";
    String detection_path = "com.example.liran.liran1.DetectionType";

    @Override
    protected void onCreate(Bundle liran) {
        super.onCreate(liran);
        setContentView(R.layout.start_menu);
        initButtons();

       // Log.i("who ", "mydying" + R.raw.mdb);
       // Log.i("who ", "orphend" + R.raw.orph);
        //onClick the btn start, opens the detection window
        onClickOpenDetectionOption();

        //on status of the start button changes, the color of the starting button is being changed
        onStartButtonMotionChangeColor();

        //onClick the btn sounds defintion, opens the definitions windows for the possible sounds
        onSounDefBtnMotionColor();

        //onClick the btn definition of sounds, opens the sound definitions window
        onSoundDefinitionClick();

        onPressShowDetectionColor();

        onDetectionDefinitionClick();

    }

    private void onSoundDefinitionClick() {
        btn_sounds_def.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // if (v.getId() == R.id.button_start)
                //  setFocus(focus_start);
                Intent openSounds = new Intent(sound_path);
                startActivity(openSounds);
            }
        });
    }

    private void onSounDefBtnMotionColor() {
        btn_sounds_def.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_sounds_def.setBackgroundColor(Color.GREEN);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_sounds_def.setBackgroundColor(Color.BLUE);
                }
                return false;
            }
        });
    }

    private void onStartButtonMotionChangeColor() {
        btn_start.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_start.setBackgroundColor(Color.RED);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_start.setBackgroundColor(Color.BLUE);
                }
                return false;
            }
        });
    }

    private void onClickOpenDetectionOption() {
        btn_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // if (v.getId() == R.id.button_start)
                  //  setFocus(focus_start);
                    Intent openStartingPoint = new Intent(detection_path);
                    startActivity(openStartingPoint);
            }
        });
    }

    private void initButtons() {
        btn_start = (Button) findViewById(R.id.button_start);
        btn_start.setBackgroundColor(Color.RED);
        btn_sounds_def = (Button) findViewById(R.id.button_sounds_def);
        btn_sounds_def.setBackgroundColor(Color.GREEN);
        btn_detection = (Button)findViewById(R.id.btnDisplay);
        btn_detection.setBackgroundColor(Color.MAGENTA);

    }

    private void onPressShowDetectionColor(){
        btn_detection.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    btn_detection.setBackgroundColor(Color.MAGENTA);
                } else if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    btn_detection.setBackgroundColor(Color.BLUE);
                }
                return false;
            }
        });

    }

    private void onDetectionDefinitionClick() {
        btn_detection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefs = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                int savedRadioIndex = sharedPrefs.getInt("wow", R.id.option1);

                try {
                    Class ourClass = null;
                    switch (savedRadioIndex) {
                        case R.id.option1:
                            ourClass = Class.forName("com.example.liran.liran1.FaceDetection");
                            break;
                        case R.id.option2:
                            ourClass = Class.forName("com.example.liran.liran1.BodyDetection");
                            break;
                        case R.id.option3:
                            break;
                        default:
                            break;
                    }
                    if (ourClass != null) {
                        Intent ourIntenet = new Intent(StartMenu.this, ourClass);
                        startActivity(ourIntenet);
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            }
        });
    }


}

