package com.itca.practica_semana_10.provider

import retrofit2.http.GET

interface PokeService {

    @GET("pokemon/pikachu")
    suspend fun getPikachuInfo()
}