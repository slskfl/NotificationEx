package com.example.notificationex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnNoti;
    String channelID="";
    NotificationManager notificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnNoti=findViewById(R.id.btnNoti);

        Bitmap largeIcon=BitmapFactory.decodeResource(getResources(), R.drawable.bell);
        PendingIntent pIntent=PendingIntent.getActivity(MainActivity.this, 0,
                new Intent(getApplicationContext(), MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        btnNoti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationCompat.Builder builder;
                if(Build.VERSION.SDK_INT>=26) {
                    //channelID>> 채널의 알림 순위를 지정
                    NotificationChannel channel=new NotificationChannel(channelID,"채널이름",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    ((NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE))
                            .createNotificationChannel(channel);
                    builder = new NotificationCompat.Builder(MainActivity.this, channelID);
                } else{
                    builder = new NotificationCompat.Builder(MainActivity.this);
                }
                //메소드 체인 이용
                builder.setSmallIcon(R.drawable.bell)
                        .setContentTitle("노티연습")
                        .setContentText("지금은 노티 연습 중")
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setLargeIcon(largeIcon)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true)
                        .setContentIntent(pIntent);
                notificationManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
                notificationManager.notify(0, builder.build());
            }
        });
    }
}