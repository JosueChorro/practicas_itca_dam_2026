package com.itca.practica_semana_11_dsw21a.data.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.itca.practica_semana_11_dsw21a.provider.RetrofitClient
import com.itca.practica_semana_11_dsw21a.repository.PokemonRepository
import com.itca.practica_semana_11_dsw21a.ui.state.PokemonUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonViewmodel : ViewModel(){

    private val repository = PokemonRepository(RetrofitClient.api)
    private val _uiState = MutableStateFlow<PokemonUIState>(
        value = PokemonUIState.Loading
    )

    val uiState: StateFlow<PokemonUIState> = _uiState.asStateFlow()

    init {
        loadPokemon()
    }

    fun loadPokemon(){
        viewModelScope.launch {
            _uiState.value = PokemonUIState.Loading

            try {
                val pokemon = repository.getPokemon()

                _uiState.value = PokemonUIState.Success(
                    pokemon =  pokemon
                )

            }catch (e: Exception){
                _uiState.value = PokemonUIState.Error(
                    message = e.message ?: "No se pudo cargar la información"
                )
            }
        }
    }

}