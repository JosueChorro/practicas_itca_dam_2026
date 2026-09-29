package com.itca.test_semana7.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun WelcomeScreen(
    onStart: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "ITCA",
            fontSize = 70.sp
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Text(
            text = "TaskManager",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Text(
            text = "Organiza tus tareas de forma sencilla"
        )

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Button(
            onClick = onStart,
            modifier = Modifier.fillMaxWidth()
        ) {

            Text("Comenzar")
        }
    }
}