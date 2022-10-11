package com.techdroidcentre.todo.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techdroidcentre.todo.ui.todos.ToDoListItemScreen
import com.techdroidcentre.todo.ui.todos.ToDoListViewModel

@Composable
fun ToDoApp(
    viewModel: ToDoListViewModel = viewModel()
) {
    val toDoListViewState by viewModel.toDoListViewState
    ToDoListItemScreen(toDoListViewState)
}