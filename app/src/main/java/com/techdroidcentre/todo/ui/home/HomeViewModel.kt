package com.techdroidcentre.todo.ui.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.domain.usecases.*
import com.techdroidcentre.todo.ui.COLOUR_KEY
import com.techdroidcentre.todo.ui.TODOLIST_ID_KEY
import com.techdroidcentre.todo.ui.util.Util
import com.techdroidcentre.todo.ui.util.defaultId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ToDoTabState(
    val titles: List<String>,
    val currentIndex: Int
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllToDoListUseCase: GetAllToDoListUseCase,
    private val addToDoListUseCase: AddToDoListUseCase,
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoListUseCase: UpdateToDoListUseCase,
    private val getScheduledTasksUseCase: GetScheduledTasksUseCase,
    private val getToDoTitleForTaskUseCase: GetToDoTitleForTaskUseCase,
    private val getScheduledTasksForTodayUseCase: GetScheduledTasksForTodayUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _toDoListViewState = mutableStateOf(ToDoListViewState())
    val toDoListViewState: State<ToDoListViewState> = _toDoListViewState

    private val _scheduledTasksState = mutableStateOf(ScheduledTaskState())
    val scheduledTasksState: State<ScheduledTaskState> = _scheduledTasksState

    private val _scheduledTasksForTodayState = mutableStateOf(ScheduledTasksForTodayState())
    val scheduledTasksForTodayState: State<ScheduledTasksForTodayState> = _scheduledTasksForTodayState

    private val todoListId = savedStateHandle.get<Long>(TODOLIST_ID_KEY) ?: defaultId
    val colour = savedStateHandle.get<Int>(COLOUR_KEY)?.also { colour ->
        viewModelScope.launch {
            getToDoListUseCase(todoListId).collect { todoList ->
                val todo = todoList ?: return@collect
                if (todo.colour != colour) {
                    updateToDoListUseCase(todo.copy(colour = colour))
                }
            }
        }

    } ?: 0

    private val _tabState = MutableStateFlow(
        ToDoTabState(
            titles = listOf("Lists", "Scheduled", "Today"),
            currentIndex = 0
        )
    )
    val tabState: StateFlow<ToDoTabState> = _tabState.asStateFlow()

    init {
        getAllToDoList()
        fetchScheduledTasks()
        fetchScheduledTasksForToday()
    }

    private fun getAllToDoList() {
        _toDoListViewState.value = _toDoListViewState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            getAllToDoListUseCase().collect {
                _toDoListViewState.value = _toDoListViewState.value.copy(
                    todos = it.map { toDoList -> toDoList.toViewState() },
                    isLoading = false
                )
            }
        }
    }

    private fun fetchScheduledTasks() {
        _scheduledTasksState.value = _scheduledTasksState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            _scheduledTasksState.value = _scheduledTasksState.value.copy(
                isLoading = true
            )

            getScheduledTasksUseCase().collect {
                val tasksState = it.map { task ->
                    val todoTitle = getToDoTitleForTaskUseCase(task.id) ?: "Unknown"
                    task.toViewState(todoTitle)
                }
                val tasks = tasksState.groupBy { task ->
                    Util.toDateString(task.dueDate)
                }
                _scheduledTasksState.value = _scheduledTasksState.value.copy(
                    tasks = tasks,
                    isLoading = false
                )
            }
        }
    }

    private fun fetchScheduledTasksForToday() {
        _scheduledTasksForTodayState.value = _scheduledTasksForTodayState.value.copy(
            isLoading = true
        )
        viewModelScope.launch {
            getScheduledTasksForTodayUseCase().collect {
                val tasks = it.map { task ->
                    val todoTitle = getToDoTitleForTaskUseCase(task.id) ?: "Unknown"
                    task.toViewState(todoTitle)
                }
                _scheduledTasksForTodayState.value = _scheduledTasksForTodayState.value.copy(
                    tasks = tasks,
                    isLoading = false
                )
            }
        }
    }

    fun addToDoList(todoList: ToDoList) {
        viewModelScope.launch {
            addToDoListUseCase(todoList)
        }
    }

    fun switchTab(newIndex: Int) {
        if (newIndex != tabState.value.currentIndex) {
            _tabState.update {
                it.copy(currentIndex = newIndex)
            }
        }
    }
}