package com.techdroidcentre.todo.data.repository

import com.techdroidcentre.todo.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TasksRepository {
    fun getTasks(toDoListId: Long): Flow<List<Task>>

    fun getScheduledTasks(): Flow<List<Task>>

    fun getScheduledTasksForToday(): Flow<List<Task>>

    suspend fun addTask(todoListId: Long,task: Task)

    suspend fun deleteTask(id: Long)
}