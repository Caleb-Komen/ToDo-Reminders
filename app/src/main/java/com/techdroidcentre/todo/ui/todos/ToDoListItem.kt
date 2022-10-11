package com.techdroidcentre.todo.ui.todos

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ToDoListItem(
    toDoState: ToDoState,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(toDoState.colour))
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .defaultMinSize(minHeight = 56.dp)
        ) {
            Text(
                text = toDoState.title,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
                fontSize = MaterialTheme.typography.titleMedium.fontSize
            )
            val tasks = toDoState.tasks
            if (tasks.isNotEmpty()){
                Column {
                    tasks.take(5).forEach {
                        TaskSnippet(taskTitle = it)
                    }
                }
            }
        }
    }
}

@Composable
fun TaskSnippet(
    taskTitle: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = null
        )

        Text(
            text = taskTitle,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun TaskSnippetPreview() {
    TaskSnippet("A task")
}

@Preview
@Composable
fun ToDoListItemPreview() {
    ToDoListItem(
        ToDoState(
            0,
            "New ToDo",
            List(5) { "Task $it" },
            Color(0xFF2367AB).toArgb()
        )
    )
}