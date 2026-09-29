package com.itca.test_semana7.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.itca.test_semana7.data.entity.Task
import com.itca.test_semana7.viewmodel.TaskViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskListScreen(
    viewModel: TaskViewModel,
    onAddTask: () -> Unit,
    onEditTask: (Int) -> Unit
) {

    val tareas by viewModel.tareas
        .collectAsStateWithLifecycle()

    Scaffold(

        topBar = {

            TopAppBar(
                title = {
                    Text("Mis tareas")
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = onAddTask
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Nueva tarea"
                )
            }
        }

    ) { paddingValues ->

        if (tareas.isEmpty()) {

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {

                Text(
                    text = "No hay tareas registradas.",
                    modifier = Modifier.padding(24.dp)
                )
            }

        } else {

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),

                verticalArrangement =
                    Arrangement.spacedBy(12.dp)
            ) {

                items(
                    items = tareas,
                    key = { it.id }
                ) { task ->

                    TaskCard(
                        task = task,

                        onEdit = {
                            onEditTask(task.id)
                        },

                        onDelete = {
                            viewModel.eliminar(task)
                        }
                    )
                }
            }
        }
    }
}


@Composable
fun TaskCard(
    task: Task,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {

    Card(
        modifier = Modifier.fillMaxWidth()
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            Text(
                text = task.titulo,
                style = MaterialTheme.typography.titleLarge
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = task.descripcion
            )

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            Row(
                horizontalArrangement =
                    Arrangement.spacedBy(8.dp)
            ) {

                AssistChip(
                    onClick = {},
                    label = {
                        Text(task.estado)
                    }
                )

                AssistChip(
                    onClick = {},
                    label = {
                        Text("#${task.etiqueta}")
                    }
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement =
                    Arrangement.End
            ) {

                IconButton(
                    onClick = onEdit
                ) {

                    Icon(
                        Icons.Default.Edit,
                        contentDescription = "Editar"
                    )
                }

                IconButton(
                    onClick = onDelete
                ) {

                    Icon(
                        Icons.Default.Delete,
                        contentDescription = "Eliminar"
                    )
                }
            }
        }
    }
}