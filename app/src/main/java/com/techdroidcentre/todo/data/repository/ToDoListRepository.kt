package com.techdroidcentre.todo.data.repository

import com.techdroidcentre.todo.data.model.ToDoList
import kotlinx.coroutines.flow.Flow

interface ToDoListRepository {
    fun getAllToDoList(): Flow<List<ToDoList>>

    suspend fun addToDoList(todoList: ToDoList)

    suspend fun deleteToDoList(id: Long)
}