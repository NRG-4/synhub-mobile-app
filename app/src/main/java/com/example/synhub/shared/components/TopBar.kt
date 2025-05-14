package com.example.synhub.shared.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import kotlinx.coroutines.Job

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onOpenDrawer: (() -> Job)?, title: String, icon: ImageVector
){
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFFDFDFD),
            titleContentColor = Color.Black
        ),
        title = {
            Text( text = title,
                fontWeight = FontWeight.Bold
            )
        },
        navigationIcon = {
            IconButton( onClick = {
                if (onOpenDrawer != null) {
                    onOpenDrawer()
                }
            }) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
        }
    )
}