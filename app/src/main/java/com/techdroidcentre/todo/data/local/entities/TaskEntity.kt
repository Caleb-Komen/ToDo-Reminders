package com.techdroidcentre.todo.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.techdroidcentre.todo.data.util.Priority

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val title: String,
    val content: String,
    @ColumnInfo(name = "due_date")
    val dueDate: Long,
    val priority: Priority,
    @ColumnInfo(name = "todo_list_id")
    val toDoListId: Long
)
