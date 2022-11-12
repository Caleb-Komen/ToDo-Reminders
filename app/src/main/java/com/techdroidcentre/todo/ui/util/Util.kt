package com.techdroidcentre.todo.ui.util

import androidx.compose.ui.graphics.toArgb
import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun toDateString(dateInMs: Long): String {
        val date = Date(dateInMs)
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.UK)
        return sdf.format(date)
    }
}

const val defaultId = 0L
val defaultColour = colours[0].toArgb()