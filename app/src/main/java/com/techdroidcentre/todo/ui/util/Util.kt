package com.techdroidcentre.todo.ui.util

import java.text.SimpleDateFormat
import java.util.*

object Util {
    fun toDateString(dateInMs: Long): String {
        val date = Date(dateInMs)
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        return sdf.format(date)
    }
}