package com.techdroidcentre.todo.ui

const val HOME_SCREEN_ROUTE = "home"
const val TASKS_SCREEN_ROUTE = "tasks"
const val COLOUR_KEY = "colour"
const val TODOLIST_ID_KEY = "todolist_id"

sealed class Screen(val route: String) {
    object HomeScreen: Screen("$HOME_SCREEN_ROUTE?id={$TODOLIST_ID_KEY}&colour={$COLOUR_KEY}") {
        fun passIdAndColour(id: Long, colour: Int): String {
            return "$HOME_SCREEN_ROUTE?id=$id&colour=$colour"
        }
    }
    object TasksScreen: Screen("$TASKS_SCREEN_ROUTE/{$TODOLIST_ID_KEY}/{$COLOUR_KEY}") {
        // helper method for passing arguments
        fun passTodoListIdAndColour(todoListId: Long, colour: Int): String {
            return "$TASKS_SCREEN_ROUTE/$todoListId/$colour"
        }
    }
}
