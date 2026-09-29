package com.itca.practica_semana_9_dsw21a.services

import com.itca.practica_semana_9_dsw21a.data.models.Post
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/:id")
    suspend fun getPost() : Post
}