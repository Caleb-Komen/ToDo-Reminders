package com.techdroidcentre.todo.ui.todos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.techdroidcentre.todo.ui.Screen
import com.techdroidcentre.todo.ui.components.NewToDoListDialog
import com.techdroidcentre.todo.ui.components.TopBar

@ExperimentalMaterial3Api
@Composable
fun ToDoListScreen(
    onClick: (Long, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ToDoListViewModel = hiltViewModel()
) {
    val toDoListViewState by viewModel.toDoListViewState

    ToDoListScreen(
        viewState = toDoListViewState,
        onClick = onClick,
        modifier = modifier
    )
}

@ExperimentalMaterial3Api
@Composable
fun ToDoListScreen(
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

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun ToDoListItemScreenPreview() {
    val todos = List(5) {
        ToDoState(
            it.toLong(),
            "New ToDo $it",
            List(5) { "Task $it" },
            Color(0xFF2367AB).toArgb()
        )
    }
    ToDoListScreen(
        ToDoListViewState(
            todos = todos
        ),
        {_, _ -> }
    )
}