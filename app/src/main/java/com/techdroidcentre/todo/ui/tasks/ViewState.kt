package com.techdroidcentre.todo.ui.tasks

import androidx.compose.ui.graphics.toArgb
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority
import com.techdroidcentre.todo.ui.util.colours

data class TasksUIState(
    val tasks: List<TaskState> = emptyList(),
    val isLoading: Boolean = false,
    val colour: Int = colours[0].toArgb()
)

data class TaskState(
    val id: Long = 0L,
    val title: String,
    val content: String = "",
    val dueDate: Long = 0L,
    val priority: Priority = Priority.NONE
)

fun Task.toViewState(): TaskState {
    return TaskState(id, title, content, dueDate, priority)
}