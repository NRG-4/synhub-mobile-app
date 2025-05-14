package com.example.synhub.groups.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.TopBar

@Composable
fun Group(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
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
    var group = false
    var members = false
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        if(!group) {
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 40.dp)
                .padding(top = 10.dp)
                .background(Color(0xFF1A4E85),
                    shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center){
                Column(
                    modifier = Modifier
                        .padding(20.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "No Haz Creado tu grupo todavia",
                        fontSize = 25.sp,
                        color = Color(0xFFFFFFFF)
                    )
                    ElevatedButton(
                        colors = ButtonDefaults.buttonColors(Color(0xFF4A90E2)),
                        shape = RoundedCornerShape(10.dp),
                        onClick = {
                            nav.navigate("Group/CreateGroup")
                        }
                    ) {
                        Text(
                            text = "Crear Grupo", fontSize = 20.sp,
                            color = Color.White, fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

        }
    }
}