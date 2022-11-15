package com.techdroidcentre.todo.data.repository

import android.text.format.DateUtils
import com.techdroidcentre.todo.data.local.TaskDao
import com.techdroidcentre.todo.data.local.ToDoListDao
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.mapper.toDomainModel
import com.techdroidcentre.todo.mapper.toEntity
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TasksRepositoryImpl @Inject constructor(
    private val todoDao: ToDoListDao,
    private val taskDao: TaskDao
): TasksRepository {
    override fun getTasks(toDoListId: Long): Flow<List<Task>> {
        return taskDao.getTasks(toDoListId).map {
            it.map { entity ->
                entity.toDomainModel()
            }
        }
    }

    override fun getScheduledTasks(): Flow<List<Task>> {
        return taskDao.getScheduledTasks().map {
            it.map { entity ->
                entity.toDomainModel()
            }
        }
    }

    override fun getScheduledTasksForToday(): Flow<List<Task>> {
        return taskDao.getScheduledTasks().map {
            it.filter { entity ->
                DateUtils.isToday(entity.dueDate)
            }.map { entity ->
                entity.toDomainModel()
            }
        }
    }

    override suspend fun getToDoTitleForTask(taskId: Long): String? {
        return coroutineScope {
            val taskEntity = async { taskDao.getTask(taskId) }
            val todoTitle = async { todoDao.getToDoListTitle(taskEntity.await()?.toDoListId!!) }
            todoTitle.await()
        }
    }

    override suspend fun addTask(todoListId: Long, task: Task) {
        taskDao.addTask(task.toEntity(todoListId))
    }

    override suspend fun deleteTask(id: Long) {
        taskDao.deleteTask(id)
    }
}