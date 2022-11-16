package com.techdroidcentre.todo.ui.components

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext

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