package com.example.synhub.groups.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.icons.abcSVG
import com.example.synhub.shared.icons.keyboardSVG
import com.example.synhub.shared.icons.linkSVG

@Composable
fun InviteMembers(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "Invitar Miembros",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> InviteMembersScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@Composable
fun InviteMembersScreen(modifier: Modifier, nav: NavHostController) {
    var TxtMembers by remember { mutableStateOf("") }
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = TxtMembers,
            singleLine = true,
            modifier = Modifier,
            label = { Text(text = "Nombre de usuario") },
            placeholder = { Text(text = "Nombre de usuario") },
            leadingIcon = {
                Icon(
                    imageVector = abcSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            onValueChange = {TxtMembers=it}
        )

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(0xFF4A90E2)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier,
            onClick = {
                nav.navigate("Group")
            }
        ) {
            Text(
                text = "Invitar", fontSize = 20.sp,
                color = Color.White, fontWeight = FontWeight.Bold
            )
        }
    }
}