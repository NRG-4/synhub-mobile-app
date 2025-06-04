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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synhub.shared.components.TopBar
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskDetail(nav: NavHostController, taskId: String?) {
    val task = exampleTasks.firstOrNull { it.id == taskId?.toIntOrNull() }
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "Detalles de la tarea",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> TaskDetailScreen(
        modifier = Modifier.padding(innerPadding),
        nav, task)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TaskDetailScreen(modifier: Modifier, nav: NavHostController, task: Task?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp)
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        if (task != null) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = "Título:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HorizontalDivider(color = Color.Black)
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = task.title,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = "Detalles de la tarea:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HorizontalDivider(color = Color.Black)
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = task.description,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = "Asignado a:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HorizontalDivider(color = Color.Black)
                    Spacer(modifier = Modifier.size(15.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
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
                            fontSize = 18.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = "Asignado el:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HorizontalDivider(color = Color.Black)
                    Spacer(modifier = Modifier.size(15.dp))
                    Text(
                        text = task.createdAt,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(15.dp)
                    )
                    .padding(10.dp),
                contentAlignment = Alignment.TopStart
            ) {
                Column {
                    Text(
                        text = "Vence el:",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    HorizontalDivider(color = Color.Black)
                    Spacer(modifier = Modifier.size(8.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(
                                color = getDividerColor(task.createdAt, task.dueDate),
                                shape = RoundedCornerShape(10.dp)
                            )
                    )
                    Spacer(modifier = Modifier.size(8.dp))
                    Text(
                        text = task.dueDate,
                        style = TextStyle(fontSize = 18.sp, color = Color.Black)
                    )
                }
            }

        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Tarea no encontrada",
                    style = TextStyle(
                        fontSize = 20.sp,
                        color = Color.Red
                    )
                )
            }
        }
    }
}

