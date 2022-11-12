package com.techdroidcentre.todo.ui.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.techdroidcentre.todo.R

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ToDoListTabContent(
    viewState: ToDoListViewState,
    onClick: (Long, Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (viewState.isLoading) {
            CircularProgressIndicator()
        }

        if (viewState.todos.isEmpty()){
            EmptyListItem()
        } else {
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(128.dp),
                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = viewState.todos) { todoState ->
                    ToDoListItem(
                        toDoState = todoState,
                        onClick = onClick
                    )
                }
            }
        }
    }
}

@Composable
fun ScheduledTabContent(
    state: ScheduledTaskState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        if (state.tasks.isEmpty()) {
            EmptyTasksList()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                state.tasks.forEach { (date, items) ->
                    item {
                        Text(
                            text = date,
                            modifier = Modifier.fillMaxSize()
                                .padding(bottom = 8.dp)
                        )
                    }
                    items(items = items) { task ->
                        ScheduledTask(
                            task = task,
                            todoTitle = "ToD0"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TodayTabContent(
    state: ScheduledTasksForTodayState,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        }

        if (state.tasks.isEmpty()) {
            EmptyTasksList()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(12.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(items = state.tasks) { task ->
                    ScheduledTask(
                        task = task,
                        todoTitle = "ToDo"
                    )
                }
            }
        }
    }
}

@Composable
fun EmptyTasksList(modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_event_available_24),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Text(
            text="No tasks",
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.titleLarge.fontSize,
            color = Color.Black
        )
    }
}
