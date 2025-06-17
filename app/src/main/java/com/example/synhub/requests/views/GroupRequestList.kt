package com.example.synhub.requests.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.R
import com.example.synhub.groups.viewmodel.MemberViewModel
import com.example.synhub.requests.viewModel.RequestViewModel
import com.example.synhub.shared.components.TopBar
import com.example.synhub.tasks.viewmodel.TaskViewModel


@Composable
fun GroupRequestList(nav: NavHostController) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                title = "Solicitudes y Validaciones",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ) {
        innerPadding -> GroupRequestsScreen(modifier = Modifier.padding(innerPadding), nav)
    }

}

@Composable
fun GroupRequestsScreen(modifier: Modifier, nav: NavHostController,
                        requestsViewModel: RequestViewModel = RequestViewModel(),
                        taskViewModel: TaskViewModel = TaskViewModel(),
                        memberViewModel: MemberViewModel = MemberViewModel()) {
    val requests by requestsViewModel.requests.collectAsState()
    val hasRequests = requests.isNotEmpty()

    fun getStatusColor(status: String?): Color {
        return when (status) {
            "COMPLETED" -> Color(0xFF4CAF50) // Green
            "EXPIRED" -> Color(0xFFF44336)   // Red
            "IN_PROGRESS" -> Color(0xFFFFC107)   // Amber
            else -> Color(0xFF2196F3)        // Blue (default)
        }
    }

    LaunchedEffect(Unit) {
        requestsViewModel.fetchGroupRequests()
    }

    Box(modifier = Modifier
        .fillMaxSize()
    ) {
        if(!hasRequests) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 120.dp)
                    .padding(horizontal = 30.dp)
            ) {
                Card(
                    modifier = Modifier
                        .padding(vertical = 15.dp)
                        .height(170.dp),
                    elevation = CardDefaults
                        .cardElevation(10.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF1A4E85)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .padding(horizontal = 60.dp, vertical = 40.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("No hay ninguna solicitud o validación por el momento.",
                                fontWeight = FontWeight.Bold,
                                fontSize = 24.sp,
                                color = Color.White,
                                textAlign = TextAlign.Center
                            )
                        }

                    }

                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .fillMaxHeight()
                    .padding(30.dp)
                    .padding(top = 90.dp),
            ) {

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                ) {
                    items(requests) {
                        request ->
                        // Fetch task details for the request
                        LaunchedEffect(request.taskId) {
                            taskViewModel.fetchTaskById(request.taskId)
                        }
                        val task by taskViewModel.task.collectAsState()
                        Card(
                            modifier = Modifier
                                .padding(vertical = 15.dp)
                                .height(260.dp),
                            elevation = CardDefaults
                                .cardElevation(10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF1A4E85)
                            ),
                            onClick = {
                                nav.navigate("Validation/${request.taskId}")
                            }
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                            ) {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Image(
                                        painter = painterResource(id = R.drawable.becker), // Replace with actual image
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(42.dp)
                                            .clip(CircleShape)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = task?.member?.name + " " + task?.member?.surname,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 20.sp,
                                        color = Color.White
                                    )
                                }
                                Row() {
                                    Card(
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .height(160.dp)
                                            .width(280.dp),
                                        colors = CardDefaults.cardColors(
                                            containerColor = Color.White
                                        )
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .padding(16.dp),
                                        ) {
                                            Text(text = task?.title.toString(), color = Color.Black)
                                            Spacer(modifier = Modifier.height(10.dp))
                                            HorizontalDivider(thickness = 2.dp)
                                            Spacer(modifier = Modifier.height(10.dp))
                                            Text(text = task?.description.toString(), color = Color.Black)
                                        }
                                    }
                                    Box(
                                        modifier = Modifier
                                            .padding(vertical = 10.dp)
                                            .height(160.dp)
                                            .width(30.dp)
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(getStatusColor(task?.status.toString()))

                                    ) {
                                        val icon = when (request.requestStatus) {
                                            "COMPLETED" -> Icons.Default.CheckCircle
                                            "EXPIRED" -> Icons.Default.Warning
                                            "PENDING" -> Icons.Default.Email
                                            else -> Icons.Default.Info
                                        }
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = null,
                                            tint = Color.White,
                                            modifier = Modifier
                                                .align(Alignment.Center)
                                                .size(24.dp)
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


}