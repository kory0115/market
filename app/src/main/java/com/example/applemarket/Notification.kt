package com.example.applemarket

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat

class Notification(private val context: Context) {
    private var notificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }
    @SuppressLint("NewApi")
    private fun createNotificationChannel() {
        val notificationChannel =
            NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH)
                .apply {
                    enableVibration(true)
                    description = "description"
                }
        notificationManager.createNotificationChannel(notificationChannel)
    }
    fun deliverNotification() {
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.baseline_adjust_24)
            .setContentTitle("안내")
            .setContentText("안내문")
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setDefaults(NotificationCompat.DEFAULT_ALL)

        notificationManager.notify(NOTIFICATION_ID, builder.build())
    }

    companion object {
        const val CHANNEL_ID = "channel_id"
        const val CHANNEL_NAME = "channel_name"
        const val NOTIFICATION_ID = 0
    }
}