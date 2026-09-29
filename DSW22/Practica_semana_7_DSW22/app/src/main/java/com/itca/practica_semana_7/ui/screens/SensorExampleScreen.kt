package com.itca.practica_semana_7.ui.screens

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import kotlin.math.sqrt

@Composable
fun SensorExampleScreen(
    onBackClick: () -> Unit
){
    //Variables de Ejes
    var x by remember { mutableFloatStateOf(0F) }
    var y by remember { mutableFloatStateOf(0f) }
    var z by remember { mutableFloatStateOf(0f) }

    //Variable que detecta movimiento
    var isMoving by remember { mutableStateOf(false) }

    val context = LocalContext.current //Manejador de contextos

    val sensorManager = remember {
        context.getSystemService(
            SensorManager::class.java
        )
    }

    val accelerometer = remember {
        sensorManager.getDefaultSensor(
            Sensor.TYPE_ACCELEROMETER
        )
    }

    val sensorListener = remember {
        object : SensorEventListener{
            override fun onSensorChanged(event: SensorEvent) {
                x = event.values[0]
                y = event.values[1]
                z = event.values[2]

                val magnitude = sqrt(
                    x * x +
                            y * y +
                            z * z
                )

                isMoving = (magnitude > 12F)
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
            }
        }
    }

    DisposableEffect(accelerometer) {
        if (accelerometer != null){
            sensorManager.registerListener(
                sensorListener,
                accelerometer,
                SensorManager.SENSOR_DELAY_UI
            )
        }

        onDispose{
            sensorManager.unregisterListener(
                sensorListener
            )
        }
    }

    SensorSection(
        ejeX = x,
        y = y,
        z = z,
        isMoving = isMoving
    )

}

@Composable
fun SensorSection(
    ejeX: Float,
    y: Float,
    z: Float,
    isMoving: Boolean
){
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Detector de movimiento",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(32.dp))

        SensorValueCard(
            eje = "X",
            value = ejeX
        )

        Spacer(modifier = Modifier.height(12.dp))

        SensorValueCard(
            eje = "Y",
            value = y
        )

        Spacer(modifier = Modifier.height(12.dp))

        SensorValueCard(
            eje = "Z",
            value = z
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = if(isMoving){
                "Estado: En movimiento"
            } else{
                "Estado: Sin movimiento"
            },
            style = MaterialTheme.typography.titleLarge
        )

    }
}

@Composable
fun SensorValueCard(
    eje: String,
    value: Float
){
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Eje: $eje",
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = "%.2f m/s2".format(value),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}