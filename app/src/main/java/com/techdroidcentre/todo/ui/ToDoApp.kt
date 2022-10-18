package com.techdroidcentre.todo.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController

@ExperimentalMaterial3Api
@Composable
fun ToDoApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    val snackbarHostState = remember { SnackbarHostState() }

    ToDoNavGraph(
        navController = navController,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}