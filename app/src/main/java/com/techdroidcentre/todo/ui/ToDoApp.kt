package com.techdroidcentre.todo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.techdroidcentre.todo.ui.components.NewToDoListDialog
import com.techdroidcentre.todo.ui.components.TopBar
import com.techdroidcentre.todo.ui.todos.ToDoListItemScreen

@ExperimentalMaterial3Api
@Composable
fun ToDoApp() {
    var value by remember { mutableStateOf("") }
    var showNewListDialog by remember { mutableStateOf(false) }

    if (showNewListDialog) {
        NewToDoListDialog(
            value = value,
            onValueChange = { value = it },
            dismissDialog = { showNewListDialog = false }
        )
    }

    Scaffold(
        topBar = {
            TopBar {
                showNewListDialog = true
            }
        }
    ) {
        ToDoListItemScreen(modifier = Modifier.padding(it))
    }
}