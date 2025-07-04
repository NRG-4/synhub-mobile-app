package com.example.synhub.tasks.views

import android.os.Build
import android.util.Log
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.icons.editSVG
import com.example.synhub.shared.icons.trashSVG
import com.example.synhub.tasks.viewmodel.TaskViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.text.toFloat
import kotlin.toString

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Tasks(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.navigate("Home")
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
    val tasksViewModel: TaskViewModel = viewModel()

    val tasks by tasksViewModel.tasks.collectAsState()

    val haveTasks = tasks.isNotEmpty()

    LaunchedEffect(Unit) {
        tasksViewModel.fetchGroupTasks()
    }

    Log.d("Tasks.kt", "Tareas obtenias: ${tasks.size}")

    Box(modifier = Modifier.fillMaxSize()){
        if(!haveTasks)
        {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp)
                    .padding(horizontal = 20.dp)
            ) {
                NoTasks()
            }

        }else{
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp)
                    .padding(horizontal = 20.dp)
            ) {
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ){
                    items(tasks){ task ->
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
                                    val createdDate = task.createdAt.substring(0, 10)
                                    val dueDate = task.dueDate.substring(0, 10)
                                    Text(
                                        text = "$createdDate - $dueDate",
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
                                                nav.navigate("Tasks/Edit/${task.id}")
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
                                                tasksViewModel.deleteTask(task.id)
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
                    }
                }
            }
        }
        FloatingActionButton(
            onClick = { nav.navigate("Tasks/Create") },
            containerColor = Color(0xFF1A4E85),
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(20.dp)
        ) {
            Icon(
                Icons.Default.Add,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
fun getDividerColor(
    createdAt: String,
    dueDate: String,
    nowDate: String = LocalDate.now().toString()
): Color {
    val formatters = listOf(
        DateTimeFormatter.ISO_OFFSET_DATE_TIME, // Soporta "2025-06-23T21:53:51.241Z"
    )
    fun parseDate(dateStr: String): LocalDate {
        for (formatter in formatters) {
            try {
                // Intenta parsear como ZonedDateTime
                return java.time.ZonedDateTime.parse(dateStr, formatter).toLocalDate()
            } catch (_: Exception) {
                try {
                    // Intenta parsear como LocalDateTime
                    return java.time.LocalDateTime.parse(dateStr, formatter).toLocalDate()
                } catch (_: Exception) {}
            }
        }
        throw IllegalArgumentException("Formato de fecha no soportado: $dateStr")
    }
    val created = parseDate(createdAt)
    val due = parseDate(dueDate)
    val now = LocalDate.parse(nowDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))

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
fun NoTasks(){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
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
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}