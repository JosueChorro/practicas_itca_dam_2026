package com.itca.practica_2_dsw22

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFrom
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itca.practica_2_dsw22.data.models.Student
import com.itca.practica_2_dsw22.ui.theme.Practica_2_DSW22Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegisterStudentForm()
        }
    }
}

@Composable
fun ViewListStudents(){
    val studentsList = listOf<Student>(
        Student(
            id = 1,
            name = "Gabriel",
            lastName = "Federico",
            email = "example@test.com",
            age = 20,
            career = "Tec. en Sistemas",
            hasLicense = false
        ),
        Student(
            id = 2,
            name = "Marta",
            lastName = "Lindsay",
            email = "example@test.com",
            age = 20,
            career = "Tec. en Sistemas",
            hasLicense = true
        ),
        Student(
            id = 1,
            name = "Angel",
            lastName = "Ludivico",
            email = "example@test.com",
            age = 20,
            career = "Tec. en Sistemas",
            hasLicense = null
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Lista de nombres",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )
        LazyColumn {
             items(studentsList) { student ->
                 Column(
                     modifier =
                         Modifier.fillMaxWidth()
                             .padding(5.dp)
                             .background(Color(0xFF4200FF))
                 ) {
                     Text(
                         text = "${student.name} ${student.lastName}",
                         fontWeight = FontWeight.Bold,
                         fontSize = 16.sp,
                         color = Color.White,
                         modifier = Modifier.padding(8.dp)
                     )
                     Text(
                         text = student.career,
                         fontWeight = FontWeight.Normal,
                         fontSize = 12.sp,
                         color = Color.White,
                         modifier = Modifier.padding(8.dp)

                     )
                 }
             }

        }
    }

}

@Composable
fun RegisterStudentForm(){
    var nameStudent by remember { mutableStateOf("") }
    var lastNameStudent by remember { mutableStateOf("") }
    var ageStudent by remember { mutableStateOf("") }
    var emailStudent by remember { mutableStateOf("") }

    var listStudents by remember { mutableStateOf(listOf<Student>()) }

    Column(

    ) {
        Text(
            text = "Formulario de registro",
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(20.dp)
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = nameStudent,
            onValueChange = {nameStudent = it},
            label = {
                Row(){
                    Text("Nombre estudiante")
                    Text(" *", color = Color.Red)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = lastNameStudent,
            onValueChange = {lastNameStudent = it},
            label = {
                Row(){
                    Text("Apellido estudiante")
                    Text(" *", color = Color.Red)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = ageStudent,
            onValueChange = {ageStudent = it},
            label = {
                Row(){
                    Text("Edad estudiante")
                    Text(" *", color = Color.Red)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = emailStudent,
            onValueChange = {emailStudent = it},
            label = {
                Row(){
                    Text("Correo estudiante")
                    Text(" *", color = Color.Red)
                }
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            onClick = {
                val newStudent = Student(
                    id = listStudents.size + 1,
                    name = nameStudent,
                    lastName = lastNameStudent,
                    age = ageStudent.toInt(),
                    email = emailStudent,
                    career = "Tec. en Sistemas",
                    hasLicense = null
                )

                listStudents += newStudent

                nameStudent = ""
                lastNameStudent = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text("---------------------------------------")
        LazyColumn {
            items(listStudents) { student ->
                Column(
                    modifier =
                        Modifier.fillMaxWidth()
                            .padding(5.dp)
                            .background(Color(0xFF4200FF))
                ) {
                    Text(
                        text = "${student.name} ${student.lastName}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                    Text(
                        text = student.career,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)

                    )
                }
            }

        }
    }
}


