package com.itca.practica_semana_9_dsw21a.ui.screens

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
import com.itca.practica_semana_9_dsw21a.data.models.Post
import com.itca.practica_semana_9_dsw21a.viewmodels.PostViewModel

@Composable
fun PostListScreen (
    viewModel: PostViewModel,
    onPostClick: (Post) -> Unit
){
    val posts = viewModel.posts
    val isLoading = viewModel.isLoading
    val msj = viewModel.errorMessage

    LaunchedEffect(Unit) {
        viewModel.loadPosts()
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


        msj != null -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = msj
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
            ) {
                items (posts) { x ->
                    PostItemCard(
                        post = x,
                        onClick = { onPostClick(x) }
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
                onClick()
            },
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = "Publicación #${post.id}",
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