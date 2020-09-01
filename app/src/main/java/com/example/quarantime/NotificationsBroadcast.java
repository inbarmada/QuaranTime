package com.example.quarantime;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationsBroadcast extends BroadcastReceiver {

    public static String notificationTitle;
    public static String notificationMessage;
    public static int notificationID = 1;

    @Override
    public void onReceive(Context context, Intent intent) {
        createNotificationChannel(context);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "remindChannel")
                .setSmallIcon(R.drawable.ic_home_black_24dp)
                .setContentTitle(notificationTitle)
                .setContentText(notificationMessage)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        NotificationManagerCompat nm = NotificationManagerCompat.from(context);
        nm.notify(notificationID, builder.build());
    }


    private void createNotificationChannel(Context c) {
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


    public static void sendNotification(Context c, long timeInMiliseconds, String title, String message, int notID){
        NotificationsBroadcast.notificationMessage = message;
        NotificationsBroadcast.notificationTitle = title;
        NotificationsBroadcast.notificationID = notID;

        Intent intent = new Intent(c,NotificationsBroadcast.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(c, 0, intent, 0);

        AlarmManager alarmManager = (AlarmManager)c.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, timeInMiliseconds, pendingIntent);
    }



}
