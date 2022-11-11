package com.techdroidcentre.todo.data.local

import com.techdroidcentre.todo.data.local.entities.TaskEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity
import com.techdroidcentre.todo.data.util.Priority

object Data {
    val toDos = listOf(
        ToDoListEntity(
            1L,
            "Jetpack Compose",
            0
        ),
        ToDoListEntity(
            2L,
            "Kotlin Coroutines",
            0
        )
    )
    val tasks = listOf(
        TaskEntity(
            10,
            "State and Events",
            "Finish states and events in compose",
            System.currentTimeMillis(),
            Priority.NONE,
            false,
            1L
        ),
        TaskEntity(
            11,
            "Compose Animations",
            "Go through jetpack compose animations documentation",
            0L,
            Priority.NONE,
            false,
            1L
        ),
        TaskEntity(
            12,
            "Coroutines",
            "Use coroutines for asynchronous operations",
            0L,
            Priority.NONE,
            false,
            2L
        )
    )
}