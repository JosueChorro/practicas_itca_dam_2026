package com.itca.practica_semana_11_dsw21b.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itca.practica_semana_11_dsw21b.data.models.Pokemon

@Composable
fun ListPokemon(
    pokemon: List<Pokemon>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(
            items = pokemon,
            key = {
                it.id
            }
        ) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}