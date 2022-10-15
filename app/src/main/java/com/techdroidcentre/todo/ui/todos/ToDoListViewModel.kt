package com.techdroidcentre.todo.ui.todos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techdroidcentre.todo.data.model.ToDoList
import com.techdroidcentre.todo.domain.usecases.AddToDoListUseCase
import com.techdroidcentre.todo.domain.usecases.GetAllToDoListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ToDoListViewModel @Inject constructor(
    private val getAllToDoListUseCase: GetAllToDoListUseCase,
    private val addToDoListUseCase: AddToDoListUseCase,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _toDoListViewState = mutableStateOf(ToDoListViewState())
    val toDoListViewState: State<ToDoListViewState> = _toDoListViewState

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