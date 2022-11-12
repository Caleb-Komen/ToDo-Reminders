package com.techdroidcentre.todo.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority

@Composable
fun ScheduledTask(
    task: Task,
    todoTitle: String,
    modifier: Modifier = Modifier
) {
    var taskExpanded by remember { mutableStateOf(false) }

    ScheduledTask(
        task = task,
        todoTitle = todoTitle,
        taskExpanded = taskExpanded,
        expandTask = { taskExpanded = !taskExpanded },
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScheduledTask(
    task: Task,
    todoTitle: String,
    taskExpanded: Boolean,
    expandTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.animateContentSize(
            animationSpec = spring(
                stiffness = Spring.StiffnessLow
            )
        ),
        onClick = expandTask,
        shape = ShapeDefaults.ExtraSmall.copy(CornerSize(2.dp)),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            ScheduledTaskHeader(
                task = task,
                todoTitle = todoTitle,
                taskExpanded = taskExpanded,
                expandTask = expandTask
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (taskExpanded) {
                ScheduledTaskBody(task = task)
            }
        }
    }
}

@Composable
fun ScheduledTaskHeader(
    task: Task,
    todoTitle: String,
    taskExpanded: Boolean,
    expandTask: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = task.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = "($todoTitle)",
                style = MaterialTheme.typography.titleSmall,
                fontStyle = FontStyle.Italic,
                modifier = Modifier
            )
        }
        IconButton(onClick = expandTask) {
            if (taskExpanded)
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = null
                )
            else
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null
                )
        }
    }
}

@Composable
fun ScheduledTaskBody(
    task: Task,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Divider()
        Spacer(modifier = Modifier.height(4.dp))
        Box(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray
                )
        ) {
            Text(
                text=task.content,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .heightIn(min = 96.dp)
            )
        }
    }
}

@Preview
@Composable
fun ScheduledTaskPreview() {
    ScheduledTask(
        task = Task(1, "Task title", "Task Content", System.currentTimeMillis(), Priority.NONE, false),
        todoTitle = "ToDo",
        taskExpanded = true,
        expandTask = {}
    )
}