package com.ccg.golddigger.utils

import java.text.SimpleDateFormat
import java.util.Calendar

/**
 * @author : C4_雍和
 * 描述 :
 * 主要功能 :
 * 维护人员 : C4_雍和
 * date : 2024/3/20 10:50
 */
object TimeUtil {

    fun getExpirationTime(): Long {
        val dateString = "2025-03-08 24:00:00"
        //        val dateString = "2024-03-08 24:00:00"
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        val date = format.parse(dateString)
        val calendar = Calendar.getInstance()
        calendar.setTime(date)
        return calendar.getTimeInMillis()
    }
}