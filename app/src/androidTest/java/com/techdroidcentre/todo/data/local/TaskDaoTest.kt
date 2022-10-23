package com.techdroidcentre.todo.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
import com.techdroidcentre.todo.data.BaseTest
import com.techdroidcentre.todo.data.local.entities.TaskEntity
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
    private lateinit var taskDao: TaskDao

    @Before
    fun setup() {
        taskDao = database.taskDao
        addTasks(Data.tasks)
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