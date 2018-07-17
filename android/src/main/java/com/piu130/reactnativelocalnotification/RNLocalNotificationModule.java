package com.piu130.reactnativelocalnotification;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

import android.os.Bundle;

public class RNLocalNotificationModule extends ReactContextBaseJavaModule {

    private final NotificationHelper notificationHelper;

    public RNLocalNotificationModule(ReactApplicationContext reactContext) {
        super(reactContext);
        notificationHelper = new NotificationHelper(reactContext);
    }

    @Override
    public String getName() {
        return "RNLocalNotification";
    }

    @ReactMethod
    public void scheduleLocalNotification(ReadableMap details) {
        Bundle data = Arguments.toBundle(details);
        notificationHelper.scheduleLocalNotification(data);
    }

    @ReactMethod
    public void cancelLocalNotifications(ReadableMap details) {
        notificationHelper.cancelLocalNotifications(Arguments.toBundle(details));
    }

    @ReactMethod
    public void createNotificationChannel(ReadableMap details) {
        notificationHelper.createNotificationChannel(Arguments.toBundle(details));
    }

    @ReactMethod
    public void checkPermissions(Promise promise) {
        notificationHelper.checkPermissions(promise);
    }
}
