package com.techdroidcentre.todo.ui

const val TODOLIST_SCREEN_ROUTE = "todo_list"
const val TASKS_SCREEN_ROUTE = "tasks"
const val COLOUR_KEY = "colour"
const val TODOLIST_ID_KEY = "todolist_id"

sealed class Screen(val route: String) {
    object ToDoListScreen: Screen("$TODOLIST_SCREEN_ROUTE/{$TODOLIST_ID_KEY}/{$COLOUR_KEY}") {
        fun passIdAndColour(id: Long, colour: Int): String {
            return "$TODOLIST_SCREEN_ROUTE/$id/$colour"
        }
    }
    object TasksScreen: Screen("$TASKS_SCREEN_ROUTE/{$TODOLIST_ID_KEY}/{$COLOUR_KEY}") {
        fun passTodoListIdAndColour(todoListId: Long, colour: Int): String {
            return "$TASKS_SCREEN_ROUTE/$todoListId/$colour"
        }
    }
}