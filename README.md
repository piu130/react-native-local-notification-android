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
