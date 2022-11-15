package com.techdroidcentre.todo.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import com.techdroidcentre.todo.R

const val CHANNEL_ID = "reminder_channel_id"
const val NOTIFICATION_ID = 1

fun createNotification(context: Context, content: String) {
    val notificationManager: NotificationManager =
        context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    createNotificationChannel(context, notificationManager)
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("You task is due.")
        .setContentText(content)
        .setSmallIcon(R.drawable.ic_baseline_event_available_24)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    notificationManager.notify(NOTIFICATION_ID, builder.build())
}

private fun createNotificationChannel(context: Context, notificationManager: NotificationManager) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannel(CHANNEL_ID, "Reminder", NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "Notify task is due."

        notificationManager.createNotificationChannel(channel)
    }
}