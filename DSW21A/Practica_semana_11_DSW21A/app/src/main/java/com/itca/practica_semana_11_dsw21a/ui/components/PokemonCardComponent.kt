package com.itca.practica_semana_11_dsw21a.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.itca.practica_semana_11_dsw21a.data.model.Pokemon

@Composable
fun PokemonCardComponent (
    pokemon: Pokemon
){
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "#${pokemon.id} " +
                    pokemon.name.replaceFirstChar { it.uppercase() },
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        PokemonImageComponent(pokemon)
    }
}