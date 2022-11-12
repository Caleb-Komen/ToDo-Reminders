package com.techdroidcentre.todo.ui.home

import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.model.ToDoList

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

fun ToDoList.toViewState(): ToDoState{
    return ToDoState(
        id = id,
        title = title,
        tasks = tasks.filter { !it.isComplete }.map { it.title },
        colour = colour
    )
}

data class ScheduledTaskState(
    val tasks: Map<String, List<Task>> = emptyMap(),
    val isLoading: Boolean = false
)

data class ScheduledTasksForTodayState(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = false
)
