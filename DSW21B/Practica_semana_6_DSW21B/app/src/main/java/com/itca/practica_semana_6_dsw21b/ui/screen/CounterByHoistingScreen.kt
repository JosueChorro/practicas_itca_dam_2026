package com.itca.practica_semana_6_dsw21b.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CounterByHoistingScreen (
    onBackClick: () -> Unit
){
    var counter by remember { mutableIntStateOf(0) }

    CounterSection(
        count = counter,
        onDecrement = {
            counter--
        },
        onIncrement = {
            counter++
        },
        onReset = {
            counter = 0
        }
    )
}

@Composable
fun CounterSection(
    count: Int,
    onDecrement: () -> Unit,
    onIncrement: () -> Unit,
    onReset: () -> Unit,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Contador",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row{
            Button(
                onClick = onDecrement
            ) {
                Text("-")
            }

            Text(
                text = " $count ",
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )

            Button(
                onClick = onIncrement
            ) {
                Text("+")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = onReset
        ) {
            Text("Reiniciar contador")
        }
    }
}