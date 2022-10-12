package com.techdroidcentre.todo.ui.components

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.ui.todos.ToDoListViewModel
import com.techdroidcentre.todo.ui.util.colours

@ExperimentalMaterial3Api
@Composable
fun NewToDoListDialog(
    value: String,
    onValueChange: (String) -> Unit,
    dismissDialog: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ToDoListViewModel = viewModel()
) {
    val context = LocalContext.current

    AlertDialog(
        title = {
            Text(text = "New ToDo List")
        },
        text = {
            TextField(
                value = value,
                onValueChange = { onValueChange(it) }
            )
        },
        onDismissRequest = { dismissDialog() },
        confirmButton = {
            Button(
                onClick = {
                    if (value.isNotBlank()) {
                        viewModel.addToDoList(ToDoList(0, value, colours[0].toArgb()))
                        onValueChange("")
                    } else {
                        Toast.makeText(context, "List name cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    dismissDialog()
                }
            ) {
                Text(text = "Create")
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