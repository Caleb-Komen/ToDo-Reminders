package com.techdroidcentre.todo.ui.tasks

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.techdroidcentre.todo.ui.theme.ToDoTheme

@ExperimentalMaterial3Api
@Composable
fun TasksScreen(
    tasks: List<TaskState>,
    modifier: Modifier = Modifier,
    viewModel: TasksViewModel = viewModel()
) {
    LazyColumn(
        modifier = modifier.background(Color.White),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = tasks) { task ->
            TaskItem(
                task = task,
                saveTask = { viewModel.addTask(it) }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    val tasks = List(5) {
        TaskState(it.toLong(), "Title $it", "content $it")
    }
    ToDoTheme {
        TasksScreen(tasks)
    }
}