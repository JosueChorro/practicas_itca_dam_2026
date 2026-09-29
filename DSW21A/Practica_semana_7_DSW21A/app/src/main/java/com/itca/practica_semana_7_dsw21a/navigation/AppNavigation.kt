package com.itca.practica_semana_7_dsw21a.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itca.practica_semana_7_dsw21a.ui.screens.HomeScreen
import com.itca.practica_semana_7_dsw21a.ui.screens.SensorExampleScreen

@Composable
fun AppNavigation (){

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home"){
            HomeScreen(
                onSensorExampleClick = {
                    navController.navigate("sensor")
                }
            )
        }

        composable("sensor"){
            SensorExampleScreen(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}