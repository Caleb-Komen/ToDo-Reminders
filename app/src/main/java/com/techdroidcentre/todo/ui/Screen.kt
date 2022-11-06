package com.techdroidcentre.todo.ui

import com.techdroidcentre.todo.ui.Screen.*

const val TODOLIST_SCREEN_ROUTE = "todo_list"
const val SCHEDULED_TASKS_SCREEN_ROUTE = "scheduled_tasks"
const val TODAY_TASKS_SCREEN_ROUTE = "today_tasks"
const val TASKS_SCREEN_ROUTE = "tasks"
const val COLOUR_KEY = "colour"
const val TODOLIST_ID_KEY = "todolist_id"

sealed class Screen(val route: String, val title: String) {
    object HomeScreen: Screen("home", "Home")
    object ToDoListScreen: Screen("$TODOLIST_SCREEN_ROUTE?id={$TODOLIST_ID_KEY}&colour={$COLOUR_KEY}", "Lists") {
        // helper method for passing arguments
        fun passIdAndColour(id: Long, colour: Int): String {
            return "$TODOLIST_SCREEN_ROUTE?id=$id&colour=$colour"
        }
    }
    object ScheduledTasksScreen: Screen(SCHEDULED_TASKS_SCREEN_ROUTE, "Scheduled")
    object TodayTasksScreen: Screen(TODAY_TASKS_SCREEN_ROUTE, "Today")
    object TasksScreen: Screen("$TASKS_SCREEN_ROUTE/{$TODOLIST_ID_KEY}/{$COLOUR_KEY}", "Tasks") {
        // helper method for passing arguments
        fun passTodoListIdAndColour(todoListId: Long, colour: Int): String {
            return "$TASKS_SCREEN_ROUTE/$todoListId/$colour"
        }
    }
}

val screens = listOf(ToDoListScreen, ScheduledTasksScreen, TodayTasksScreen)