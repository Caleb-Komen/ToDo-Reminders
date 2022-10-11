package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.data.repository.ToDoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllToDoListUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {
    operator fun invoke(): Flow<List<ToDoList>> {
        return repository.getAllToDoList()
    }
}