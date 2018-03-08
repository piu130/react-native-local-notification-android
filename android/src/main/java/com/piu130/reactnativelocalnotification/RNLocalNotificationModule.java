package com.piu130.reactnativelocalnotification;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.Random;

import static android.content.Context.ALARM_SERVICE;

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
                data.getInt("id", new Random().nextInt()),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.set(AlarmManager.RTC_WAKEUP, data.getLong("fireDate"), pendingIntent);
    }
}
