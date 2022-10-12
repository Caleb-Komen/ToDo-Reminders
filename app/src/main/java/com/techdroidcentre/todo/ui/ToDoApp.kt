package com.techdroidcentre.todo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techdroidcentre.todo.ui.components.TopBar
import com.techdroidcentre.todo.ui.todos.ToDoListItemScreen

@ExperimentalMaterial3Api
@Composable
fun ToDoApp() {
    Scaffold(
        topBar = { TopBar() }
    ) {
        ToDoListItemScreen(modifier = Modifier.padding(it))
    }
}