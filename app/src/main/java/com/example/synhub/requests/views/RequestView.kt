package com.example.synhub.requests.views

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.synhub.R
import com.example.synhub.requests.application.dto.RequestResponse
import com.example.synhub.requests.viewModel.RequestViewModel
import com.example.synhub.shared.components.TopBar
import com.example.synhub.tasks.application.dto.TaskResponse
import com.example.synhub.tasks.viewmodel.TaskViewModel

@Composable
fun RequestView(nav: NavHostController, taskId: String?) {
    val requestViewModel: RequestViewModel = viewModel()
    val request by requestViewModel.request.collectAsState()

    val taskViewModel: TaskViewModel = viewModel()
    val task by taskViewModel.task.collectAsState()

    LaunchedEffect(taskId) {
        taskId?.toLongOrNull()?.let {
            requestViewModel.fetchRequestByTaskId(it)
        }
    }

    LaunchedEffect(request?.taskId) {
        request?.taskId?.let {
            taskViewModel.fetchTaskById(it)
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
                title = "Validación",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) {
        innerPadding -> RequestDetails(modifier = Modifier.padding(innerPadding),nav, request, task)
    }
}


@Composable
fun RequestDetails(modifier: Modifier, nav: NavHostController, request: RequestResponse?, task: TaskResponse?) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight()
            .padding(30.dp)
            .padding(top = 90.dp),
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = R.drawable.becker), // Replace with actual image
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = task?.member?.name.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp
            )
        }
        Card(
            modifier = Modifier
                .padding(vertical = 15.dp),
            elevation = CardDefaults
                .cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(120.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A4E85)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Text(text = request?.taskId.toString(), color = Color.White)
                        Spacer(modifier = Modifier.height(10.dp))
                        HorizontalDivider(thickness = 2.dp)
                        Spacer(modifier = Modifier.height(10.dp))
                        Text(text = task?.description.toString(), color = Color.White)
                    }
                }
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(8.dp)
                ) {
                    Text(
                        text = "Tiempo de desarrollo asignado",
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = task?.createdAt + " - " + task?.dueDate,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .height(10.dp)
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFFF832A))

                    )
                }

            }
        }

        Card(
            modifier = Modifier
                .padding(vertical = 15.dp),
            elevation = CardDefaults
                .cardElevation(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card(
                    modifier = Modifier
                        .padding(10.dp)
                        .height(180.dp)
                        .fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A4E85)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                    ) {
                        Text(text = "Comentario", color = Color.White)
                    }
                }

            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFFF832A)
            ),
            elevation = ButtonDefaults
                .buttonElevation(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp),
        ) {
            Icon(
                Icons.Default.DateRange,
                contentDescription = null)
            Spacer(
                modifier = Modifier
                    .width(8.dp))
            Text("Reprogramar")
        }
        Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFF44336)
            ),
            elevation = ButtonDefaults
                .buttonElevation(5.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)
        ) {
            Icon(
                Icons.Default.Clear,
                contentDescription = null)
            Spacer(
                modifier = Modifier
                    .width(8.dp))
            Text("Denegar")
        }
    }
}