package com.techdroidcentre.todo.ui.tasks

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techdroidcentre.todo.data.model.Task
import com.techdroidcentre.todo.domain.usecases.AddTaskUseCase
import com.techdroidcentre.todo.domain.usecases.GetTasksUseCase
import com.techdroidcentre.todo.ui.COLOUR_KEY
import com.techdroidcentre.todo.ui.TODOLIST_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TasksViewModel @Inject constructor(
    private val getTasksUseCase: GetTasksUseCase,
    private val addTaskUseCase: AddTaskUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = mutableStateOf(TasksUIState())
    val uiState: State<TasksUIState> = _uiState

    private val todoListId = savedStateHandle.get<Long>(TODOLIST_ID_KEY) ?: 0L
    private val colour = savedStateHandle.get<Int>(COLOUR_KEY) ?: 0

    fun retrieveTasks(todoListId: Long) {
        _uiState.value = _uiState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            getTasksUseCase(todoListId).collect { tasks ->
                _uiState.value = _uiState.value.copy(
                    tasks = tasks.map { it.toViewState() },
                    isLoading = false
                )
            }
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            addTaskUseCase(todoListId, task)
        }
    }

}