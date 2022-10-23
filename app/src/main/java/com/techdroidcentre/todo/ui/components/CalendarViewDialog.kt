package com.techdroidcentre.todo.ui.components

import android.widget.CalendarView
import androidx.appcompat.view.ContextThemeWrapper
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import java.util.*

@Composable
fun CalendarViewDialog(
    date: Long,
    onDateChange: (Long) -> Unit,
    closeDialog: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = {
            Text(text = "Choose date")
        },
        text = {
            CalendarView(date = date, onDateChange = onDateChange)
        },
        onDismissRequest = closeDialog,
        confirmButton = {
            Button(
                onClick = closeDialog
            ) {
                Text(text = "Done")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onDateChange(0L)
                    closeDialog()
                }
            ) {
                Text(text = "None")
            }
        },
        modifier = modifier
    )
}

@Composable
fun CalendarView(
    date: Long,
    onDateChange: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    AndroidView(
        modifier = modifier,
        factory = { context ->
            CalendarView(ContextThemeWrapper(context, android.R.style.Widget_Material_DatePicker))
        },
        update = { calendarView ->
            calendarView.apply {
                if (date > 0L) setDate(date) else setDate(Calendar.getInstance().timeInMillis)

                setOnDateChangeListener { _, year, month, dayOfMonth ->
                    val calendar = GregorianCalendar(year, month, dayOfMonth)
                    onDateChange(calendar.timeInMillis)
                }
            }
        }
    )
}

@Preview
@Composable
fun CalendarDialogPreview() {
    CalendarViewDialog(0L, {}, {})
}
