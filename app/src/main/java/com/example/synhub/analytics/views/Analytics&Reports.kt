package com.example.synhub.analytics.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.analytics.data.*
import com.example.synhub.shared.components.SlideMenu
import com.example.synhub.shared.components.TopBar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Mapeo de claves técnicas a nombres amigables
private val friendlyNames = mapOf(
    "IN_PROGRESS" to "En progreso",
    "COMPLETED" to "Completadas",
    "total" to "Total",
    "rescheduled" to "Reprogramadas",
    "completedTasks" to "Tareas Completadas",
    "taskCount" to "Cantidad de Tareas"
)

private fun getFriendlyName(key: String): String = friendlyNames[key] ?: key.replaceFirstChar { it.uppercase() }

private fun formatDetailValue(value: Any?): String =
    when (value) {
        is Double -> if (value % 1.0 == 0.0) value.toInt().toString() else "%.2f".format(value)
        is Float -> if (value % 1.0f == 0.0f) value.toInt().toString() else "%.2f".format(value)
        else -> value?.toString() ?: ""
    }

private fun formatDuration(ms: Long?): String {
    if (ms == null || ms <= 0) return "Sin tiempo registrado"
    val seconds = ms / 1000
    val days = seconds / (24 * 3600)
    val hours = (seconds % (24 * 3600)) / 3600
    val minutes = (seconds % 3600) / 60
    val secs = seconds % 60

    val parts = mutableListOf<String>()
    if (days > 0) parts.add("$days día${if (days > 1) "s" else ""}")
    if (hours > 0) parts.add("$hours h")
    if (minutes > 0) parts.add("$minutes min")
    if (secs > 0 || parts.isEmpty()) parts.add("$secs s")
    return parts.joinToString(" ")
}

private fun formatDaysToDuration(days: Double?): String {
    if (days == null || days <= 0.0) return "Sin tiempo registrado"
    val ms = (days * 24 * 60 * 60 * 1000).toLong()
    return formatDuration(ms)
}

private fun formatSummary(summary: String?): String {
    if (summary == null) return ""
    // Reemplaza los valores decimales .00 por enteros en los textos conocidos
    return summary
        .replace(Regex("(Vista general de tareas: )([0-9]+)\\.00"), "$1$2")
        .replace(Regex("(Distribución de tareas: )([0-9]+)\\.00"), "$1$2")
        .replace(Regex("(Tareas reprogramadas vs no reprogramadas: )([0-9]+)\\.00"), "$1$2")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnalyticsAndReports(nav: NavHostController, groupId: Long = 1L) {
    val token = "eyJhbGciOiJIUzM4NCJ9.eyJzdWIiOiJnaWFubHVjYSIsImlhdCI6MTc0OTE4MDQ0NiwiZXhwIjoxNzQ5Nzg1MjQ2fQ.TLES8wqu9YQ_DlWitOL2DU4emGS7fHdiT2GspAS86804wCyW4fDv5umjd6hmFc-G"

    val okHttpClient = remember {
        OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
                chain.proceed(request)
            })
            .build()
    }

    val retrofit = remember {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:8080/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val api = remember { retrofit.create(AnalyticsApi::class.java) }

    val taskOverview by produceState<Result<MetricResource>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getTaskOverview(groupId) }
    }
    val taskDistribution by produceState<Result<MetricResource>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getTaskDistribution(groupId) }
    }
    val rescheduledTasks by produceState<Result<MetricResource>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getRescheduledTasks(groupId) }
    }
    val avgSolutionTime by produceState<Result<MetricResource>?>(initialValue = null, api) {
        value = runCatching { api.getAvgSolutionTime(1L) }
    }
    val avgDevTime by produceState<Result<MetricResource>?>(initialValue = null, api) {
        value = runCatching { api.getAvgDevTime(1L) }
    }
    val groupMemberCount by produceState<Result<GroupMemberCountResource>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getGroupMemberCount(groupId) }
    }
    val taskTimePassed by produceState<Result<TaskTimePassedResource>?>(initialValue = null, api) {
        value = runCatching { api.getTaskTimePassed(1L) }
    }

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
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFF0F2F5)),
            containerColor = Color(0xFFF0F2F5),
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
                        .fillMaxWidth()
                        .padding(innerPadding)
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(18.dp)
                ) {
                    item {
                        MetricCard(
                            title = "Vista General de Tareas",
                            content = taskOverview?.getOrNull()?.let {
                                val summary = formatSummary(it.summary)
                                val details = it.details?.entries
                                    ?.joinToString("\n") { (k, v) -> "• ${getFriendlyName(k)}: ${formatDetailValue(v)}" }
                                    ?: ""
                                "$summary\n$details"
                            } ?: taskOverview?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar overview", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando..."
                        )
                    }
                    item {
                        MetricCard(
                            title = "Distribución de Tareas",
                            content = taskDistribution?.getOrNull()?.let {
                                val summary = formatSummary(it.summary)
                                val details = it.details?.entries
                                    ?.joinToString("\n") { (k, v) -> "• ${getFriendlyName(k)}: ${formatDetailValue(v)}" }
                                    ?: ""
                                "$summary\n$details"
                            } ?: taskDistribution?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar distribution", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando...",
                            additionalContent = {
                                taskDistribution?.getOrNull()?.details?.let { dist ->
                                    EnhancedBarChart(dist.mapValues { v -> (v.value as? Number)?.toInt() ?: 0 })
                                }
                            }
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tareas Reprogramadas",
                            content = rescheduledTasks?.getOrNull()?.let {
                                val summary = formatSummary(it.summary)
                                val details = it.details?.entries
                                    ?.joinToString("\n") { (k, v) -> "• ${getFriendlyName(k)}: ${formatDetailValue(v)}" }
                                    ?: ""
                                "$summary\n$details"
                            } ?: rescheduledTasks?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar rescheduled", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando..."
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tiempo Promedio de Solución (Líder)",
                            content = avgSolutionTime?.getOrNull()?.let {
                                val summary = it.summary ?: "Sin resumen"
                                val details = it.details?.entries
                                    ?.joinToString("\n") { (k, v) -> "• ${getFriendlyName(k)}: ${formatDetailValue(v)}" }
                                    ?: ""
                                "$summary\n$details"
                            } ?: avgSolutionTime?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar avgSolutionTime", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando..."
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tiempo Promedio de Desarrollo",
                            content = avgDevTime?.getOrNull()?.let {
                                val summary = it.summary ?: "Sin resumen"
                                // Busca el promedio en días en el summary usando regex
                                val avgDays = Regex("([0-9]+(\\.[0-9]+)?) días?").find(summary)?.groupValues?.get(1)?.toDoubleOrNull()
                                val formatted = formatDaysToDuration(avgDays)
                                val details = it.details?.entries
                                    ?.joinToString("\n") { (k, v) -> "• ${getFriendlyName(k)}: ${formatDetailValue(v)}" }
                                    ?: ""
                                "$summary\nPromedio: $formatted\n$details"
                            } ?: avgDevTime?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar avgDevTime", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando..."
                        )
                    }
                    item {
                        MetricCard(
                            title = "Miembros del Grupo",
                            content = groupMemberCount?.getOrNull()?.let {
                                "Miembros del grupo: ${it.memberCount ?: "?"}"
                            } ?: groupMemberCount?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar memberCount", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando..."
                        )
                    }
                    item {
                        MetricCard(
                            title = "Tiempo transcurrido de tarea",
                            content = taskTimePassed?.getOrNull()?.let {
                                "Tiempo transcurrido: ${formatDuration(it.timePassed)}"
                            } ?: taskTimePassed?.exceptionOrNull()?.let {
                                Log.e("Analytics", "Error al cargar timePassed", it)
                                "Error al cargar: ${it.localizedMessage}"
                            } ?: "Cargando..."
                        )
                    }
                }
            }
        )
    }
}

@Composable
fun MetricCard(
    title: String,
    content: String,
    additionalContent: (@Composable () -> Unit)? = null
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        shape = RoundedCornerShape(18.dp),
        shadowElevation = 6.dp,
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .padding(20.dp)
        ) {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
            androidx.compose.material3.Divider(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(),
                color = Color(0xFFEEEEEE),
                thickness = 1.dp
            )
            Text(
                text = content,
                color = Color(0xFF333333),
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            additionalContent?.let {
                Spacer(modifier = Modifier.padding(top = 8.dp))
                it()
            }
        }
    }
}

@Composable
fun EnhancedBarChart(distribution: Map<String, Int>?) {
    if (distribution.isNullOrEmpty()) return
    val max = distribution.values.maxOrNull()?.takeIf { it > 0 } ?: 1
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
            .background(Color(0xFFF7F7F7), RoundedCornerShape(8.dp))
            .padding(12.dp)
    ) {
        distribution.forEach { (member, count) ->
            Column {
                Text(
                    text = member,
                    color = Color(0xFF666666),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
                androidx.compose.foundation.layout.Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(bottom = 6.dp)
                ) {
                    androidx.compose.foundation.layout.Box(
                        modifier = Modifier
                            .background(Color(0xFF1976D2), RoundedCornerShape(4.dp))
                            .height(18.dp)
                            .fillMaxWidth(fraction = count / max.toFloat())
                    )
                    Spacer(modifier = Modifier.padding(start = 8.dp))
                    Text(
                        text = count.toString(),
                        color = Color(0xFF1976D2),
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}