package com.example.synhub.groups.views

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.synhub.groups.application.dto.MemberResponse
import com.example.synhub.groups.application.dto.TaskResponse
import com.example.synhub.groups.viewmodel.MemberViewModel
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.icons.editSVG
import com.example.synhub.shared.icons.trashSVG
import com.example.synhub.tasks.views.getDividerColor

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemberDetails(nav: NavHostController, memberId: String?) {
    val memberViewModel: MemberViewModel = viewModel()
    val member by memberViewModel.member.collectAsState()
    val memberTasks by memberViewModel.memberTasks.collectAsState()

    LaunchedEffect(memberId) {
        memberId?.toLongOrNull()?.let {
            memberViewModel.fetchMemberDetails(it)
            memberViewModel.fetchMemberTasks(it)
        }
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "${member?.name} ${member?.surname}",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> MemberDetailScreen(
        modifier = Modifier.padding(innerPadding),
        nav, member, memberTasks)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MemberDetailScreen(modifier: Modifier, nav: NavHostController, member: MemberResponse?, tasks: List<TaskResponse>) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .padding(horizontal = 20.dp)
        ) {
            Text("Tareas:", fontSize = 25.sp, color = Color(0xFF1A4E85), fontWeight = FontWeight.Bold)
            HorizontalDivider(color = Color(0xFF1A4E85), thickness = 2.dp)
            Spacer(modifier = Modifier.height(20.dp))
            if(tasks.isNotEmpty()){
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(tasks) { task ->
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
                                modifier = Modifier
                                    .padding(10.dp)
                                    .background(Color(0xFFF5F5F5))
                            ) {
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
            } else {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(20.dp))
                {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp)
                        .padding(top = 10.dp)
                        .background(
                            Color(0xFF1A4E85),
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
                                text = "No hay tareas programadas para: ${member?.name ?: "Este miembro"}",
                                fontSize = 25.sp,
                                color = Color(0xFFFFFFFF)
                            )
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

        FloatingActionButton(
            onClick = { nav.popBackStack() },
            containerColor = Color(0xFFF44336),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Icon(
                Icons.Default.Delete,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .size(40.dp)
            )
        }
    }
}
