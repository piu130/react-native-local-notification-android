package com.piu130.reactnativelocalnotification;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

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

public class RNLocalNotificationModule extends ReactContextBaseJavaModule {

    public RNLocalNotificationModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "RNLocalNotification";
    }

    @ReactMethod
    public void scheduleLocalNotification(ReadableMap details) {
        Bundle data = Arguments.toBundle(details);
        Context context = getReactApplicationContext();

        Intent intent = new Intent(context, NotificationPublisher.class);
        intent.putExtra("data", data);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                data.containsKey("id") ? (int) data.getDouble("id") : new Random().nextInt(),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.set(AlarmManager.RTC_WAKEUP, (long) data.getDouble("fireDate"), pendingIntent);
    }

    @ReactMethod
    public void cancelLocalNotifications(ReadableMap details) {
        Bundle data = Arguments.toBundle(details);
        Context context = getReactApplicationContext();

        Intent intent = new Intent(context, NotificationPublisher.class);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                context,
                (int) data.getDouble("id"),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.cancel(pendingIntent);
    }

    @ReactMethod
    public void createNotificationChannel(ReadableMap details) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return;

        Bundle data = Arguments.toBundle(details);
        NotificationChannel channel = new NotificationChannel(data.getString("id"), data.getCharSequence("name"), (int) data.getDouble("importance"));
        if (data.containsKey("description")) channel.setDescription(data.getString("description"));
        NotificationManager manager = (NotificationManager) getReactApplicationContext().getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(channel);
    }

    @ReactMethod
    public void checkPermissions(Promise promise) {
        ReactContext reactContext = getReactApplicationContext();
        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(reactContext);
        promise.resolve(managerCompat.areNotificationsEnabled());
    }
}
