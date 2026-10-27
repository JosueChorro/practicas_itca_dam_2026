package com.itca.practica_semana_13_dsw21b

import android.os.Bundle
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.itca.practica_semana_13_dsw21b.data.model.Task
import com.itca.practica_semana_13_dsw21b.ui.theme.Practica_semana_13_DSW21BTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Practica_semana_13_DSW21BTheme {
                TaskApp()
            }
        }
    }
}


@Composable
fun TaskApp(){
    val tasks = remember{ mutableListOf(
        Task(
            id = 1,
            title = "Practica DSW21B",
            description = "Desarrollar clase práctica",
            category = "ITCA",
            isCompleted = false
        ),
        Task(
            id = 2,
            title = "Indicaciones proyecto finales DSW21B",
            description = "Explicar que es lo que se necesita para la entrega",
            category = "ITCA",
            isCompleted = false
        ),
    )}

    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("ITCA") }
    var isCompleted by remember { mutableStateOf(false) }

    var expandedTaskId by remember { mutableStateOf<Int?>(null) }
    var idEditing by remember { mutableStateOf<Int?>(null) }
    var idTaskToDelete by remember { mutableStateOf<Int?>(null) }
    var idTaskToEdit by remember { mutableStateOf<Int?>(null) }
    val scope = rememberCoroutineScope()

    fun notify(message: String){
        scope.launch{
            SnackbarHostState.showSnackbar(message)
        }
    }

    fun saveTask(){
        if (title.isBlank()){
            notify("Escriba el titulo de la tarea")
        }

        val currentId = idEditing

        if (currentId == null){
            tasks.add(Task(
                id = tasks.size + 1,
                title = title.trim(),
                description = description.trim(),
                category = category,
                isCompleted = false
            ))

            notify("Tarea guardada")
        }else{
            val index = tasks.indexOfFirst { it.id == currentId }
            if (index >= 0 ){
                tasks[index] = tasks[index].copy(
                    title = title.trim(),
                    description = description.trim(),
                    category = category
                )
            }

            notify("Tarea actualizada")
        }
    }

    Scaffold(
        snackbarHost = {
            //SnackbarHost(hostState = )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {}
            ) {
                Text("+", style = MaterialTheme.typography.headlineMedium)
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(20.dp),
        ) {
            Text("Gestor de tareas", style = MaterialTheme.typography.headlineLarge)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                "${tasks.count{ it.isCompleted }} de ${tasks.size} tareas completadas ",
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(8.dp))

            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(items = tasks, key = {it.id}){ task ->

                    TaskCard(
                        task = task,
                        hasExpanded = expandedTaskId == task.id,
                        onMenuChange = { expandedTaskId = if (it) task.id else null },
                        onDelete = {}
                        onCompleted = {},
                        onEdit = {}
                    )

                }
            }
        }
    }
}


@Composable
fun TaskCard(
    task: Task,
    hasExpanded: Boolean,
    onMenuChange: (Boolean) -> Unit,
    onCompleted: () -> Unit,
    onEdit: () -> Unit,
    onDelete: () -> Unit,
){
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
                    onCheckedChange = { onCompleted() }
                )

                Column(
                    modifier = Modifier.weight(1F)
                ) {
                    Text(
                        text = task.title,
                        style = MaterialTheme.typography.titleMedium,
                        color = if (task.isCompleted)
                            Color.Magenta
                        else Color.Cyan
                    )

                    Text(
                        text = task.category,
                        style = MaterialTheme.typography.labelMedium
                    )
                }

                Box(){
                    TextButton(
                        onClick = {}
                    ) {
                        Text("Opciones")
                    }

                    DropdownMenu(
                        expanded = hasExpanded,
                        onDismissRequest = {
                            onMenuChange(false)
                        }
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
                                onCompleted()
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Editar")
                            },
                            onClick = {
                                onMenuChange(false)
                                onEdit()
                            }
                        )

                        DropdownMenuItem(
                            text = {
                                Text("Eliminar")
                            },
                            onClick = {
                                onMenuChange(false)
                                onDelete()
                            }
                        )
                    }
                }
            }

            if(task.description.isNotBlank()){
                Text(
                    text = task.description,
                    modifier = Modifier.padding(
                        start = 48.dp,
                        top = 4.dp
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}