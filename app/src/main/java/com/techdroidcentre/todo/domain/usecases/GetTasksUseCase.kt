package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTasksUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    operator fun invoke(todoListId: Long): Flow<List<Task>> {
        return repository.getTasks(todoListId)
    }
}