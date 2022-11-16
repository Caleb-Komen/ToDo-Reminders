package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@Composable
fun ToDoListPopUp(
    id: Long,
    title: String,
    closePopUp: () -> Unit,
    showEditTitleDialog: (Long, String) -> Unit,
    deleteToDo: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Popup(
        properties = PopupProperties(focusable = true),
        onDismissRequest = closePopUp
    ) {
        Row(modifier = modifier.background(color = Color.Cyan)) {
            IconButton(
                onClick = {
                    showEditTitleDialog(id, title)
                    closePopUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
            }
            IconButton(
                onClick = {
                    deleteToDo(id)
                    closePopUp()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
            }
        }
    }
}
