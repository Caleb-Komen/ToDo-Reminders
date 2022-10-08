package com.techdroidcentre.todo.data.local

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth
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
    fun getAllTasks_confirmTasksAvailable() = runTest {
        val tasks = taskDao.getTasks().first()
        Truth.assertThat(tasks).containsAnyIn(Data.tasks)
    }

    @Test
    fun addTaskAndGetAllTasks_newTaskAddedToDb() = runTest {
        val task = TaskEntity(
            15,
            "Theming in compose",
            "Finish theming in compose",
            0L,
            Priority.NONE,
            1L
        )
        taskDao.addTask(task)
        val tasks = taskDao.getTasks().first()
        Truth.assertThat(tasks).contains(task)
    }

    @Test
    fun deleteTask_taskRemovedFromDb() = runTest {
        var tasks = taskDao.getTasks().first()
        val taskToDelete = tasks[0]

        taskDao.deleteTask(taskToDelete.id)

        tasks = taskDao.getTasks().first()
        Truth.assertThat(tasks).doesNotContain(taskToDelete)
    }
}