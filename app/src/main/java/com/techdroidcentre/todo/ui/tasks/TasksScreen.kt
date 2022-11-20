package com.techdroidcentre.todo.ui.tasks

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.data.util.Priority
import com.techdroidcentre.todo.ui.Screen
import com.techdroidcentre.todo.ui.components.ColourPicker
import com.techdroidcentre.todo.ui.components.TasksTopBar
import com.techdroidcentre.todo.ui.theme.ToDoTheme
import com.techdroidcentre.todo.ui.util.colours
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun TasksScreen(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    val viewModel: TasksViewModel = hiltViewModel()
    var showColourPickerDialog by remember { mutableStateOf(false) }
    var isBackPressed by remember { mutableStateOf(false) }

    if (showColourPickerDialog) {
        ColourPicker(
            colours = colours.map { it.toArgb() },
            closeDialog = {
                showColourPickerDialog = false
            },
            viewModel = viewModel
        )
    }

    Scaffold(
        topBar = {
            TasksTopBar {
                showColourPickerDialog = true
            }
        },
        snackbarHost = {
            SnackbarHost(hostState =snackbarHostState)
        }
    ) {
        TasksScreen(
            snackbarHostState = snackbarHostState,
            viewModel = viewModel,
            isBackPressed = isBackPressed,
            modifier = modifier.padding(it)
        )
    }
    BackHandler {
        isBackPressed = true
        navController.navigate(
            Screen.HomeScreen.passIdAndColour(
                viewModel.todoListId,
                viewModel.colour
            )
        ) {
            popUpTo(navController.graph.startDestinationId){
                inclusive = true
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun TasksScreen(
    snackbarHostState: SnackbarHostState,
    viewModel: TasksViewModel,
    isBackPressed: Boolean,
    modifier: Modifier = Modifier
) {
    val tasks = viewModel.uiState.value.tasks
    val colour = viewModel.uiState.value.colour
    val scope = rememberCoroutineScope()

    LazyColumn(
        modifier = modifier
            .background(Color(colour))
            .fillMaxSize(),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = tasks, key = { task -> task.id }) { task ->
            TaskItem(
                task = task,
                isBackPressed = isBackPressed,
                saveTask = { viewModel.addTask(it) },
                deleteTask = {
                    viewModel.deleteTask(it.id)
                    scope.launch {
                        val result = snackbarHostState.showSnackbar(
                            message = "Task removed",
                            actionLabel ="Undo",
                            duration = SnackbarDuration.Long
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.addTask(it)
                        }
                    }
                }
            )
        }
        item {
            NewTaskItem(
                value = viewModel.taskTitle,
                onValueChange = {
                    viewModel.updateTitle(it)
                },
                onDone = {
                    viewModel.addTask(Task(0L,viewModel.taskTitle, "", 0L, Priority.NONE, false))
                    viewModel.updateTitle("")
                }
            )
        }
    }
}

@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun TasksScreenPreview() {
    ToDoTheme {
        TasksScreen(rememberNavController(), SnackbarHostState())
    }
}