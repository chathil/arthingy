package com.example.artic.util

import java.text.SimpleDateFormat
import java.util.Locale

fun String.iso8601ToLong(): Long {
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.getDefault())
    val date = sdf.parse(this)
    return date?.time ?: 0
}
