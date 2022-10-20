package com.techdroidcentre.todo.ui.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.domain.usecases.AddToDoListUseCase
import com.techdroidcentre.todo.domain.usecases.GetAllToDoListUseCase
import com.techdroidcentre.todo.domain.usecases.GetToDoListUseCase
import com.techdroidcentre.todo.domain.usecases.UpdateToDoListUseCase
import com.techdroidcentre.todo.ui.COLOUR_KEY
import com.techdroidcentre.todo.ui.TODOLIST_ID_KEY
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val getAllToDoListUseCase: GetAllToDoListUseCase,
    private val addToDoListUseCase: AddToDoListUseCase,
    private val getToDoListUseCase: GetToDoListUseCase,
    private val updateToDoListUseCase: UpdateToDoListUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _toDoListViewState = mutableStateOf(ToDoListViewState())
    val toDoListViewState: State<ToDoListViewState> = _toDoListViewState

    private val todoListId = savedStateHandle.get<Long>(TODOLIST_ID_KEY) ?: 0L
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

    init {
        getAllToDoList()
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

    fun addToDoList(todoList: ToDoList) {
        viewModelScope.launch {
            addToDoListUseCase(todoList)
        }
    }
}