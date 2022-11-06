package com.techdroidcentre.todo.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavBackStackEntry
import com.techdroidcentre.todo.ui.Screen
import com.techdroidcentre.todo.ui.components.ToDoTabRow

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun HomeScreen(
    onToDoClick: (Long, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val toDoListViewState by viewModel.toDoListViewState
    val tabState by viewModel.tabState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeContent(
            tabState = tabState,
            todoViewState = toDoListViewState,
            onToDoClick = onToDoClick,
            onTabSelected = viewModel::switchTab
        )
    }
}

@Composable
fun HomeContent(
    tabState: ToDoTabState,
    todoViewState: ToDoListViewState,
    onToDoClick: (Long, Int) -> Unit,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ToDoTabRow(
            tabState = tabState,
            onTabSelected = onTabSelected
        )
        when (tabState.currentIndex) {
            0 -> {
                ToDoListTabContent(
                    viewState = todoViewState,
                    onClick = onToDoClick
                )
            }
            1 -> {
                ScheduledTabContent()
            }
            2 -> {
                TodayTabContent()
            }
        }
    }
}