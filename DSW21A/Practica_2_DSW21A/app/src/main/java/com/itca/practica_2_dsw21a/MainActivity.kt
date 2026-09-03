package com.itca.practica_2_dsw21a

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalProvider
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itca.practica_2_dsw21a.data.model.Student
import com.itca.practica_2_dsw21a.ui.theme.Practica_2_DSW21ATheme

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
fun ListStudentsScreen(){
    val studentList = listOf(
        Student(
            id = 1,
            name = "Federico",
            age = 20,
            phone = "0000-0000",
            career = "Tec. en Sistemas"
        ),

        Student(
            id = 2,
            name = "Ludovico",
            age = 19,
            phone = "0000-0000",
            career = "Tec. en Sistemas"
        ),

        Student(
            id = 3,
            name = "Gloria",
            age = 19,
            phone = "0000-0000",
            career = "Tec. en Sistemas"
        ),

        Student(
            id = 4,
            name = "Ana",
            age = 21,
            phone = "0000-0000",
            career = "Tec. en Sistemas"
        ),
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Text(
            text = "Lista de estudiantes",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(20.dp))
        LazyColumn() {
            items(studentList){student ->
                Text(
                    text = "${student.name} - ${student.career}",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp
                )
            }
        }
    }
}


@Composable
fun RegisterStudentForm(){
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var studentList by remember { mutableStateOf(listOf<Student>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Lista de estudiantes",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = name,
            onValueChange = {name = it},
            label = {
                Row {
                    Text("Nombre")
                    Text(" *", color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = age,
            onValueChange = {age = it},
            label = {
                Row {
                    Text("Edad")
                    Text(" *", color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            value = phone,
            onValueChange = {phone = it},
            label = {
                Row {
                    Text("Télefono")
                    Text(" *", color = Color.Red)
                }
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                val newStudent = Student(
                    id = studentList.size + 1,
                    name = name,
                    age = age.toInt(),
                    phone = phone,
                    career = "Tec. en Sistemas"
                )

                studentList = studentList + newStudent

                name = ""
                age = ""
                phone = ""

            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guadar")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text("--------------------------------------------")

        LazyColumn() {
            items(studentList){student ->
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(15.dp)
                        .background(Color(0xFF4900FF))
                ) {
                    Row {
                        Text(
                            text = student.id.toString(),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )

                        Text(
                            text = student.name,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 18.sp,
                            color = Color.White,
                            modifier = Modifier.padding(8.dp)
                        )
                    }

                    Text(
                        text = student.career,
                        fontWeight = FontWeight.Normal,
                        fontSize = 14.sp,
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                    )
                }
            }
        }
    }
}