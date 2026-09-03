package com.itca.practica_semana6_dsw22.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itca.practica_semana6_dsw22.viewmodel.CounterViewModel

@Composable
fun CounterByViewModelScreen(
    viewModel: CounterViewModel = viewModel(),
    onBackClick: () -> Unit
) {

    val count by viewModel.count.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Counter - ViewModel",
            textAlign = TextAlign.Center,
            color = Color.Red,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { viewModel.decrement() }
            ) {
                Text("-")
            }

            Text(
                text = count.toString(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Normal,
                fontSize = 48.sp
            )

            Button(
                onClick = { viewModel.increment() }
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = { viewModel.reset() }
        ) {
            Text("Reiniciar")
        }
    }
}