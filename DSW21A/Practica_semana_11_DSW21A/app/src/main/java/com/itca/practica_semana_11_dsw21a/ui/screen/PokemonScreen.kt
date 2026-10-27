package com.itca.practica_semana_11_dsw21a.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itca.practica_semana_11_dsw21a.data.viewmodel.PokemonViewmodel
import com.itca.practica_semana_11_dsw21a.ui.components.ListPokemonComponent
import com.itca.practica_semana_11_dsw21a.ui.state.PokemonUIState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonScreen (
    viewModel: PokemonViewmodel = viewModel()
){
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Pokemon App", fontWeight = FontWeight.Bold)
                }
            )
        }
    ) { paddingValues ->
        when(val state = uiState){
            PokemonUIState.Loading -> {
                LoadingContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is PokemonUIState.Error -> {
                ErrorContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    message = state.message,
                    onRetry = viewModel::loadPokemon
                )
            }

            is PokemonUIState.Success -> {
                ListPokemonComponent(
                    pokemon = state.pokemon,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}


@Composable
fun LoadingContent(
    modifier: Modifier = Modifier
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CircularProgressIndicator()
            Text(text = "Cargando lista de pokemon")
        }
    }
}

@Composable
fun ErrorContent(
    modifier: Modifier = Modifier,
    message: String,
    onRetry: () -> Unit
){
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = "Ocurrio un problema",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Text(text = message)
            Button(
                onClick = onRetry
            ) {
                Text("Reintentar")
            }
        }
    }
}