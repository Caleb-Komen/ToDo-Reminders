package com.techdroidcentre.todo.ui.home

import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.data.util.Priority

data class ToDoListViewState(
    val todos: List<ToDoState> = emptyList(),
    val isLoading: Boolean = false
)

data class ToDoState(
    val id: Long,
    val title: String = "",
    val tasks: List<String> = emptyList(),
    val colour: Int = 0
)

data class TaskState(
    val id: Long,
    val taskTitle: String,
    val todoTitle: String,
    val content: String,
    val dueDate: Long,
    val priority: Priority,
    val isComplete: Boolean
)

fun ToDoList.toViewState(): ToDoState{
    return ToDoState(
        id = id,
        title = title,
        tasks = tasks.filter { !it.isComplete }.map { it.title },
        colour = colour
    )
}

fun Task.toViewState(todoTitle: String): TaskState {
    return TaskState(
        id = id,
        taskTitle = title,
        todoTitle = todoTitle,
        content = content,
        dueDate = dueDate,
        priority = priority,
        isComplete = isComplete
    )
}

data class ScheduledTaskState(
    val tasks: Map<String, List<TaskState>> = emptyMap(),
    val isLoading: Boolean = false
)

data class ScheduledTasksForTodayState(
    val tasks: List<TaskState> = emptyList(),
    val isLoading: Boolean = false
)
