package com.piu130.reactnativelocalnotification;

import android.app.Notification;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getBundleExtra("data");

        Notification notification = NotificationBuilder.fromBundle(context, data);

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify((int) data.getDouble("id"), notification);
    }
}
