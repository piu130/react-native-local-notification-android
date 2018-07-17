package com.piu130.reactnativelocalnotification;

import com.facebook.react.bridge.Promise;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;

import java.util.Random;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationHelper {

    private final Context context;
    private final NotificationStorage notificationStorage;

    public NotificationHelper(Context context) {
        this.context = context;
        this.notificationStorage = NotificationStorage.fromContext(context);
    }

    protected void scheduleLocalNotification(Bundle data) {
        if (data.getDouble("id") == 0) data.putDouble("id", new Random().nextInt());
        int id = (int) data.getDouble("id");

        Intent intent = new Intent(context, NotificationPublisher.class);
        intent.putExtra("data", data);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.set(AlarmManager.RTC_WAKEUP, (long) data.getDouble("fireDate"), pendingIntent);

        notificationStorage.store(id, data);
    }

    protected void cancelLocalNotifications(Bundle data) {
        int id = (int) data.getDouble("id");

        Intent intent = new Intent(context, NotificationPublisher.class);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                id,
                intent,
                PendingIntent.FLAG_CANCEL_CURRENT
        );

        alarmManager.cancel(pendingIntent);

        notificationStorage.remove(id);
    }

    protected void createNotificationChannel(Bundle data) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;

        NotificationChannel channel = new NotificationChannel(data.getString("id"), data.getCharSequence("name"), (int) data.getDouble("importance"));
        if (data.containsKey("description")) channel.setDescription(data.getString("description"));
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }

    protected void checkPermissions(Promise promise) {
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(context);
        promise.resolve(managerCompat.areNotificationsEnabled());
    }
}