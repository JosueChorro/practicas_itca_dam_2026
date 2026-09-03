package com.itca.practica_semana_6_dsw21b.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen (
    onExerciseClick: () -> Unit,
){

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenidas y Bienvenidos",
            fontWeight = FontWeight.Bold,
            fontSize = 32.sp
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = onExerciseClick
        ) {
            Text(
                text = "Continuar",
                fontWeight = FontWeight.Normal,
                fontSize = 14.sp
            )
        }
    }

}