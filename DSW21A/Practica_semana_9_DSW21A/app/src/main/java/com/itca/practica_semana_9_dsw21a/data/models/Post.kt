package com.itca.practica_semana_9_dsw21a.data.models

import java.io.Serializable

data class Post (
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Serializable