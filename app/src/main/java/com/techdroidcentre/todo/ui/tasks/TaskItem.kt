package com.techdroidcentre.todo.ui.tasks

import android.content.Context
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority
import com.techdroidcentre.todo.data.worker.KEY_TASK_TITLE
import com.techdroidcentre.todo.data.worker.ReminderWorker
import com.techdroidcentre.todo.ui.theme.ToDoTheme
import java.util.*
import java.util.concurrent.TimeUnit

@ExperimentalMaterial3Api
@Composable
fun TaskItem(
    task: TaskState,
    isBackPressed: Boolean,
    saveTask: (task:Task) -> Unit,
    deleteTask: (task: Task) -> Unit
) {
    val originalTitle = task.title
    val originalContent = task.content
    val originalPriority = task.priority.name
    val originalDueDate = task.dueDate
    val completed = task.isComplete
    val context = LocalContext.current

    var title by remember { mutableStateOf(task.title) }
    var content by remember { mutableStateOf(task.content) }
    var priority by remember { mutableStateOf(task.priority.name) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var isComplete by remember { mutableStateOf(task.isComplete) }
    var isTitleFocused by remember { mutableStateOf(false) }
    var isContentFocused by remember { mutableStateOf(false) }
    var taskExpanded by remember { mutableStateOf(false) }
    var dropDownExpanded by remember { mutableStateOf(false) }

    if (isBackPressed) {
        isTitleFocused = false
        isContentFocused = false
    }

    if ((!isTitleFocused && originalTitle != title)
        || (!isContentFocused && originalContent != content)
        || originalPriority != priority
        || originalDueDate != dueDate
        || completed != isComplete) {
        saveTask(Task(task.id, title, content, dueDate, Priority.valueOf(priority), isComplete))
    }

    fun scheduleNotification (context: Context, dueDate: Long) {
        val calendar = Calendar.getInstance()
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(
            Calendar.DATE))
        val duration = dueDate - calendar.timeInMillis
        val workRequest = OneTimeWorkRequestBuilder<ReminderWorker>()
            .setInitialDelay(duration, TimeUnit.MILLISECONDS)
            .setInputData(workDataOf(KEY_TASK_TITLE to title))
            .build()
        if (duration > 0L) {
            WorkManager.getInstance(context).enqueue(workRequest)
        } else {
            WorkManager.getInstance(context).cancelWorkById(workRequest.id)
        }
    }

    TaskItem(
        title = title,
        content = content,
        onTitleChange = { title = it },
        onContentChange = { content = it},
        completed = isComplete,
        onCompletedChange = { isComplete = it },
        isTitleFocused = isTitleFocused,
        isContentFocused = isContentFocused,
        onTitleFocusChanged = { isTitleFocused = it.isFocused },
        onContentFocusChanged = { isContentFocused = it.isFocused },
        taskExpanded = taskExpanded,
        onExpandTask = { taskExpanded = !taskExpanded},
        dropDownExpanded = dropDownExpanded,
        priority = priority,
        date = dueDate,
        onDateAndTimeSet = {
            dueDate = it
            scheduleNotification(context, it)
        },
        onDismissRequest = { dropDownExpanded = false },
        onDropDownMenuClick = { dropDownExpanded = true },
        onDropDownMenuItemSelected = {
            priority = it
            dropDownExpanded = false
        },
        onTaskDelete = { deleteTask(task.toDomainModel()) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun TaskItem(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    completed: Boolean,
    onCompletedChange: (Boolean) -> Unit,
    isTitleFocused: Boolean,
    isContentFocused: Boolean,
    onTitleFocusChanged: (FocusState) -> Unit,
    onContentFocusChanged: (FocusState) -> Unit,
    taskExpanded: Boolean,
    onExpandTask: () -> Unit,
    dropDownExpanded: Boolean,
    priority: String,
    date: Long,
    onDateAndTimeSet: (Long) -> Unit,
    onDismissRequest: () -> Unit,
    onDropDownMenuClick: () -> Unit,
    onDropDownMenuItemSelected: (String) -> Unit,
    onTaskDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.animateContentSize(
            animationSpec = spring(
                stiffness = Spring.StiffnessLow
            )
        ),
        onClick = { /*TODO*/ },
        shape = ShapeDefaults.ExtraSmall.copy(CornerSize(2.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            TaskHeaderSection(
                title = title,
                onTitleChange = onTitleChange,
                completed = completed,
                onCompletedChange = onCompletedChange,
                isTitleFocused = isTitleFocused,
                onTitleFocusChanged = onTitleFocusChanged,
                taskExpanded = taskExpanded,
                onExpandTask = onExpandTask
            )
            if (taskExpanded) {
                TaskBodySection(
                    content = content,
                    onContentChange = onContentChange,
                    isContentFocused = isContentFocused,
                    onContentFocusChanged = onContentFocusChanged,
                    priority = priority,
                    date = date,
                    onDateAndTimeSet = onDateAndTimeSet,
                    dropDownExpanded = dropDownExpanded,
                    onDismissRequest = onDismissRequest,
                    onDropDownMenuClick = onDropDownMenuClick,
                    onDropDownMenuItemSelected = onDropDownMenuItemSelected,
                    onTaskDelete = onTaskDelete
                )
            }
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TaskItemPreview() {
    ToDoTheme {
        TaskItem(
            title = "Jetpack Compose",
            content = "Finish compose codelab",
            onTitleChange = {},
            onContentChange = {},
            completed = false,
            onCompletedChange = {},
            isTitleFocused = false,
            isContentFocused = false,
            onTitleFocusChanged = {},
            onContentFocusChanged = {},
            taskExpanded = true,
            onExpandTask = {},
            dropDownExpanded = true,
            priority = "None",
            date = 0L,
            onDateAndTimeSet = {},
            onDismissRequest = {},
            onDropDownMenuClick = {},
            onDropDownMenuItemSelected = {},
            onTaskDelete = {}
        )
    }
}
