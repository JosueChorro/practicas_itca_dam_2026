package com.itca.practica_semana_13_dsw21a.data.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val category: String,
    val isCompleted: Boolean = false
)
