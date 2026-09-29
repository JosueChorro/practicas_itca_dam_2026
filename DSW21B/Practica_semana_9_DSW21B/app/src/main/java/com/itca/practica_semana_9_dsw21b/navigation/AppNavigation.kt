package com.itca.practica_semana_9_dsw21b.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itca.practica_semana_9_dsw21b.ui.screens.PostListScreen
import com.itca.practica_semana_9_dsw21b.viewmodel.PostViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val postViewModel: PostViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "posts"
    ){
        composable ("posts"){
            PostListScreen(
                viewModel = postViewModel,
                onPostClick = { post ->
                    navController.navigate("")
                }
            )
        }
    }
}