package com.itca.practica_semana_11_dsw21b.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itca.practica_semana_11_dsw21b.provider.RetrofitClient
import com.itca.practica_semana_11_dsw21b.repository.PokemonRepository
import com.itca.practica_semana_11_dsw21b.ui.state.PokemonUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewModel : ViewModel(){

    private val repository = PokemonRepository(RetrofitClient.api)
    private val _uiState = MutableStateFlow<PokemonUiState>(
        value = PokemonUiState.Loading
    )

    val uiState: StateFlow<PokemonUiState> = _uiState.asStateFlow()


    init {
        loadPokemon()
    }

    fun loadPokemon(){
        viewModelScope.launch {
            _uiState.value = PokemonUiState.Loading

            try {
                val pokemon = repository.getPokemon()

                _uiState.value = PokemonUiState.Success(
                    pokemon = pokemon
                )

            }catch (e: Exception){
                _uiState.value = PokemonUiState.Error(
                    message = e.message ?: "No se pudo cargar la información"
                )
            }
        }
    }

}