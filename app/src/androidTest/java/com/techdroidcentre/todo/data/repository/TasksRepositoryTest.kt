package com.techdroidcentre.todo.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.techdroidcentre.todo.data.BaseTest
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.model.ToDoList
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
    private lateinit var repository: ToDoListRepository
    // subject to test
    private lateinit var tasksRepository: TasksRepository
    private val todos = listOf(
        ToDoList(1L, "Test and Debug", 0),
        ToDoList(2L, "Dependency Injection", 0)
    )
    private val tasks = listOf(
        Task(20L, "Testing & Debugging", "Finish testing & debugging codelab", System.currentTimeMillis() + 87400000, Priority.NONE, false),
        Task(21L, "DI", "Finish Hilt codelab", System.currentTimeMillis(), Priority.NONE, false)
    )

    @Before
    fun setup() = runBlocking {
        repository = ToDoListRepositoryImpl(database.toDoListDao)
        tasksRepository = TasksRepositoryImpl(database.taskDao)
        // add todos to the parent table first to prevent the mistake of...
        // ...adding tasks first to the child table with an empty parent table
        todos.forEach {
            repository.addToDoList(it)
        }
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
        val task = Task(22L, "States and Events", "Finish states and events in compose", 0L, Priority.NONE, false)
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