package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetToDoTitleForTaskUseCase @Inject constructor(
    private val repository: TasksRepository
){
    suspend operator fun invoke(taskId: Long): String? {
        return repository.getToDoTitleForTask(taskId)
    }
}