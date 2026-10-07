package com.itca.practica_semana_11_dsw21b.ui.state

import com.itca.practica_semana_11_dsw21b.data.models.Pokemon

sealed interface PokemonUiState {

    data object Loading : PokemonUiState

    data class Success(
        val pokemon: List<Pokemon>
    ) : PokemonUiState

    data class Error(
        val message: String
    ): PokemonUiState
}