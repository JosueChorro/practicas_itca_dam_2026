package com.itca.practica_semana_9_dsw22.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itca.practica_semana_9_dsw22.data.models.Post
import com.itca.practica_semana_9_dsw22.provider.RetrofitClient
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    var posts by mutableStateOf<List<Post>>(emptyList())
        private set

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set


    fun loadPost(){
        viewModelScope.launch {
            try {
                isLoading = true
                errorMessage = null

                posts = RetrofitClient.api.getPost()

            }catch (e: Exception){
                errorMessage = "No se pudo cargar las publicaciones"
            } finally {
                isLoading = false
            }
        }
    }

}