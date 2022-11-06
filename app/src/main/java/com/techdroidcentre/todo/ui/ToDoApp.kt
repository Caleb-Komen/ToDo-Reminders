package com.techdroidcentre.todo.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.techdroidcentre.todo.ui.Screen.ToDoListScreen
import com.techdroidcentre.todo.ui.components.NewToDoListDialog
import com.techdroidcentre.todo.ui.components.ToDoTabRow
import com.techdroidcentre.todo.ui.components.TopBar

@ExperimentalMaterial3Api
@Composable
fun ToDoApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }
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
            Column {
                TopBar {
                    showNewListDialog = true
                }
            }
        }
    ) {
        ToDoNavGraph(
            navController = navController,
            snackbarHostState = snackbarHostState,
            modifier = modifier.padding(it)
        )
    }
}