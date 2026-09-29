package com.itca.test_semana7.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.itca.test_semana7.ui.screens.TaskFormScreen
import com.itca.test_semana7.ui.screens.TaskListScreen
import com.itca.test_semana7.ui.screens.WelcomeScreen
import com.itca.test_semana7.viewmodel.TaskViewModel

@Composable
fun AppNavigation(
    viewModel: TaskViewModel
) {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "welcome"
    ) {

        composable("welcome") {

            WelcomeScreen(
                onStart = {
                    navController.navigate("tasks")
                }
            )
        }

        composable("tasks") {

            TaskListScreen(
                viewModel = viewModel,

                onAddTask = {
                    navController.navigate("task_form")
                },

                onEditTask = { taskId ->

                    navController.navigate(
                        "task_form/$taskId"
                    )
                }
            )
        }

        composable(
            route = "task_form/{taskId}",
            arguments = listOf(
                navArgument("taskId") {
                    type = NavType.IntType
                }
            )
        ) { backStackEntry ->

            val taskId =
                backStackEntry.arguments?.getInt("taskId")

            TaskFormScreen(
                viewModel = viewModel,
                taskId = taskId,

                onBack = {
                    navController.popBackStack()
                }
            )
        }

        composable("task_form") {

            TaskFormScreen(
                viewModel = viewModel,
                taskId = null,

                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}