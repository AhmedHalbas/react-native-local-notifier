package com.ahmedessam.reactnativelocalnotifier

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.ahmedessam.helpers.NotificationCenter

class RNLocalNotifierModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

   private val context: ReactApplicationContext = reactContext


    override fun getName() = "RNLocalNotifierModule"

   @ReactMethod
    fun sendNotification(notifierObject: ReadableMap) {
        NotificationCenter.send(context,notifierObject)
    }

    @ReactMethod
    fun scheduleNotification(notifierObject: ReadableMap) {
        NotificationCenter.schedule(context,notifierObject)
    }

}

