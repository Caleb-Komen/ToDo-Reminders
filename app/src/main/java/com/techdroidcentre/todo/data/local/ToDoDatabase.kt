package com.techdroidcentre.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.techdroidcentre.todo.data.local.entities.TaskEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity

@Database(entities = [TaskEntity::class, ToDoListEntity::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val toDoListDao: ToDoListDao
}