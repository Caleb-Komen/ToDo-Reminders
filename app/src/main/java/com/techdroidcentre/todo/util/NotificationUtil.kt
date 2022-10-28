package com.techdroidcentre.todo.util

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat.getSystemService
import com.techdroidcentre.todo.R

const val CHANNEL_ID = "reminder_channel_id"
const val NOTIFICATION_ID = 1

fun createNotification(context: Context, content: String) {
    createNotificationChannel(context)
    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
        .setContentTitle("You task is due.")
        .setContentText(content)
        .setSmallIcon(R.drawable.ic_baseline_event_available_24)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

    NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, builder.build())
}

private fun createNotificationChannel(context: Context) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val channel =
            NotificationChannelCompat.Builder(CHANNEL_ID, NotificationManager.IMPORTANCE_DEFAULT)
                .setDescription("Notify task is due.")
                .build()

        val notificationManager: NotificationManagerCompat =
            getSystemService(context, NotificationCompat::class.java) as NotificationManagerCompat
        notificationManager.createNotificationChannel(channel)
    }
}