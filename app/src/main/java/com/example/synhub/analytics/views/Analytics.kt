package com.example.synhub.analytics.views

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synhub.analytics.model.response.AnalyticsWebService
import com.example.synhub.analytics.application.dto.GroupMemberCountResponse
import com.example.synhub.analytics.application.dto.AnalyticsResponse
import com.example.synhub.analytics.application.dto.TaskTimePassedResponse
import com.example.synhub.shared.components.SlideMenu
import com.example.synhub.shared.components.TopBar
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.synhub.analytics.views.AnalyticsDetails.EnhancedBarChart
import com.example.synhub.analytics.views.AnalyticsDetails.MetricCard
import com.example.synhub.analytics.views.AnalyticsDetails.formatDaysToDuration
import com.example.synhub.analytics.views.AnalyticsDetails.formatDetailValue
import com.example.synhub.analytics.views.AnalyticsDetails.formatDuration
import com.example.synhub.analytics.views.AnalyticsDetails.formatSummary
import com.example.synhub.analytics.views.AnalyticsDetails.getFriendlyName

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
    val api = remember { retrofit.create(AnalyticsWebService::class.java) }

    val taskOverview by produceState<Result<AnalyticsResponse>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getTaskOverview(groupId) }
    }
    val taskDistribution by produceState<Result<AnalyticsResponse>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getTaskDistribution(groupId) }
    }
    val rescheduledTasks by produceState<Result<AnalyticsResponse>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getRescheduledTasks(groupId) }
    }
    val avgSolutionTime by produceState<Result<AnalyticsResponse>?>(initialValue = null, api) {
        value = runCatching { api.getAvgSolutionTime(1L) }
    }
    val avgDevTime by produceState<Result<AnalyticsResponse>?>(initialValue = null, api) {
        value = runCatching { api.getAvgDevTime(1L) }
    }
    val groupMemberCount by produceState<Result<GroupMemberCountResponse>?>(initialValue = null, api, groupId) {
        value = runCatching { api.getGroupMemberCount(groupId) }
    }
    val taskTimePassed by produceState<Result<TaskTimePassedResponse>?>(initialValue = null, api) {
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
