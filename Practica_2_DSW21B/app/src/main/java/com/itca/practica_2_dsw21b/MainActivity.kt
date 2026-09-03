package com.itca.practica_2_dsw21b

import android.graphics.drawable.shapes.RoundRectShape
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.Button
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itca.practica_2_dsw21b.data.models.Student

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterForm()
        }
    }
}

@Composable
fun ViewListNames(){
    //val names = listOf(
        //"Pepito",
        //"Ana",
        //"Federico",
        //"Ludovico",
        //"Cristian"
    //)

    val studentsList = listOf(
        Student(
            id = 1,
            name = "Pepito",
            age = 21,
            career = "Tec. Sistemas",
            hasLicense = true,
        ),
        Student(
            id = 2,
            name = "Ana",
            age = 19,
            career = "Tec. Sistemas",
            hasLicense = false,
        ),
        Student(
            id = 3,
            name = "Federico",
            age = 20,
            career = "Tec. Sistemas",
            hasLicense = null,
        ),
    )

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp)
    ) {
        LazyColumn() {
            items(studentsList) { student ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color(0xFFE4BBEB))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF000000),
                        )
                ) {
                    Text(
                        text = student.name + " - " + student.career,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                    Text(
                        text = "Tiene permiso: ${student.hasLicense}",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterForm(){
    var name by remember { mutableStateOf("") }
    var age by remember { mutableIntStateOf(0) }
    var studentsList by remember { mutableStateOf(listOf<Student>()) }

    Column(
        modifier =
            Modifier
                .fillMaxSize()
                .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registro de estudiantes",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            modifier = Modifier.padding(5.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            value = name,
            onValueChange = {name = it},
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Nombre del estudiante:")
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                val newStudent = Student(
                    id = studentsList.size + 1,
                    name = name,
                    career = "Tec. Sistemas",
                    age = 0,
                    hasLicense = null,
                )

                studentsList = studentsList + newStudent

                name = ""


            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
        Spacer(modifier = Modifier.height(15.dp))
        Text("--------------------------------------")
        Spacer(modifier = Modifier.height(10.dp))
        LazyColumn() {
            items(studentsList) { student ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .background(Color(0xFFE4BBEB))
                        .border(
                            width = 2.dp,
                            color = Color(0xFF000000),
                        )
                ) {
                    Text(
                        text = student.name + " - " + student.career,
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
        }
    }


}


