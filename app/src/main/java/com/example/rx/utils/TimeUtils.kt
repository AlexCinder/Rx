package com.example.rx.utils

import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

fun convertTimeStampToTime(timeStamp : Long): String{
    val stamp = Timestamp(timeStamp*1000)
    val date = Date(stamp.time)
    val pattern = "HH:mm:ss"
    val simpleDateFormat = SimpleDateFormat(pattern,Locale.getDefault())
    simpleDateFormat.timeZone = TimeZone.getDefault()
    return simpleDateFormat.format(date)

}