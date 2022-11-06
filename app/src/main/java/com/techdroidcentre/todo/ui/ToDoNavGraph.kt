package com.techdroidcentre.todo.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.techdroidcentre.todo.ui.home.HomeScreen
import com.techdroidcentre.todo.ui.tasks.TasksScreen
import com.techdroidcentre.todo.ui.util.defaultColour
import com.techdroidcentre.todo.ui.util.defaultId

@ExperimentalMaterial3Api
@Composable
fun ToDoNavGraph(
    navController: NavHostController,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route,
        modifier = modifier
    ) {
        composable(
            route = Screen.HomeScreen.route,
            arguments = listOf(
                navArgument(TODOLIST_ID_KEY) {
                    type = NavType.LongType
                    defaultValue = defaultId
                },
                navArgument(COLOUR_KEY) {
                    type = NavType.IntType
                    defaultValue = defaultColour
                }
            )
        ) {
            HomeScreen(
                onToDoClick = { id, colour ->
                    navController.navigate(
                        Screen.TasksScreen.passTodoListIdAndColour(id, colour)
                    )
                }
            )
        }

//        composable(
//            route = Screen.ToDoListScreen.route,
//            arguments = listOf(
//                navArgument(TODOLIST_ID_KEY) {
//                    type = NavType.LongType
//                    defaultValue = defaultId
//                },
//                navArgument(COLOUR_KEY) {
//                    type = NavType.IntType
//                    defaultValue = defaultColour
//                }
//            )
//        ) {
//            ToDoListScreen(
//                onClick = { id, colour ->
//                    navController.navigate(
//                        Screen.TasksScreen.passTodoListIdAndColour(id, colour)
//                    )
//                }
//            )
//        }
//
//        composable(route = Screen.ScheduledTasksScreen.route) {
//            ScheduledTasksScreen()
//        }
//
//        composable(route = Screen.TodayTasksScreen.route) {
//            TodayTasksScreen()
//        }

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
            TasksScreen(navController = navController, snackbarHostState = snackbarHostState)
        }
    }
}

fun NavHostController.navigateSingleTopTo(route: String) {
    this.navigate(route) {
        launchSingleTop = true
        popUpTo(this@navigateSingleTopTo.graph.findStartDestination().id) {
            saveState = true
        }
        restoreState = true
    }
}