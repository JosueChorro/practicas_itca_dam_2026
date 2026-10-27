package com.itca.practica_semana_11_dsw21a.provider

import com.itca.practica_semana_11_dsw21a.data.model.Pokemon
import com.itca.practica_semana_11_dsw21a.data.model.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit")
        limits: Int =  20,

        @Query("offset")
        offset: Int =  0,
    ): PokemonList
}