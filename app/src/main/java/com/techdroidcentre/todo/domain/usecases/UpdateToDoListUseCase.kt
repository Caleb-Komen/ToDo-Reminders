package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.data.repository.ToDoListRepository
import javax.inject.Inject

class UpdateToDoListUseCase @Inject constructor(
    private val repository: ToDoListRepository
) {
    suspend operator fun invoke(todoList: ToDoList) {
        repository.updateToDoList(todoList)
    }
}