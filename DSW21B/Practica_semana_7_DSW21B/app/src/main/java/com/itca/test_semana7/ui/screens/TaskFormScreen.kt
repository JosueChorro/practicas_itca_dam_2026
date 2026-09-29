package com.itca.test_semana7.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.itca.test_semana7.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskFormScreen(
    viewModel: TaskViewModel,
    taskId: Int?,
    onBack: () -> Unit
) {

    val tareas by viewModel.tareas.collectAsState()

    val tareaExistente =
        tareas.find { it.id == taskId }

    var titulo by remember(
        tareaExistente
    ) {
        mutableStateOf(
            tareaExistente?.titulo ?: ""
        )
    }

    var descripcion by remember(
        tareaExistente
    ) {
        mutableStateOf(
            tareaExistente?.descripcion ?: ""
        )
    }

    var estado by remember(
        tareaExistente
    ) {
        mutableStateOf(
            tareaExistente?.estado ?: "Pendiente"
        )
    }

    var etiqueta by remember(
        tareaExistente
    ) {
        mutableStateOf(
            tareaExistente?.etiqueta ?: ""
        )
    }

    var tituloError by remember {
        mutableStateOf(false)
    }

    var descripcionError by remember {
        mutableStateOf(false)
    }

    var etiquetaError by remember {
        mutableStateOf(false)
    }

    val estados = listOf(
        "Pendiente",
        "En progreso",
        "Completada"
    )

    var expanded by remember {
        mutableStateOf(false)
    }

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text(
                        if (taskId == null)
                            "Nueva tarea"
                        else
                            "Editar tarea"
                    )
                }
            )
        }

    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(20.dp),

            verticalArrangement =
                Arrangement.spacedBy(16.dp)
        ) {

            OutlinedTextField(

                value = titulo,

                onValueChange = {
                    titulo = it
                    tituloError = false
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Título")
                },

                isError = tituloError,

                supportingText = {

                    if (tituloError) {
                        Text("El título es obligatorio")
                    }
                },

                singleLine = true
            )

            OutlinedTextField(

                value = descripcion,

                onValueChange = {
                    descripcion = it
                    descripcionError = false
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Descripción")
                },

                isError = descripcionError,

                supportingText = {

                    if (descripcionError) {
                        Text("La descripción es obligatoria")
                    }
                },

                minLines = 4
            )

            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = {
                    expanded = !expanded
                }
            ) {

                OutlinedTextField(

                    value = estado,

                    onValueChange = {},

                    readOnly = true,

                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor(),

                    label = {
                        Text("Estado")
                    }
                )

                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    }
                ) {

                    estados.forEach { opcion ->

                        DropdownMenuItem(

                            text = {
                                Text(opcion)
                            },

                            onClick = {

                                estado = opcion
                                expanded = false
                            }
                        )
                    }
                }
            }

            OutlinedTextField(

                value = etiqueta,

                onValueChange = {
                    etiqueta = it
                    etiquetaError = false
                },

                modifier = Modifier.fillMaxWidth(),

                label = {
                    Text("Etiqueta")
                },

                placeholder = {
                    Text("Ej. Universidad")
                },

                isError = etiquetaError,

                supportingText = {

                    if (etiquetaError) {
                        Text("La etiqueta es obligatoria")
                    }
                },

                singleLine = true
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Button(

                onClick = {

                    tituloError = titulo.isBlank()
                    descripcionError =
                        descripcion.isBlank()
                    etiquetaError =
                        etiqueta.isBlank()

                    if (
                        !tituloError &&
                        !descripcionError &&
                        !etiquetaError
                    ) {

                        if (tareaExistente == null) {

                            viewModel.insertar(
                                titulo = titulo.trim(),
                                descripcion =
                                    descripcion.trim(),
                                estado = estado,
                                etiqueta =
                                    etiqueta.trim()
                            )

                        } else {

                            viewModel.actualizar(
                                tareaExistente.copy(
                                    titulo = titulo.trim(),
                                    descripcion =
                                        descripcion.trim(),
                                    estado = estado,
                                    etiqueta =
                                        etiqueta.trim()
                                )
                            )
                        }

                        onBack()
                    }
                },

                modifier = Modifier.fillMaxWidth()
            ) {

                Text(
                    if (taskId == null)
                        "Guardar tarea"
                    else
                        "Actualizar tarea"
                )
            }

            OutlinedButton(
                onClick = onBack,
                modifier = Modifier.fillMaxWidth()
            ) {

                Text("Cancelar")
            }
        }
    }
}