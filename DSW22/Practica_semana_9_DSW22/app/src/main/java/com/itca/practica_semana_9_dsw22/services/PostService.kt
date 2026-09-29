package com.itca.practica_semana_9_dsw22.services

import com.itca.practica_semana_9_dsw22.data.models.Post
import retrofit2.http.GET
import retrofit2.http.POST

interface PostService {

    @GET("posts")
    suspend fun getPost() : List<Post>

    @POST("posts")
    suspend fun createPost() : Post
}