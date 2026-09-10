package com.itca.practica_semana_6_dsw21a.ui.screen

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseListScreen (
    onExample1Click: () -> Unit,
    onExample2Click: () -> Unit,
    onBackClick: () -> Unit
){
    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de ejercicios",
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
        )

        Text(
            text = "Gestión de estados",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 32.sp,
        )

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onExample1Click
        ) {
            Text(
                text = "State Hoisting",
                fontSize = 16.sp
            )
        }
        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = onExample2Click
        ) {
            Text(
                text = "ViewModel",
                fontSize = 16.sp
            )
        }
    }
}