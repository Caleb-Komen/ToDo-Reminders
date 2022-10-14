package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.repository.TasksRepository
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(todoListId: Long, task: Task) {
        repository.addTask(todoListId, task)
    }
}