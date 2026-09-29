package com.itca.practica_semana_9_dsw21b.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itca.practica_semana_9_dsw21b.data.models.Post
import com.itca.practica_semana_9_dsw21b.utils.RetrofitClient
import kotlinx.coroutines.launch

class PostViewModel : ViewModel() {

    var posts by mutableStateOf<List<Post>>(emptyList())

    var isLoading by mutableStateOf(false)

    var errorMessage by mutableStateOf<String?>(null)

    fun loadPosts(){
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                posts = RetrofitClient.api.getPost()

            }catch (e: Exception){
                errorMessage = "No se pudieron cargar las publicaciones"
            } finally {
                isLoading = false
            }
        }
    }
}