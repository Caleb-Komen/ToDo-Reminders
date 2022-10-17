package com.techdroidcentre.todo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.techdroidcentre.todo.ui.components.NewToDoListDialog
import com.techdroidcentre.todo.ui.components.TopBar

@ExperimentalMaterial3Api
@Composable
fun ToDoApp() {
    var value by remember { mutableStateOf("") }
    var showNewListDialog by remember { mutableStateOf(false) }
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

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
        },
        snackbarHost = {
            SnackbarHost(hostState =snackbarHostState)
        }
    ) {
        ToDoNavGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            modifier = Modifier.padding(it)
        )
    }
}