package com.itca.test_semana7.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.itca.test_semana7.data.dao.TaskDAO
import com.itca.test_semana7.data.entity.Task
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(
    private val taskDao: TaskDAO
) : ViewModel() {

    val tareas: StateFlow<List<Task>> =
        taskDao.getAll()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = emptyList()
            )

    fun insertar(
        titulo: String,
        descripcion: String,
        estado: String,
        etiqueta: String
    ) {

        viewModelScope.launch {

            val task = Task(
                titulo = titulo,
                descripcion = descripcion,
                estado = estado,
                etiqueta = etiqueta
            )

            taskDao.insert(task)
        }
    }

    fun actualizar(task: Task) {

        viewModelScope.launch {
            taskDao.update(task)
        }
    }

    fun eliminar(task: Task) {

        viewModelScope.launch {
            taskDao.delete(task)
        }
    }
}

class TaskViewModelFactory(
    private val taskDao: TaskDAO
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {

            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(taskDao) as T
        }

        throw IllegalArgumentException(
            "Unknown ViewModel class"
        )
    }
}