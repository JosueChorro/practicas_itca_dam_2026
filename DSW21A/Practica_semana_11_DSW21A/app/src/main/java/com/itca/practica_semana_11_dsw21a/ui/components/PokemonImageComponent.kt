package com.itca.practica_semana_11_dsw21a.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.itca.practica_semana_11_dsw21a.data.model.Pokemon

@Composable
fun PokemonImageComponent (
    pokemon: Pokemon
){

    SubcomposeAsyncImage(
        model = pokemon.imageUrl,
        contentDescription = "Imagen de ${pokemon.name}",
        modifier = Modifier
            .fillMaxWidth()
            .size(220.dp)
            .padding(12.dp)
            .clip(
                MaterialTheme.shapes.medium
            ),
        contentScale = ContentScale.Fit
    ) {
        when (painter.state) {
            is AsyncImagePainter.State.Error -> {
                ImageError()
            }
            is AsyncImagePainter.State.Loading -> {
                ImagePlaceholder()
            }
            else -> {
                SubcomposeAsyncImageContent()
            }
        }
    }
}

@Composable
private fun ImagePlaceholder(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(220.dp)
            .background(
                MaterialTheme.colorScheme.surfaceVariant
            ),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
private fun ImageError(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(220.dp)
            .background(
                MaterialTheme.colorScheme.errorContainer
            ),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "No se pudo cargar la imagen",
            color = MaterialTheme.colorScheme.onErrorContainer
        )
    }
}