package com.techdroidcentre.todo.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.techdroidcentre.todo.ui.tasks.TasksScreen
import com.techdroidcentre.todo.ui.todos.ToDoListScreen

@ExperimentalMaterial3Api
@Composable
fun ToDoNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.ToDoListScreen.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.ToDoListScreen.route,
            arguments = listOf(
                navArgument(COLOUR_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            ToDoListScreen(navController = navController)
        }

        composable(
            route = Screen.TasksScreen.route,
            arguments = listOf(
                navArgument(TODOLIST_ID_KEY) {
                    type = NavType.LongType
                },
                navArgument(COLOUR_KEY) {
                    type = NavType.IntType
                }
            )
        ) {
            TasksScreen()
        }
    }
}