package com.example.quarantime;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import static android.provider.Settings.System.getString;

public class Notifications {
    Context c;

    public Notifications(Context con){
        this.c = con;
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int imp = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("I.DONT.KNOW.WHAT.I.AM.DOING", "TestChannel", imp);
            channel.setDescription("I don't know what I'm doing please help uwu");
            channel.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);
            channel.enableLights(true);
            channel.setLightColor(Color.RED);
            channel.enableVibration(true);

            NotificationManager nm = c.getSystemService(NotificationManager.class);
            nm.createNotificationChannel(channel);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendNotification(String title, String text){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(c, "I.DONT.KNOW.WHAT.I.AM.DOING")
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setContentTitle(title)
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager nm = c.getSystemService(NotificationManager.class);
        nm.notify(001, builder.build());
    }






}
