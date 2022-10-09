package com.techdroidcentre.todo.data.repository

import com.techdroidcentre.todo.data.local.ToDoListDao
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.mapper.toDomainModel
import com.techdroidcentre.todo.mapper.toEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ToDoListRepositoryImpl(
    private val toDoListDao: ToDoListDao
): ToDoListRepository {
    override fun getAllToDoList(): Flow<List<ToDoList>> {
        return toDoListDao.getAllToDoList().map {
            it.map { todoList ->
                todoList.toDomainModel()
            }
        }
    }

    override suspend fun addToDoList(todoList: ToDoList) {
        toDoListDao.addToDoList(todoList.toEntity())
    }

    override suspend fun deleteToDoList(id: Long) {
        toDoListDao.deleteToDoList(id)
    }
}