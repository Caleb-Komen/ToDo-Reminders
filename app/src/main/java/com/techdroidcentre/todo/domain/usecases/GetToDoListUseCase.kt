package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.data.repository.ToDoListRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToDoListUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {
    operator fun invoke(id: Long): Flow<ToDoList?> {
        return repository.getToDoList(id)
    }
}