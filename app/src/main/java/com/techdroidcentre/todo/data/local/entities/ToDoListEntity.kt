package com.techdroidcentre.todo.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class ToDoListEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val colour: Int
)
