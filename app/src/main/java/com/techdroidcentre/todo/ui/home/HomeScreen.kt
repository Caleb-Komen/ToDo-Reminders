package com.techdroidcentre.todo.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.techdroidcentre.todo.ui.components.NewToDoListDialog
import com.techdroidcentre.todo.ui.components.ToDoTabRow
import com.techdroidcentre.todo.ui.components.TopBar

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onToDoClick: (Long, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val toDoListViewState by viewModel.toDoListViewState
    val tabState by viewModel.tabState.collectAsStateWithLifecycle()
    var value by remember { mutableStateOf("") }
    var showNewListDialog by remember { mutableStateOf(false) }

    if (showNewListDialog) {
        NewToDoListDialog(
            value = value,
            onValueChange = { value = it },
            dismissDialog = { showNewListDialog = false }
        )
    }

    Scaffold(
        topBar = {
            Column {
                TopBar {
                    showNewListDialog = true
                }
            }
        }
    ) {
        Column(
            modifier = modifier.padding(it),
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