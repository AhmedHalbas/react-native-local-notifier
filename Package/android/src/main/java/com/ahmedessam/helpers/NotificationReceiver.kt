package com.ahmedessam.helpers
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import java.lang.System
import android.app.PendingIntent
import androidx.core.app.NotificationCompat
import android.app.Notification
import android.graphics.BitmapFactory

class NotificationReceiver : BroadcastReceiver()
{

    
    override fun onReceive(context: Context, intent: Intent)
    {
        val notification = intent.getParcelableExtra<Notification>("Notification")
        
        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        
        manager.notify((System.currentTimeMillis()).toInt(), notification)
    }

}