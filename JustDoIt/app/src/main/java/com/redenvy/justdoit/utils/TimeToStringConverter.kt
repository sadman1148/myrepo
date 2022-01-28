package com.redenvy.justdoit.utils

import java.text.SimpleDateFormat
import java.util.*

object TimeToStringConverter{
    fun TimeToString(time: Long): String {
        val dateFormat = SimpleDateFormat(Constants.PARSE_FROM_STRING_PATTERN)
        return dateFormat.format(Date(time))
    }
}