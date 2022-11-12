package com.techdroidcentre.todo.domain.usecases

import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.repository.TasksRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetScheduledTasksForTodayUseCase @Inject constructor(
    private val repository: TasksRepository
) {
    suspend operator fun invoke(): Flow<List<Task>> {
        return repository.getScheduledTasksForToday()
    }
}