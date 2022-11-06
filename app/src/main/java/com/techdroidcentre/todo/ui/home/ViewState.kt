package com.techdroidcentre.todo.ui.home

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
        tasks = tasks.map { it.title },
        colour = colour
    )
}
