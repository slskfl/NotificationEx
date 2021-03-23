package com.example.vibrationex;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnVib1, btnVib2, btnVib3, btnVib4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnVib1=findViewById(R.id.btnVib1);
        btnVib2=findViewById(R.id.btnVib2);
        btnVib3=findViewById(R.id.btnVib3);
        btnVib4=findViewById(R.id.btnVib4);

        Vibrator vib=(Vibrator)getSystemService(VIBRATOR_SERVICE);

        btnVib1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //진동이 울림
                vib.vibrate(1000); // 1초동안 진동이 울림
            }
        });
        btnVib2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //대기시간, 초동안 진동(대기, 진동 반복), '0'일 경우 무한 반복
                vib.vibrate(new long[]{500, 1000, 700, 500, 1000, 600}, -1);
                //vic.cancle(); 멈추기
            }
        });
        btnVib3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //휴대폰 기본 사운드
                Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                Ringtone ringtone=RingtoneManager.getRingtone(getApplicationContext(), uri);
                ringtone.play();
            }
        });

        btnVib4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mp=MediaPlayer.create(MainActivity.this, R.raw.cottoncandy);
                mp.start();
            }
        });
    }
}