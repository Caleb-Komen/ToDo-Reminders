package com.techdroidcentre.todo.data.local.entities

import androidx.room.Embedded
import androidx.room.Relation

data class ToDoListWithTasksEntity(
    @Embedded
    val toDoListEntity: ToDoListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "todo_list_id"
    )
    val tasksEntity: List<TaskEntity>
)
