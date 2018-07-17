# react-native-local-notification-android

## Setup

Install the library

```bash
npm install git://github.com/piu130/react-native-local-notification-android.git
```

Link the library

```bash
react-native link react-native-local-notification-android
```

Edit the `AndroidManifest.xml`

```xml
<uses-permission android:name="android.permission.RECEIVE_BOOT_COMPLETED" />
...
<application ...>
    <receiver android:name="com.piu130.reactnativelocalnotification.NotificationPublisher" />
    <receiver android:name="com.piu130.reactnativelocalnotification.BootEventReceiver">
        <intent-filter>
            <action android:name="android.intent.action.BOOT_COMPLETED" />
            <action android:name="android.intent.action.QUICKBOOT_POWERON" />
        </intent-filter>
    </receiver>
    ...
```

## Usage

Create notification channel

```js
import {createNotificationChannel} from 'react-native-local-notification-android'

createNotificationChannel({
    id: 'example',
    name: 'exampleChannel',
    importance: 3,
    descrition: 'This is an example channel.'
})
```

Schedule notification

```js
import {scheduleLocalNotification} from 'react-native-local-notification-android'

scheduleLocalNotification({
    id: 1234,
    alertTitle: 'Notification title',
    alertBody: 'Notification body',
    fireDate: Date.now() + 5000,
    channelId: 'example'
})
```

Cancel notification

```js
import {cancelLocalNotifications} from 'react-native-local-notification-android'

cancelLocalNotifications({
    id: 1234
})
```

Check permission

```js
import {checkPermissions} from 'react-native-local-notification-android'

checkPermissions()
    .then(enabled => console.log(
        enabled ? 'You have the permission' : 'You don\'t have the permission'
    ))
```

## Notification

Param | Type | Description
--- | --- | ---
id | number | Notification id.
fireDate | number | Fire date in millis (eg. `date.getTime()`).
channelId | string | The id of the created channel.
alertBody | string | Notification content.
alertTitle | string | Notification title.
autoCancel | boolean | Auto cancel on notification click.
badgeIconType | number | The type of the badge icon (if supported by launcher).
category | string | Notification category.
color | string | Notification color (`colorized` must be true).
colorized | boolean | Should the notification be colored with `color`.
contentInfo | string | Large text at the right-hand side of the notification.
group | string | Notification group.
largeIcon | string | Notifications large icon.
number | number | Large number at the right-hand side of the notification.
priority | number | Notification priority.
smallIcon | string | Notification small icon.
sortKey | string | Sort key to sort the notifications order.
subText | string | Third line of notification.
ticker | string | Ticker text is sent to accessibility services.
timeoutAfter | number | Set the cancel timeout.
visibility | number | The visibility (public, private or secret).
