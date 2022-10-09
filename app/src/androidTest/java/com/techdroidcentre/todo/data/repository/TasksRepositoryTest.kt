package com.techdroidcentre.todo.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.techdroidcentre.todo.data.BaseTest
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TasksRepositoryTest: BaseTest() {
    private lateinit var tasksRepository: TasksRepository
    private val tasks = listOf(
        Task(20L, "Testing & Debugging", "Finish testing & debugging codelab", 0L, Priority.NONE),
        Task(21L, "DI", "Finish Hilt codelab", 0L, Priority.NONE)
    )

    @Before
    fun setup() = runBlocking {
        tasksRepository = TasksRepositoryImpl(database.taskDao)
        tasks.forEach {
            tasksRepository.addTask(1L, it)
        }
    }
    @Test
    fun getTasks() = runTest {
        val result = tasksRepository.getTasks(1L).first()
        Truth.assertThat(result).containsAnyIn(tasks)
    }

    @Test
    fun addTask() = runTest {
        val task = Task(22L, "States and Events", "Finish states and events in compose", 0L, Priority.NONE)
        tasksRepository.addTask(1L, task)

        val result = tasksRepository.getTasks(1L).first()
        Truth.assertThat(result).contains(task)
    }

    @Test
    fun deleteTask() = runTest {
        var tasks = tasksRepository.getTasks(1L).first()
        val taskToDelete = tasks[0]

        tasksRepository.deleteTask(taskToDelete.id)

        tasks = tasksRepository.getTasks(1L).first()
        Truth.assertThat(tasks).doesNotContain(taskToDelete)
    }
}