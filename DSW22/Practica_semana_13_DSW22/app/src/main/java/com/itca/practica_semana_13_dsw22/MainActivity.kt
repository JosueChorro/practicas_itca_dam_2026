package com.itca.practica_semana_13_dsw22

import android.os.Bundle
import android.os.Message
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itca.practica_semana_13_dsw22.data.model.Task
import com.itca.practica_semana_13_dsw22.ui.theme.Practica_semana_13_DSW22Theme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_semana_13_DSW22Theme {
                TaskApp()
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskApp() {
    val tasks = remember {
        mutableStateListOf(
            Task(
                1,
                "Desarrollar Practica DSW22",
                "Desarrollar clase de semana 13, modals",
                "ITCA"
            ),
            Task(
                2,
                "Explicar entregables del proyecto final DSW21B",
                "Abordar entrega de proyecto final",
                "ITCA",
            )
        )
    }

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("ITCA") }
    var showCategory by remember { mutableStateOf(false) }
    var taskToDelete by remember { mutableStateOf<Task?>(null) }

    var showSheet by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    var expandedTaskId by remember { mutableStateOf<Int?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    fun notify(message: String){
        scope.launch {
            snackbarHostState.showSnackbar(message)
        }
    }


    fun saveTask(){
        if (title.isBlank()){
            notify("Debe ingresar un titulo")
            return
        }

        tasks.add(
            Task(
                id = tasks.size + 1,
                title = title.trim(),
                description = description.trim(),
                category = category
            )
        )

        showSheet = false

        notify("Tarea guardada exitosamente")
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.height(60.dp)
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showSheet = true }) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        }
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp)
        ) {
            Text(
                text = "Gestor de tareas",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "${tasks.count { it.isCompleted }} de ${tasks.size} tareas completadas",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(Modifier.height(20.dp))

            if (tasks.isEmpty()) {
                Text("No hay tareas. Pulsa + para agregar una.")
            } else {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(items = tasks, key = { it.id }) { task ->
                        TaskCard(
                            task = task,
                            menuExpanded = expandedTaskId == task.id,
                            onMenuChange = { expandedTaskId = if (it) task.id else null },
                            onComplete = {
                                val index = tasks.indexOfFirst { it.id == task.id }
                                if (index >= 0) {
                                    tasks[index] = tasks[index].copy(
                                        isCompleted = !tasks[index].isCompleted
                                    )
                                }
                            },
                            onEdit = {
                            },
                            onDelete = {
                                expandedTaskId = null
                                taskToDelete = task
                                showDialog = true
                            }
                        )
                    }
                }
            }
        }
    }

    if (showDialog && taskToDelete != null){
        val task = taskToDelete

        AlertDialog(
            onDismissRequest = {
                showDialog = false
                taskToDelete = null
            },
            title = {
                Text("Eliminar tarea")
            },
            text = {
                Text(
                    "¿Desea eliminar \"${task!!.title}\"?" +
                            "Esta acción no se puede deshacer"
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        tasks.removeAll{it.id == task!!.id}
                        showDialog = false
                        notify("Registro eliminiado correctamente")
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        showDialog = false
                    }
                ) {
                    Text("Cancelar")
                }
            },
        )
    }



    if (showSheet)
        ModalBottomSheet(
            onDismissRequest = { showSheet = false }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .padding(bottom = 32.dp),
                verticalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Text(
                    text = "Nueva tarea",
                    style = MaterialTheme.typography.headlineSmall
                )

                OutlinedTextField(
                    value = title,
                    onValueChange = {title = it},
                    label = {
                        Text("Titulo")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                OutlinedTextField(
                    value = description,
                    onValueChange = {description = it},
                    label = {
                        Text("Descripción")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Box() {
                    OutlinedButton(
                        onClick = {
                            showCategory = true
                        },
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text("Categoria: $category")
                    }

                    DropdownMenu(
                        onDismissRequest = {showCategory = false},
                        expanded = showCategory
                    ) {
                        listOf(
                            "ITCA",
                            "Personal",
                            "Trabajo"
                        ).forEach { option ->
                            DropdownMenuItem(
                                text = {Text(option)},
                                onClick = {
                                    category = option
                                    showCategory = false
                                }
                            )
                        }
                    }
                }

                Button(
                    onClick = { saveTask() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Guardar tarea")
                }

                TextButton(
                    onClick = {
                        showSheet = false
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cancelar")
                }
            }
        }
}

@Composable
fun TaskCard(
    task: Task,
    menuExpanded: Boolean,
    onMenuChange: (Boolean) -> Unit,
    onComplete: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = task.isCompleted,
                    onCheckedChange = { onComplete() }
                )

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (task.isCompleted)
                            MaterialTheme.colorScheme.primary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )

                    Text(
                        text = task.category,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Box {
                    TextButton(onClick = { onMenuChange(true) }) {
                        Text("Opciones ▾")
                    }

                    DropdownMenu(
                        expanded = menuExpanded,
                        onDismissRequest = { onMenuChange(false) }
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    if (task.isCompleted)
                                        "Marcar pendiente"
                                    else
                                        "Completar"
                                )
                            },
                            onClick = {
                                onMenuChange(false)
                                onComplete()
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Editar") },
                            onClick = {
                                onMenuChange(false)
                                onEdit()
                            }
                        )

                        DropdownMenuItem(
                            text = { Text("Eliminar") },
                            onClick = {
                                onMenuChange(false)
                                onDelete()
                            }
                        )
                    }
                }
            }

            if (task.description.isNotBlank()) {
                Text(
                    text = task.description,
                    modifier = Modifier.padding(start = 48.dp, top = 4.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
