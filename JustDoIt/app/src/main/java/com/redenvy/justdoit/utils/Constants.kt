package com.redenvy.justdoit.utils

object Constants {
    const val DARK_MODE = "Dark"
    const val LIGHT_MODE = "Light"
    const val FOLLOW_SYSTEM = "System"
    
    const val THEME_KEY = "theme"
    const val SYNC_KEY = "Sync"
    const val SYNC_INTERVAL_KEY = "sync_time"
    
    const val LOCAL_DB_NAME = "todoListItems"
    const val PARSE_FROM_STRING_PATTERN = "yyyy-MM-dd hh:mm aa"
    const val FORMAT_TO_STRING_PATTERN = "E dd MMM yyyy hh:mm aa"
    const val BUNDLE_NAME = "todoListItemPackage"

    const val NOTIFICATION_CHANNEL_NAME = "notificationChannelName"
    const val NOTIFICATION_CHANNEL_ID = "notificationChannelId"
    const val NOTIFICATION_CHANNEL_DESC = "notificationChannelDesc"
    
    const val INTENT_ACTION = "com.redenvy.justdoit.BROADCAST_INTENT"
    const val DATA_UPDATE_INTENT = "com.example.todolist.BROADCAST"

    const val TODO_ITEM_KEY = "todoListItem"

    const val FIRST_LAUNCH_KEY = "first_time_key"

    const val THIRTY_SEC_DELAY_TIME: Long =  30 * 1000
    const val FIVE_MIN_DELAY_TIME: Long = 5 * 60 * 1000
    const val ONE_HOUR_DELAY_TIME: Long = 1 * 60 * 60 * 1000

    const val FOREGROUND_SERVICE_ID = 17101403
    const val NEXT_NOTIFICATION_JOB_ID = 11432
    const val SYNC_JOB_ID = 1148
}