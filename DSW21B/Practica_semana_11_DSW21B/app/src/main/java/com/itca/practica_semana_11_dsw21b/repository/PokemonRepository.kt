package com.itca.practica_semana_11_dsw21b.repository

import com.itca.practica_semana_11_dsw21b.data.models.Pokemon
import com.itca.practica_semana_11_dsw21b.provider.PokemonInterface

class PokemonRepository(
    private val api: PokemonInterface
) {

    suspend fun getPokemon(): List<Pokemon>{
        return api.getPokemon().results
    }
}