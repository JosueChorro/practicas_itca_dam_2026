package com.itca.practica_semana_9_dsw21b.services

import com.itca.practica_semana_9_dsw21b.data.models.Post
import retrofit2.http.GET

interface PostService {

    @GET("posts")
    suspend fun getPost() : List<Post>

}