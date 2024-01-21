package com.github.zimoyin.utils

import androidx.compose.ui.window.Notification
import com.github.zimoyin.trayState



/**
 * 发送 None 信息通知
 */
fun sendNoneNotification(title:String = "通知",message:String){
    trayState.sendNotification(Notification(title,message,Notification.Type.None))
}

/**
 * 发送 Info 信息通知
 */
fun sendInfoNotification(title:String = "信息",message:String){
    trayState.sendNotification(Notification(title, message,Notification.Type.Info))
}

/**
 * 发送 Warning 信息通知
 */
fun sendWarningNotification(title:String = "警告",message:String){
    trayState.sendNotification(Notification(title, message,Notification.Type.Warning))
}

/**
 * 发送 Error 信息通知
 */
fun sendErrorNotification(title:String = "错误",message:String){
    trayState.sendNotification(Notification(title, message,Notification.Type.Error))
}


