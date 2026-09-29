package com.itca.test_semana7

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.itca.test_semana7.data.database.AppDatabase
import com.itca.test_semana7.navigation.AppNavigation
import com.itca.test_semana7.ui.theme.Test_semana7Theme
import com.itca.test_semana7.viewmodel.TaskViewModel
import com.itca.test_semana7.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(applicationContext)


        setContent {

            val taskViewModel: TaskViewModel = viewModel(
                factory = TaskViewModelFactory(database.taskDao())
            )

            Test_semana7Theme {
                AppNavigation(viewModel = taskViewModel)
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Test_semana7Theme {
        Greeting("Android")
    }
}