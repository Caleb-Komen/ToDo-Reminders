package com.techdroidcentre.todo.data.repository

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.techdroidcentre.todo.data.BaseTest
import com.techdroidcentre.todo.data.model.ToDoList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ToDoListRepositoryTest: BaseTest() {
    private lateinit var repository: ToDoListRepository
    private val todos = listOf(
        ToDoList(1L, "Test and Debug", 0),
        ToDoList(2L, "Dependency Injection", 0)
    )

    @Before
    fun setup()= runBlocking {
        repository = ToDoListRepositoryImpl(database.toDoListDao)
        todos.forEach {
            repository.addToDoList(it)
        }
    }

    @Test
    fun getAllToDoList() = runTest {
        val result = repository.getAllToDoList().first()
        Truth.assertThat(result.size).isAtLeast(1)
    }

    @Test
    fun getToDoList() = runTest {
        val todo = todos[0]
        val result = repository.getToDoList(todo.id).first()
        Truth.assertThat(result).isEqualTo(todo)
    }

    @Test
    fun getToDoList_unknownId_returnNull() = runTest {
        val result = repository.getToDoList(-1L).first()
        Truth.assertThat(result).isNull()
    }

    @Test
    fun addToDoList() = runTest {
        val todo = ToDoList(3L, "Jetpack", 0)
        repository.addToDoList(todo)

        val result = repository.getAllToDoList().first()
        Truth.assertThat(result).contains(todo)
    }

    @Test
    fun deleteToDoList() = runTest {
        var todos = repository.getAllToDoList().first()
        val todoToDelete = todos[0]

        repository.deleteToDoList(todoToDelete.id)

        todos = repository.getAllToDoList().first()
        Truth.assertThat(todos).doesNotContain(todoToDelete)
    }
}
