package com.techdroidcentre.todo.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority
import com.techdroidcentre.todo.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun TasksScreen(
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = hiltViewModel()
) {
    val tasks = viewModel.uiState.value.tasks
    val colour = viewModel.uiState.value.colour

    LazyColumn(
        modifier = modifier.background(Color(colour)).fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = tasks, key = { task -> task.id }) { task ->
            TaskItem(
                task = task,
                saveTask = { viewModel.addTask(it) },
                deleteTask = { viewModel.deleteTask(it) }
            )
        }
        item {
            NewTaskItem(
                value = viewModel.taskTitle,
                onValueChange = {
                    viewModel.updateTitle(it)
                },
                onDone = {
                    viewModel.addTask(Task(0L,viewModel.taskTitle, "", 0L, Priority.NONE))
                    viewModel.updateTitle("")
                }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    ToDoTheme {
        TasksScreen()
    }
}