package com.example.synhub.groups.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.TopBar

@Composable
fun Group(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                null,
                "Grupos",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> GroupScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@Composable
fun GroupScreen(modifier: Modifier, nav: NavHostController) {
    Text(text = "hola")
}