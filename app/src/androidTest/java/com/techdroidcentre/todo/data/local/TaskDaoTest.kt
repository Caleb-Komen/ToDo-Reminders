package com.techdroidcentre.todo.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.techdroidcentre.todo.data.BaseTest
import com.techdroidcentre.todo.data.local.entities.TaskEntity
import com.techdroidcentre.todo.data.local.entities.ToDoListEntity
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
class TaskDaoTest: BaseTest() {
    private lateinit var toDoListDao: ToDoListDao
    // subject to test
    private lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        toDoListDao = database.toDoListDao
        taskDao = database.taskDao
        // add todos to the parent table first to prevent the mistake of...
        // ...adding tasks first to the child table with an empty parent table
        addToDoLists(Data.toDos)
        addTasks(Data.tasks)
    }

    private fun addToDoLists(lists: List<ToDoListEntity>) = runBlocking{
        lists.forEach {
            toDoListDao.addToDoList(it)
        }
    }

    private fun addTasks(tasks: List<TaskEntity>) = runBlocking{
        tasks.forEach {
            taskDao.addTask(it)
        }
    }

    @Test
    fun getAllTasksWithToDoListId_confirmTasksAvailable() = runTest {
        val todo = Data.toDos[0]
        val tasks = taskDao.getTasks(todo.id).first()
        Truth.assertThat(Data.tasks).containsAnyIn(tasks)
    }

    @Test
    fun getScheduledTasks() = runTest {
        val tasks = taskDao.getScheduledTasks().first()
        // We have one scheduled task in the db
        Truth.assertThat(tasks.size).isEqualTo(1)
    }

    @Test
    fun addTaskAndGetAllTasks_newTaskAddedToDb() = runTest {
        val todo = Data.toDos[0]
        val task = TaskEntity(
            15,
            "Theming in compose",
            "Finish theming in compose",
            0L,
            Priority.NONE,
            false,
            1L
        )
        taskDao.addTask(task)
        val tasks = taskDao.getTasks(todo.id).first()
        Truth.assertThat(tasks).contains(task)
    }

    @Test
    fun deleteTask_taskRemovedFromDb() = runTest {
        val todo = Data.toDos[0]
        var tasks = taskDao.getTasks(todo.id).first()
        val taskToDelete = tasks[0]

        taskDao.deleteTask(taskToDelete.id)

        tasks = taskDao.getTasks(todo.id).first()
        Truth.assertThat(tasks).doesNotContain(taskToDelete)
    }
}