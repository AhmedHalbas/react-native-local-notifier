package com.ahmedessam.helpers
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Notification
import android.content.Context
import android.os.Build
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import java.lang.System;
import android.app.PendingIntent
import android.app.AlarmManager
import android.icu.util.Calendar
import android.content.Intent
import com.facebook.react.bridge.ReadableMap

object NotificationCenter {

    private fun setupNotificationChannel(context: Context, notificationManager: NotificationManager): String {
        val notificationChannelId = context.getPackageName()
        val notificationChannelName = context.getApplicationInfo().loadLabel(context.getPackageManager()).toString() + " Channel"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                notificationChannelId,
                notificationChannelName,
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }
        return notificationChannelId
    }
    
    private fun createNotification(context: Context, notifierObject: ReadableMap): Notification {

        val title = notifierObject.getString("title")
        
        val body = notifierObject.getString("body")

        val notificationManager: NotificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notificationChannelId = setupNotificationChannel(context, notificationManager)

        val pm = context.getPackageManager();
        val intent = pm.getLaunchIntentForPackage(context.getPackageName())

        val pendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notification = NotificationCompat.Builder(context, notificationChannelId)
            .setSmallIcon(context.getApplicationInfo().icon)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    context.resources,
                    context.getApplicationInfo().icon
                )
            )
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)

        return notification.build()
        
    }


    fun send(context: Context, notifierObject: ReadableMap) {

        val notification = createNotification(context, notifierObject)

        val intent = Intent(context, NotificationReceiver::class.java)
        intent.putExtra("Notification", notification)
        context.sendBroadcast(intent);
        
    }


    fun schedule(context: Context, notifierObject: ReadableMap) {

        val notification = createNotification(context, notifierObject)

        val intent = Intent(context, NotificationReceiver::class.java)

        intent.putExtra("Notification", notification)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val currentTime = System.currentTimeMillis();
        val triggerReminder = currentTime + notifierObject.getInt("delay") * 1000;
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerReminder,
            pendingIntent
        )
        
    }


    
}
