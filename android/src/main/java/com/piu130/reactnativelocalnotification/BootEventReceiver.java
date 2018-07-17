package com.piu130.reactnativelocalnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class BootEventReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationHelper notificationHelper = new NotificationHelper(context);
        notificationHelper.rescheduleAll();
    }
}
