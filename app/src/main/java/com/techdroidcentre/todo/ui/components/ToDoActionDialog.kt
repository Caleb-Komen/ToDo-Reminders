package com.techdroidcentre.todo.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ToDoActionDialog(
    id: Long,
    title: String,
    closeDialog: () -> Unit,
    showEditTitleDialog: (Long, String) -> Unit,
    deleteToDo: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = closeDialog) {
        Column(
            modifier = modifier.width(200.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clickable {
                    showEditTitleDialog(id, title)
                    closeDialog()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = "Edit"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Edit")
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clickable {
                    deleteToDo(id)
                    closeDialog()
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete"
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Delete")
            }
        }
    }
}