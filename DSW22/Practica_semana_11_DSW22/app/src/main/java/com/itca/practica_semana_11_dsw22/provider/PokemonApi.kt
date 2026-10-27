package com.itca.practica_semana_11_dsw22.provider

import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon")
    suspend fun getPokemon(
        @Query("limit")
        limits: Int = 20,

        @Query("offset")
        offset: Int = 0
    )
}