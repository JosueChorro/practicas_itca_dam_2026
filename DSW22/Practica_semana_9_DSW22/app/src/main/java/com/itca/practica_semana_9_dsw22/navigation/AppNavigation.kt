package com.itca.practica_semana_9_dsw22.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.itca.practica_semana_9_dsw22.data.models.Post
import com.itca.practica_semana_9_dsw22.ui.screens.PostDetailScreen
import com.itca.practica_semana_9_dsw22.ui.screens.PostListScreen
import com.itca.practica_semana_9_dsw22.viewmodel.PostViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val viewModel: PostViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = "posts"
    ){
        composable ("posts"){
            PostListScreen(
                viewModel = viewModel,
                onPostClick = { post ->
                    navController.currentBackStackEntry
                        ?.savedStateHandle
                        ?.set("post",post)

                    navController.navigate("detail")
                }
            )
        }

        composable ("detail"){
            val post = navController.previousBackStackEntry
                ?.savedStateHandle
                ?.get<Post>("post")

            if (post != null){
                PostDetailScreen(post = post)
            }
        }
    }
}