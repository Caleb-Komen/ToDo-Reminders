package com.techdroidcentre.todo.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ToDoListDaoTest: BaseTest() {
    private lateinit var toDoListDao: ToDoListDao

    @Before
    fun setup() {
        toDoListDao = database.toDoListDao
        addToDoLists(Data.toDos)
    }

    private fun addToDoLists(lists: List<ToDoListEntity>) = runBlocking{
        lists.forEach {
            toDoListDao.addToDoList(it)
        }
    }

    @Test
    fun getAllToDoList() = runTest{
        val toDos = toDoListDao.getAllToDoList().first()
        Truth.assertThat(toDos).containsAnyIn(Data.toDos)
    }

    @Test
    fun getToDoListWithId() = runTest {
        val todo = Data.toDos[1]
        val result = toDoListDao.getToDoList(todo.id).first()
        Truth.assertThat(result.toDoListEntity).isEqualTo(todo)
    }

    @Test
    fun addToDoList_toDoListAddedToDb() = runTest {
        val todoList = ToDoListEntity(
            3L,
            "Testing and Debugging",
            0
        )

        toDoListDao.addToDoList(todoList)

        val toDos = toDoListDao.getAllToDoList().first()
        Truth.assertThat(toDos).contains(todoList)
    }

    @Test
    fun deleteToDoList_toDoListRemovedFromDb() = runTest {
        var todos = toDoListDao.getAllToDoList().first()
        val todoToDelete = todos[0]

        toDoListDao.deleteToDoList(todoToDelete.id)

        todos = toDoListDao.getAllToDoList().first()
        Truth.assertThat(todos).doesNotContain(todoToDelete)
    }
}