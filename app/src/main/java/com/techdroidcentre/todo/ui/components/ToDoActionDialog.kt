package com.techdroidcentre.todo.ui.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun ToDoActionDialog(
    id: Long,
    title: String,
    closeDialog: () -> Unit,
    showEditTitleDialog: (Long, String) -> Unit,
    showConfirmDeleteDialog: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = closeDialog) {
        Column(
            modifier = modifier
                .width(200.dp)
                .background(MaterialTheme.colorScheme.surface)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
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
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .clickable {
                        showConfirmDeleteDialog(id)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditToDoTitleDialog(
    id: Long,
    title: String,
    onTitleChange: (String) -> Unit,
    dismissDialog: () -> Unit,
    updateToDoList: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    AlertDialog(
        title = {
            Text(text = "Edit Title")
        },
        text = {
            TextField(
                value = title,
                onValueChange = onTitleChange
            )
        },
        onDismissRequest = { dismissDialog() },
        confirmButton = {
            Button(
                onClick = {
                    if (title.isNotBlank()) {
                        updateToDoList(id, title)
                        onTitleChange("")
                    } else {
                        Toast.makeText(context, "List name cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    dismissDialog()
                }
            ) {
                Text(text = "OK")
            }
        },
        dismissButton = {
            Button(
                onClick = { dismissDialog() }
            ) {
                Text(text = "Cancel")
            }
        },
        modifier = modifier
    )
}

@Composable
fun ConfirmDeleteDialog(
    id: Long,
    dismissDialog: () -> Unit,
    deleteToDo: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = {
            Text(
                text = "Remove?",
                style = MaterialTheme.typography.titleLarge
            )
        },
        onDismissRequest = { dismissDialog() },
        confirmButton = {
            Button(
                onClick = {
                    deleteToDo(id)
                    dismissDialog()
                }
            ) {
                Text(text = "Remove")
            }
        },
        dismissButton = {
            Button(
                onClick = { dismissDialog() }
            ) {
                Text(text = "Cancel")
            }
        },
        modifier = modifier
    )
}