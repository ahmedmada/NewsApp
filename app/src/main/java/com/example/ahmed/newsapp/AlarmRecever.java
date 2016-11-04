package com.example.ahmed.newsapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by master on 04/11/2016.
 */
public class AlarmRecever  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        PendingIntent notification = PendingIntent.getActivity(context,0,new Intent(context,MainActivity.class),0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentTitle("News App")
                .setContentText("ما رايك في قرائه الاخبار في هذا الوقت من اليوم ,فقط شغل الانترنت وافتح التطبيق لتجد احدث الاخبار المتنوعه")
                .setSmallIcon(R.drawable.news_icon);

        builder.setContentIntent(notification);
        builder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        builder.setAutoCancel(true);

        NotificationManager manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        manager.notify(1,builder.build());
    }
}