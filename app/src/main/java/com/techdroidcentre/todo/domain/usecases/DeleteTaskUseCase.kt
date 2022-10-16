package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.repository.TasksRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(taskId: Long) {
        repository.deleteTask(taskId)
    }
}