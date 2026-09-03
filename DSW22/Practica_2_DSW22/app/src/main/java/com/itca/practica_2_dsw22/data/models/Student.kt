package com.itca.practica_2_dsw22.data.models

data class Student(
    val id: Int,
    val name: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val career: String,
    val hasLicense: Boolean?
)
