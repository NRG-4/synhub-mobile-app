package com.example.synhub.tasks.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.icons.editSVG
import com.example.synhub.shared.icons.trashSVG
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

data class Member(
    val name: String,
    val surname: String,
    val urlImage: String
)

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val dueDate: String,
    val createdAt: String,
    val updatedAt: String,
    val status: String,
    val member: Member,
    val groupId: Int
)

val exampleTasks = listOf(
    Task(
        id = 1,
        title = "Diseñar logo",
        description = "Crear el logo principal del grupo",
        dueDate = "2025-06-15",
        createdAt = "2025-06-01",
        updatedAt = "2025-06-10",
        status = "Pendiente",
        member = Member(
            name = "Ana",
            surname = "García",
            urlImage = "https://s1.elespanol.com/2023/06/08/vivir/salud-mental/769933690_233804290_1706x960.jpg"
        ),
        groupId = 101
    ),
    Task(
        id = 2,
        title = "Desarrollar app",
        description = "Programar la funcionalidad básica",
        dueDate = "2025-06-05",
        createdAt = "2025-05-30",
        updatedAt = "2025-06-05",
        status = "En progreso",
        member = Member(
            name = "Luis",
            surname = "Pérez",
            urlImage = "https://images.ecestaticos.com/vU8sC8tLdkx-2YYh1fkOGL8vfeY=/0x0:990x557/1200x900/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2F62c%2Fe5d%2F314%2F62ce5d3141c0b670144a692b7f6a21fa.jpg"
        ),
        groupId = 101
    ),
    Task(
        id = 3,
        title = "Redactar documentación",
        description = "Escribir la documentación del proyecto",
        dueDate = "2025-06-18",
        createdAt = "2025-05-04",
        updatedAt = "2025-06-13",
        status = "Completado",
        member = Member(
            name = "María",
            surname = "López",
            urlImage = "https://img.freepik.com/free-psd/expressive-woman-gesturing_23-2150198838.jpg?semt=ais_items_boosted&w=740"
        ),
        groupId = 101
    ),
    Task(
        id = 4,
        title = "Redactar documentación",
        description = "Escribir la documentación del proyecto",
        dueDate = "2024-06-18",
        createdAt = "2024-06-12",
        updatedAt = "2024-06-13",
        status = "Completado",
        member = Member(
            name = "Juan",
            surname = "López",
            urlImage = "https://www.trendtic.cl/wp-content/uploads/2018/05/003-Rub%C3%A9n-Belluomo-INFOR-2018.jpg"
        ),
        groupId = 101
    ),
    Task(
        id = 5,
        title = "Redactar documentación",
        description = "Escribir la documentación del proyecto",
        dueDate = "2024-06-18",
        createdAt = "2024-06-12",
        updatedAt = "2024-06-13",
        status = "Completado",
        member = Member(
            name = "María",
            surname = "Huaman",
            urlImage = "https://www.caritas.org.mx/wp-content/uploads/2019/02/cualidades-persona-humanitaria.jpg"
        ),
        groupId = 101
    )
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Tasks(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "Tareas",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> TaskScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskScreen(modifier: Modifier, nav: NavHostController) {

    var tareas = true

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp)
            .padding(horizontal = 20.dp)
    ){
        if(!tareas)
        {
            NoTasks(nav)
        }else{
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ){
                items(exampleTasks){
                        task ->(
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 5.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    clip = true
                                ),
                            shape = RoundedCornerShape(10.dp),
                            colors = cardColors(
                                containerColor = Color(0xFFF5F5F5)
                            ),
                            onClick = {
                                nav.navigate("Tasks/Detail/${task.id}")
                            }
                        ){
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(10.dp)
                                    .background(Color(0xFFF5F5F5))
                            ) {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ){
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
                                            .shadow(
                                                elevation = 5.dp,
                                                shape = CircleShape,
                                                clip = true
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        AsyncImage(
                                            model = task.member.urlImage,
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier
                                                .matchParentSize()
                                                .clip(CircleShape)
                                        )
                                    }
                                    Text(
                                        text = task.member.name + " " + task.member.surname,
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }

                                Text(
                                    text=task.title,
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )
                                HorizontalDivider(color = Color.Black)
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .shadow(
                                            elevation = 5.dp,
                                            shape = RoundedCornerShape(10.dp),
                                            clip = true
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = cardColors(
                                        containerColor = Color(0xFFFFFFFF)
                                    ),
                                ){
                                    Column (
                                        modifier = Modifier
                                            .padding(10.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                    ){
                                        Text(
                                            text=task.description,
                                            fontSize = 15.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(10.dp)
                                        .background(
                                            color = getDividerColor(task.createdAt, task.dueDate),
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                )
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(
                                        text = task.createdAt + " - " + task.dueDate,
                                        fontSize = 15.sp,
                                        color = Color.Black
                                    )
                                }
                                Column (
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ){
                                    Row (
                                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                                    ){
                                        ElevatedButton(
                                            colors = ButtonDefaults.buttonColors(Color(0xFFFF9800)),
                                            shape = RoundedCornerShape(10.dp),
                                            modifier = Modifier,
                                            onClick = {
                                                nav.navigate("Tasks/Edit")
                                            }
                                        ) {
                                            Icon(
                                                painter = rememberVectorPainter(image = editSVG),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Editar", fontSize = 15.sp,
                                                color = Color.White, fontWeight = FontWeight.Bold
                                            )

                                        }
                                        ElevatedButton(
                                            colors = ButtonDefaults.buttonColors(Color(0xFFF44336)),
                                            shape = RoundedCornerShape(10.dp),
                                            modifier = Modifier,
                                            onClick = {

                                            }
                                        ) {
                                            Icon(
                                                painter = rememberVectorPainter(image = trashSVG),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Borrar", fontSize = 15.sp,
                                                color = Color.White, fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        )
                }

            }

            Spacer(modifier = Modifier.height(20.dp))

            Column (
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(Color(0xFF1A4E85)),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier,
                    onClick = {
                        nav.navigate("Tasks/Create")
                    }
                ) {
                    Text(
                        text = "Crear Tarea", fontSize = 20.sp,
                        color = Color.White, fontWeight = FontWeight.Bold
                    )

                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
fun getDividerColor(
    createdAt: String,
    dueDate: String,
    nowDate: String = LocalDate.now().toString()
): Color {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val created = LocalDate.parse(createdAt, formatter)
    val due = LocalDate.parse(dueDate, formatter)
    val now = LocalDate.parse(nowDate, formatter)

    val totalDays = ChronoUnit.DAYS.between(created, due).toFloat().coerceAtLeast(1f)
    val daysPassed = ChronoUnit.DAYS.between(created, now).toFloat()
    val progress = (daysPassed / totalDays).coerceIn(0f, 1f)

    return when {
        now.isAfter(due) -> Color(0xFFF44336)
        progress < 0.7f -> Color(0xFF4CAF50)
        else -> Color(0xFFFDD634)
    }
}

@Composable
fun NoTasks(nav: NavHostController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(top = 10.dp)
            .background(Color(0xFF1A4E85),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center){
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No hay tareas programadas",
                    fontSize = 25.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}