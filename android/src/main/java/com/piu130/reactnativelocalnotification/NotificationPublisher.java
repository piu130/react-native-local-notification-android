package com.piu130.reactnativelocalnotification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationPublisher extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getBundleExtra("data");

        NotificationCompat.Builder builder = new NotificationCompat.Builder(
                context,
                data.getString("channelId", "default")
        );

        if (data.containsKey("alertBody")) builder.setContentText(data.getString("alertBody"));
        if (data.containsKey("alertTitle")) builder.setContentTitle(data.getString("alertTitle"));
        if (data.containsKey("autoCancel")) builder.setAutoCancel(data.getBoolean("autoCancel"));
        if (data.containsKey("badgeIconType")) builder.setBadgeIconType(data.getInt("badgeIconType"));
        if (data.containsKey("category")) builder.setCategory(data.getString("category"));
        if (data.containsKey("color")) builder.setColor(Color.parseColor(data.getString("color")));
        if (data.containsKey("colorized")) builder.setColorized(data.getBoolean("colorized"));
        if (data.containsKey("contentInfo")) builder.setContentInfo(data.getString("contentInfo"));
        if (data.containsKey("group")) builder.setGroup(data.getString("group"));
        if (data.containsKey("largeIcon")) builder.setLargeIcon(getLargeIcon(context, data));
        if (data.containsKey("localOnly")) builder.setLocalOnly(data.getBoolean("localOnly"));
        if (data.containsKey("number")) builder.setNumber(data.getInt("number"));
        if (data.containsKey("priority")) builder.setPriority(data.getInt("priority"));
        if (data.containsKey("sortKey")) builder.setSortKey(data.getString("sortKey"));
        if (data.containsKey("subText")) builder.setSubText(data.getString("subText"));
        if (data.containsKey("ticker")) builder.setTicker(data.getString("ticker"));
        if (data.containsKey("timeoutAfter")) builder.setTimeoutAfter(data.getInt("timeoutAfter"));
        if (data.containsKey("visibility")) builder.setVisibility(data.getInt("visibility"));

        builder
                .setSmallIcon(getSmallIconId(context, data));
//                .setLights()
//                .setProgress()

        NotificationManagerCompat manager = NotificationManagerCompat.from(context);
        manager.notify(5, builder.build());
    }

    private int getSmallIconId(Context context, Bundle data) {
        int id = getIconId(context, data.getString("smallIcon", "ic_notification"));

        return id == 0
                ? getIconId(context, "ic_launcher")
                : id;
    }

    private Bitmap getLargeIcon(Context context, Bundle data) {
        int id = getIconId(context, data.getString("largeIcon"));

        return BitmapFactory.decodeResource(
                context.getResources(),
                id == 0
                        ? getIconId(context, "ic_launcher")
                        : id
        );
    }

    private int getIconId(Context context, String icon) {
        return context
                .getResources()
                .getIdentifier(icon, "mipmap", context.getPackageName());
    }
}
