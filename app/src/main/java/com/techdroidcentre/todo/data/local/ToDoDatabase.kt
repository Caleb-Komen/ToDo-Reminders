package com.techdroidcentre.todo.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.techdroidcentre.todo.data.local.entities.TaskEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity

@Database(entities = [TaskEntity::class, ToDoListEntity::class], version = 1)
abstract class ToDoDatabase: RoomDatabase() {
    abstract val taskDao: TaskDao
    abstract val toDoListDao: ToDoListDao

    companion object {
        const val DATABASE_NAME = "todo_db"
    }
}