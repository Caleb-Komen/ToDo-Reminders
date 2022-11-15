package com.techdroidcentre.todo.ui.components

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun DateAndTimePicker(
    date: Long,
    onDateAndTimeSet: (Long) -> Unit,
    context: Context = LocalContext.current
) {
    val startYear: Int
    val startMonth: Int
    val startDay: Int
    val startHour: Int
    val startMinute: Int
    if (date > 0) {
        val currentDateTime = Calendar.getInstance()
        currentDateTime.timeInMillis = date
        startYear = currentDateTime.get(Calendar.YEAR)
        startMonth = currentDateTime.get(Calendar.MONTH)
        startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        startMinute = currentDateTime.get(Calendar.MINUTE)
    } else {
        val currentDateTime = Calendar.getInstance()
        startYear = currentDateTime.get(Calendar.YEAR)
        startMonth = currentDateTime.get(Calendar.MONTH)
        startDay = currentDateTime.get(Calendar.DAY_OF_MONTH)
        startHour = currentDateTime.get(Calendar.HOUR_OF_DAY)
        startMinute = currentDateTime.get(Calendar.MINUTE)
    }

    DatePickerDialog(ContextThemeWrapper(context, android.R.style.Widget_Material_DatePicker), { _, year, month, day ->
        TimePickerDialog(ContextThemeWrapper(context, android.R.style.Widget_Material_TimePicker), { _, hour, minute ->
            val dateTime = Calendar.getInstance()
            dateTime.set(year, month, day, hour, minute)
            onDateAndTimeSet(dateTime.timeInMillis)
        }, startHour, startMinute, true).show()
    }, startYear, startMonth, startDay).show()
}

