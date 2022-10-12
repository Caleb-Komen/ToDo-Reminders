package com.techdroidcentre.todo.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techdroidcentre.todo.ui.components.TopBar
import com.techdroidcentre.todo.ui.todos.ToDoListItemScreen
import com.techdroidcentre.todo.ui.todos.ToDoListViewModel

@ExperimentalMaterial3Api
@Composable
fun ToDoApp() {
    Scaffold(
        topBar = { TopBar() }
    ) {
        ToDoListItemScreen(modifier = Modifier.padding(it))
    }
}