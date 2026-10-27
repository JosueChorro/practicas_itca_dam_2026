package com.itca.practica_semana_11_dsw21a.ui.state

import com.itca.practica_semana_11_dsw21a.data.model.Pokemon

sealed interface PokemonUIState {
    data object Loading: PokemonUIState

    data class Success(
        val pokemon: List<Pokemon>
    ): PokemonUIState

    data class Error(
        val message: String
    ): PokemonUIState
}