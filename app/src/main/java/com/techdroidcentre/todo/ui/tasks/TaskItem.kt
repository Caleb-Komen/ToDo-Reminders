package com.techdroidcentre.todo.ui.tasks

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority
import com.techdroidcentre.todo.ui.theme.ToDoTheme
import com.techdroidcentre.todo.ui.util.Util

@ExperimentalMaterial3Api
@Composable
fun TaskItem(
    task: TaskState,
    saveTask: (task:Task) -> Unit,
    deleteTask: (id: Long) -> Unit,
) {
    val originalTitle = task.title
    val originalContent = task.content
    val originalPriority = task.priority.name
    val originalDueDate = task.dueDate

    var title by remember { mutableStateOf(task.title) }
    var content by remember { mutableStateOf(task.content) }
    var priority by remember { mutableStateOf(task.priority.name) }
    var dueDate by remember { mutableStateOf(task.dueDate) }
    var isTitleFocused by remember { mutableStateOf(false) }
    var isContentFocused by remember { mutableStateOf(false) }
    var taskExpanded by remember { mutableStateOf(false) }
    var dropDownExpanded by remember { mutableStateOf(false) }

    if ((!isTitleFocused && originalTitle != title)
        || (!isContentFocused && originalContent != content)
        || originalPriority != priority
        || originalDueDate != dueDate) {

        saveTask(Task(task.id, title, content, dueDate, Priority.valueOf(priority)))
    }

    TaskItem(
        title = title,
        content = content,
        onTitleChange = { title = it },
        onContentChange = { content = it},
        checked = false, // TODO: Work on this
        onCheckedChange = {}, // TODO: Work on this
        isTitleFocused = isTitleFocused,
        isContentFocused = isContentFocused,
        onTitleFocusChanged = { isTitleFocused = it.isFocused },
        onContentFocusChanged = { isContentFocused = it.isFocused },
        taskExpanded = taskExpanded,
        onExpandTask = { taskExpanded = !taskExpanded},
        dropDownExpanded = dropDownExpanded,
        priority = task.priority.name,
        date = Util.toDateString(task.dueDate),
        onDismissRequest = { dropDownExpanded = false },
        onDropDownMenuClick = { dropDownExpanded = true },
        onDropDownMenuItemSelected = { priority = it},
        onTaskDelete = { deleteTask(task.id) }
    )
}

@ExperimentalMaterial3Api
@Composable
fun TaskItem(
    title: String,
    content: String,
    onTitleChange: (String) -> Unit,
    onContentChange: (String) -> Unit,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isTitleFocused: Boolean,
    isContentFocused: Boolean,
    onTitleFocusChanged: (FocusState) -> Unit,
    onContentFocusChanged: (FocusState) -> Unit,
    taskExpanded: Boolean,
    onExpandTask: () -> Unit,
    dropDownExpanded: Boolean,
    priority: String,
    date: String,
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
                checked = checked,
                onCheckedChange = onCheckedChange,
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
            checked = false,
            onCheckedChange = {},
            isTitleFocused = false,
            isContentFocused = false,
            onTitleFocusChanged = {},
            onContentFocusChanged = {},
            taskExpanded = true,
            onExpandTask = {},
            dropDownExpanded = true,
            priority = "None",
            date = "01/11/2022",
            onDismissRequest = {},
            onDropDownMenuClick = {},
            onDropDownMenuItemSelected = {},
            onTaskDelete = {}
        )
    }
}
