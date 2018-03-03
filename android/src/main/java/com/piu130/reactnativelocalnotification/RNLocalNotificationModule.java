package com.piu130.reactnativelocalnotification;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Random;

public class RNLocalNotificationModule extends ReactContextBaseJavaModule {

    @Override
    public String getName() {
        return "RNLocalNotification";
    }

    @ReactMethod
    public void scheduleLocalNotification(ReadableMap details) {
        Intent intent = new Intent(this, NotificationPublisher.class);
        intent.putExtra("data", Arguments.toBundle(details));

        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this,
                details.getInt("id", new Random().nextInt()),
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        alarmManager.set(AlarmManager.RTC_WAKEUP, details.getLong("fireDate"), pendingIntent);
    }
}
