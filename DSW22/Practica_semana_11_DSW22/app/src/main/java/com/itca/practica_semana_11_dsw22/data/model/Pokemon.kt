package com.itca.practica_semana_11_dsw22.data.model

data class Pokemon(
    val name: String,
    val url: String
){
    val id: Int
        get() = url
            .trimEnd('/')
            .substringAfterLast('/')
            .toIntOrNull() ?: 0

    val imageUrl: String
        get() = "https://raw.githubusercontent.com/" +
                "PokeAPI/sprites/master/sprites/pokemon" +
                "other/official-artwork/$id.png"
}
