package com.itca.practica_semana_6_dsw21b.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel : ViewModel() {

    private val _count = MutableStateFlow(0)

    val count: StateFlow<Int> = _count.asStateFlow()

    fun increment(){
        _count.update { it + 1 }
    }

    fun decrement(){
        _count.update { it - 1 }
    }

    fun reset(){
        _count.update { 0 }
    }

    fun operations(text: String){
        if (text == "sum"){
            _count.update { it + 1 }
        }else if (text == "rest"){
            _count.update { it - 1 }
        }
    }
}