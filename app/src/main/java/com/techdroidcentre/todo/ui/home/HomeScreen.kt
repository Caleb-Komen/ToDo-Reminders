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
import com.techdroidcentre.todo.ui.components.*

@OptIn(ExperimentalLifecycleComposeApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onToDoClick: (Long, Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val toDoListViewState by viewModel.toDoListViewState
    val scheduledTasksState by viewModel.scheduledTasksState
    val scheduledTasksForTodayState by viewModel.scheduledTasksForTodayState
    val tabState by viewModel.tabState.collectAsStateWithLifecycle()
    var value by remember { mutableStateOf("") }
    var showNewListDialog by remember { mutableStateOf(false) }
    var displayToDoActionDialog by remember { mutableStateOf(false) }
    var todoId by remember { mutableStateOf(0L) }
    var todoTitle by remember { mutableStateOf("") }
    var showEditToDoTitleDialog by remember { mutableStateOf(false) }
    var newTodoTitle by remember { mutableStateOf("") }
    var showConfirmDeleteDialog by remember { mutableStateOf(false) }

    if (showNewListDialog) {
        NewToDoListDialog(
            value = value,
            onValueChange = { value = it },
            dismissDialog = { showNewListDialog = false }
        )
    }

    if (displayToDoActionDialog) {
        ToDoActionDialog(
            id = todoId,
            title = todoTitle,
            closeDialog = { displayToDoActionDialog = false },
            showEditTitleDialog = { _, title ->
                newTodoTitle = title
                showEditToDoTitleDialog = true
            },
            showConfirmDeleteDialog = {
                showConfirmDeleteDialog = true
            }
        )
    }

    if (showEditToDoTitleDialog) {
        EditToDoTitleDialog(
            id = todoId,
            title = newTodoTitle,
            onTitleChange = { newTodoTitle = it},
            dismissDialog = { showEditToDoTitleDialog = false },
            updateToDoList = viewModel::updateToDoList
        )
    }

    if (showConfirmDeleteDialog) {
        ConfirmDeleteDialog(
            id = todoId,
            dismissDialog = { showConfirmDeleteDialog = false },
            deleteToDo = viewModel::deleteToDoList
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
                scheduledTasksState = scheduledTasksState,
                scheduledTasksForTodayState = scheduledTasksForTodayState,
                onToDoClick = onToDoClick,
                onTabSelected = viewModel::switchTab,
                showDialog = { id, title ->
                    displayToDoActionDialog = true
                    todoId = id
                    todoTitle = title
                }
            )
        }
    }
}

@Composable
fun HomeContent(
    tabState: ToDoTabState,
    todoViewState: ToDoListViewState,
    scheduledTasksState: ScheduledTaskState,
    scheduledTasksForTodayState: ScheduledTasksForTodayState,
    onToDoClick: (Long, Int) -> Unit,
    onTabSelected: (Int) -> Unit,
    showDialog: (Long, String) -> Unit,
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
                    onClick = onToDoClick,
                    showDialog = showDialog
                )
            }
            1 -> {
                ScheduledTabContent(
                    state = scheduledTasksState
                )
            }
            2 -> {
                TodayTabContent(
                    state = scheduledTasksForTodayState
                )
            }
        }
    }
}