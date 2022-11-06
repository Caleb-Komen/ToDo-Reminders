package com.techdroidcentre.todo.ui.components

import android.widget.Toast
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.ui.home.HomeViewModel
import com.techdroidcentre.todo.ui.util.defaultColour
import com.techdroidcentre.todo.ui.util.defaultId

@ExperimentalMaterial3Api
@Composable
fun NewToDoListDialog(
    value: String,
    onValueChange: (String) -> Unit,
    dismissDialog: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
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
                        viewModel.addToDoList(ToDoList(defaultId, value, defaultColour))
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