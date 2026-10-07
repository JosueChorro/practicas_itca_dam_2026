package com.itca.practica_semana_11_dsw21b.provider

import com.itca.practica_semana_11_dsw21b.data.models.PokemonList
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonInterface {

    @GET("pokemon")
    suspend fun getPokemon(

        @Query("limit")
        limits: Int =  20,

        @Query("offset")
        offset: Int = 0,
    ): PokemonList
}