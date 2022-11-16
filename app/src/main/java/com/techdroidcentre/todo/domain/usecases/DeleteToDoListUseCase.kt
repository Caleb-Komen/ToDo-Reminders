package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.repository.ToDoListRepository
import javax.inject.Inject

class DeleteToDoListUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {
    suspend operator fun invoke(id: Long) {
        repository.deleteToDoList(id)
    }
}