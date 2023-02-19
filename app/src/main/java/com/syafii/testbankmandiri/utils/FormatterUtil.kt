/*
 * Created by Muhamad Syafii
 * Sunday, 19/2/2023
 * Test Technical Assessment
 * Copyright (c) 2023.
 * All Rights Reserved
 */

package com.syafii.testbankmandiri.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FormatterUtil {
    fun stringToDate(
        date: String,
        formatFrom: String = "yyyy-MM-dd",
    ): Date {
        val formatter = SimpleDateFormat(formatFrom, Locale.getDefault())
        return formatter.parse(date)
    }

    fun dateToString(date: Date, formatTo: String = "yyyy-MM-dd HH:mm"): String {
        val simpleDateFormat = SimpleDateFormat(formatTo)
        return simpleDateFormat.format(date)
    }

}