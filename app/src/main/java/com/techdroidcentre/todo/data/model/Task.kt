package com.techdroidcentre.todo.data.model

import com.techdroidcentre.todo.data.util.Priority

data class Task(
    val id: Long,
    val title: String,
    val content: String,
    val dueDate: Long,
    val priority: Priority,
    val isComplete: Boolean
)
