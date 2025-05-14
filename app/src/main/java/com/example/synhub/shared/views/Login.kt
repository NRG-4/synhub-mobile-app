package com.example.synhub.shared.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.R
import com.example.synhub.shared.icons.lockSVG
import com.example.synhub.shared.icons.personSVG


@Composable
fun Login(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF)
    ){
        innerPadding -> LoginScreen(modifier =Modifier.padding(innerPadding), nav)
    }
}

@Composable
fun LoginScreen(modifier: Modifier, nav: NavHostController){

    var txtUser by remember { mutableStateOf("") }
    var txtPass by remember { mutableStateOf("") }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .padding(vertical = 40.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "SynHub",
            fontSize = 70.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A4E85),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 20.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.synhub_logo),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
        )
        Text(
            text = "Login",
            fontSize = 20.sp,
            color = Color(0xFF000000),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        OutlinedTextField(
            value = txtUser,
            singleLine = true,
            modifier = Modifier,
            label = { Text(text = "Insert User")},
            placeholder = { Text(text = "User")},
            leadingIcon = {
                Icon(
                    imageVector = personSVG,
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
            onValueChange = {txtUser=it}
        )

        OutlinedTextField(
            value = txtPass,
            singleLine = true,
            modifier = Modifier,
            label = { Text(text = "Insert Password")},
            placeholder = { Text(text = "password")},
            leadingIcon = {
                Icon(
                    imageVector = lockSVG,
                    tint = Color.Gray,
                    contentDescription = ""
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF3F3F3),
                unfocusedContainerColor = Color.White,
                cursorColor = Color.Cyan
            ),
            visualTransformation = PasswordVisualTransformation(),
            onValueChange = {txtPass=it}
        )

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(0xFF4A90E2)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier,
            onClick = {
                nav.navigate("Home")
            }
        ) {
            Text(
                text = "Iniciar Sesion", fontSize = 20.sp,
                color = Color.White, fontWeight = FontWeight.Bold
            )

        }

        ElevatedButton(
            colors = ButtonDefaults.buttonColors(Color(0xFFFFFFFF)),
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier,
            onClick = {
                nav.navigate("Register")
            }
        ) {
            Text(
                text = "Registrase", fontSize = 20.sp,
                color = Color.Black, fontWeight = FontWeight.Bold
            )

        }
    }
}