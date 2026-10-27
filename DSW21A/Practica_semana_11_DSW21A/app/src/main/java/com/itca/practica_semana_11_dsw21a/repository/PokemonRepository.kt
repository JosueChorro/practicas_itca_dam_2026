package com.itca.practica_semana_11_dsw21a.repository

import com.itca.practica_semana_11_dsw21a.data.model.Pokemon
import com.itca.practica_semana_11_dsw21a.provider.PokemonApi

class PokemonRepository(
    private val api: PokemonApi
) {
    suspend fun getPokemon(): List<Pokemon>{
        return api.getPokemon().results
    }
}