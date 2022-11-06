package com.techdroidcentre.todo.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
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
            LazyVerticalGrid(
                columns = GridCells.Adaptive(128.dp),
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
fun ScheduledTabContent() {
    Text(text = "Coming soon")
}

@Composable
fun TodayTabContent() {
    Text(text = "Coming soon")
}