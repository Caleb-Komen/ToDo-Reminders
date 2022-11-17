package com.techdroidcentre.todo.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
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

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalMaterial3Api
@Composable
fun ToDoListItem(
    toDoState: ToDoState,
    onClick: (Long, Int) -> Unit,
    showDialog: (Long, String) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(
                onLongClick = { showDialog(toDoState.id, toDoState.title) },
                onClick = { onClick(toDoState.id, toDoState.colour) }
            ),
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
                style = MaterialTheme.typography.titleLarge,
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
                    if (tasks.size > 5) {
                        Text(text = "\u22EE", modifier = Modifier.padding(start = 24.dp))
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
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )

        Text(
            text = taskTitle,
            style = MaterialTheme.typography.bodySmall,
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

@ExperimentalMaterial3Api
@Preview
@Composable
fun ToDoListItemPreview() {
    ToDoListItem(
        ToDoState(
            0,
            "New ToDo",
            List(5) { "Task $it" },
            Color(0xFF2367AB).toArgb()
        ),
        {_,_ -> },
        {_, _ ->}
    )
}