package com.example.synhub.analytics.views

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synhub.analytics.views.mocks.MockAnalyticsData
import com.example.synhub.shared.components.SlideMenu
import com.example.synhub.shared.components.TopBar
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsAndReports(nav: NavHostController, groupId: Long = 1L) {
    val taskOverview = remember { MockAnalyticsData.getTaskOverview(groupId) }
    val taskDistribution = remember { MockAnalyticsData.getTaskDistribution(groupId) }
    val rescheduledTasks = remember { MockAnalyticsData.getRescheduledTasks(groupId) }
    val avgSolutionTime = remember { MockAnalyticsData.getAvgSolutionTime(leaderId = 1L) }
    val avgDevTime = remember { MockAnalyticsData.getAvgDevTime(memberId = 1L) }

    val slideMenuState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = slideMenuState,
        drawerContent = {
            ModalDrawerSheet {
                SlideMenu(nav)
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFFFFFFF),
            topBar = {
                TopBar(
                    function = {
                        nav.popBackStack()
                    },
                    "Analítica y Reportes",
                    Icons.AutoMirrored.Filled.ArrowBack
                )
            },
            content = { innerPadding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item {
                        MetricCard(
                            title = "Vista General de Tareas",
                            content = taskOverview["summary"] as String
                        )
                    }
                    item {
                        MetricCard(
                            title = "Distribución de Tareas",
                            content = taskDistribution["summary"] as String,
                            additionalContent = {
                                EnhancedBarChart(taskDistribution["details"] as Map<String, Int>)
                            }
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tareas Reprogramadas",
                            content = rescheduledTasks["summary"] as String
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tiempo Promedio de Solución (Líder)",
                            content = avgSolutionTime["summary"] as String
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tiempo Promedio de Desarrollo",
                            content = avgDevTime["summary"] as String
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun MetricCard(title: String, content: String, additionalContent: @Composable (() -> Unit)? = null) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Text(text = content, style = MaterialTheme.typography.bodyMedium)
            additionalContent?.let {
                Spacer(modifier = Modifier.height(8.dp))
                it()
            }
        }
    }
}

@Composable
fun EnhancedBarChart(details: Map<String, Int>) {
    val maxValue = details.values.maxOrNull() ?: 1
    Column(modifier = Modifier.fillMaxWidth()) {
        details.forEach { (name, value) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.width(100.dp) // Ensures consistent alignment
                )
                Spacer(modifier = Modifier.width(8.dp))
                Canvas(
                    modifier = Modifier
                        .weight(1f) // Ensures bars take up the remaining space
                        .height(24.dp)
                ) {
                    val barWidth = size.width * (value.toFloat() / maxValue)
                    drawRoundRect(
                        color = Color(0xFF6200EE),
                        topLeft = Offset(0f, 0f),
                        size = Size(barWidth, size.height),
                        cornerRadius = CornerRadius(12f, 12f)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = value.toString(),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}