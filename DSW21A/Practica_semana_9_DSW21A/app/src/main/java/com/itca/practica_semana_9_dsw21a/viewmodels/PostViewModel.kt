package com.itca.practica_semana_9_dsw21a.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itca.practica_semana_9_dsw21a.data.models.Post
import com.itca.practica_semana_9_dsw21a.providers.RetrofitClient
import kotlinx.coroutines.launch

class PostViewModel : ViewModel(){

    var posts by mutableStateOf<List<Post>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    fun loadPosts(){
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                posts = RetrofitClient.api.getPosts()

            }catch (e: Exception){
                errorMessage = "No se pudieron cargar las publicaciones"
            } finally {
                isLoading = false
            }
        }
    }

}