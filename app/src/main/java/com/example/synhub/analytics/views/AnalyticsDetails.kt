package com.example.synhub.analytics.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

object AnalyticsDetails {
    private val friendlyNames = mapOf(
        "IN_PROGRESS" to "En progreso",
        "COMPLETED" to "Completadas",
        "total" to "Total",
        "rescheduled" to "Reprogramadas",
        "completedTasks" to "Tareas Completadas",
        "taskCount" to "Cantidad de Tareas"
    )

    fun getFriendlyName(key: String): String = friendlyNames[key] ?: key.replaceFirstChar { it.uppercase() }

    fun formatDetailValue(value: Any?): String =
        when (value) {
            is Double -> if (value % 1.0 == 0.0) value.toInt().toString() else "%.2f".format(value)
            is Float -> if (value % 1.0f == 0.0f) value.toInt().toString() else "%.2f".format(value)
            else -> value?.toString() ?: ""
        }

    fun formatDuration(ms: Long?): String {
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

    fun formatDaysToDuration(days: Double?): String {
        if (days == null || days <= 0.0) return "Sin tiempo registrado"
        val ms = (days * 24 * 60 * 60 * 1000).toLong()
        return formatDuration(ms)
    }

    fun formatSummary(summary: String?): String {
        if (summary == null) return ""
        return summary
            .replace(Regex("(Vista general de tareas: )([0-9]+)\\.00"), "$1$2")
            .replace(Regex("(Distribución de tareas: )([0-9]+)\\.00"), "$1$2")
            .replace(Regex("(Tareas reprogramadas vs no reprogramadas: )([0-9]+)\\.00"), "$1$2")
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
                Divider(
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
}
