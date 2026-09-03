package com.itca.practica_semana6_dsw22.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itca.practica_semana6_dsw22.ui.screen.CounterByHoistingScreen
import com.itca.practica_semana6_dsw22.ui.screen.CounterByViewModelScreen
import com.itca.practica_semana6_dsw22.ui.screen.ExerciseListScreen
import com.itca.practica_semana6_dsw22.ui.screen.HomeScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable ("home"){
            HomeScreen(
                onExerciseListClick = {
                    navController.navigate("exercise")
                }
            )
        }

        composable ("exercise"){
            ExerciseListScreen(
                onExample1Click = {
                    navController.navigate("counter-hoisting")
                },
                onExample2Click = {
                    navController.navigate("counter-viewmodel")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable ("counter-hoisting"){
            CounterByHoistingScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }

        composable("counter-viewmodel") {
            CounterByViewModelScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}