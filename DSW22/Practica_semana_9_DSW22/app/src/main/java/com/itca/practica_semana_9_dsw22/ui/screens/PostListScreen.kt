package com.itca.practica_semana_9_dsw22.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itca.practica_semana_9_dsw22.data.models.Post
import com.itca.practica_semana_9_dsw22.viewmodel.PostViewModel

@Composable
fun PostListScreen(
    viewModel: PostViewModel,
    onPostClick: (Post) -> Unit
) {

    val posts = viewModel.posts
    val isLoading = viewModel.isLoading
    val messageError = viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.loadPost()
    }

    when {
        isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }

        messageError != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = messageError
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(posts){ obj ->
                    PostItemCard(
                        post = obj,
                        onClick = {
                            onPostClick(obj)
                        }
                    )

                }
            }
        }
    }
}


@Composable
fun PostItemCard(
    post: Post,
    onClick: () -> Unit
){
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
            .clickable {
                onClick
            }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                    text = "Publicación ${post.id}",
            style = MaterialTheme.typography.labelMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = post.title,
                style = MaterialTheme.typography.titleMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = post.body,
                style = MaterialTheme.typography.labelSmall,
                maxLines = 2
            )
        }
    }
}