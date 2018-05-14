import { NativeModules } from 'react-native'

const {RNLocalNotification} = NativeModules
const LocalNotification = {}

/**
 * Cancels a local notification.
 * @param {number} details.id - Notification id.
 */
LocalNotification.cancelLocalNotifications = details => RNLocalNotification.cancelLocalNotifications(details)

/**
 * Checks if notification permission is enabled.
 * @return {Promise<boolean>} Returns true if permission is set. Otherwise false.
 */
LocalNotification.checkPermissions = () => RNLocalNotification.checkPermissions

/**
 * Creates a notification channel.
 * @param {object} details - Channel details.
 * @param {string} details.id - Channel id.
 * @param {string} details.name - Channel name.
 * @param {number} details.importance - Channel importance.
 * @param {string} details.description - Channel description.
 */
LocalNotification.createNotificationChannel = details => RNLocalNotification.createNotificationChannel(details)

/**
 * Schedules a local notification.
 * @param {object} details - Notification details.
 * @param {number} details.id - Notification id.
 * @param {number} details.fireDate - Notification fireDate.
 * @param {string} details.channelId - Notification channelId.
 * @param {string} details.alertBody - Notification alert body.
 * @param {string} details.alertTitle - Notification alert title.
 * @param {boolean} details.autoCancel - Notification auto cancel.
 * @param {number} details.badgeIconType - Notification badge icon type.
 * @param {string} details.category - Notification category.
 * @param {string} details.color - Notification color.
 * @param {boolean} details.colorized - Notification colorized.
 * @param {string} details.contentInfo - Notification content info.
 * @param {string} details.group - Notification group.
 * @param {string} details.largeIcon - Notification large icon.
 * @param {number} details.number - Notification number.
 * @param {number} details.priority - Notification priority.
 * @param {string} details.smallIcon - Notification small icon.
 * @param {string} details.sortKey - Notification sort key.
 * @param {string} details.subText - Notification sub text.
 * @param {string} details.ticker - Notification ticker.
 * @param {number} details.timeoutAfter - Notification timeout after.
 * @param {number} details.visibility - Notification visibility.
 */
LocalNotification.scheduleLocalNotification = details => RNLocalNotification.scheduleLocalNotification(details)

module.exports = LocalNotification
