package com.example.synhub.tasks.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.icons.abcSVG
import com.example.synhub.shared.icons.calendarSVG
import com.example.synhub.shared.icons.keyboardSVG
import com.example.synhub.shared.icons.linkSVG
import com.example.synhub.shared.icons.logoutSVG
import com.example.synhub.shared.icons.personSVG
import com.example.synhub.shared.icons.saveSVG

@Composable
fun EditTask(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "Editar Tarea",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> EditTaskScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@Composable
fun EditTaskScreen(modifier: Modifier = Modifier, nav: NavHostController
) {
    var txtTitle by remember { mutableStateOf("") }
    var txtDescription by remember { mutableStateOf("") }
    var txtMember by remember { mutableStateOf("") }
    var txtDueDate by remember { mutableStateOf("") }


    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .padding(horizontal = 10.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedTextField(
            value = txtTitle,
            singleLine = true,
            modifier = Modifier,
            label = { Text(text = "Titulo de la tarea") },
            placeholder = { Text(text = "Titulo") },
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
            onValueChange = {txtTitle=it}
        )

        OutlinedTextField(
            value = txtDescription,
            modifier = Modifier,
            label = { Text(text = "Descripción de la tarea") },
            placeholder = { Text(text = "Descripción") },
            leadingIcon = {
                Icon(
                    imageVector = keyboardSVG,
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
            onValueChange = {txtDescription=it}
        )

        OutlinedTextField(
            value = txtMember,
            singleLine = true,
            modifier = Modifier,
            label = { Text(text = "Integrante") },
            placeholder = { Text(text = "Integrante") },
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
            onValueChange = {txtMember=it}
        )

        OutlinedTextField(
            value = txtDueDate,
            singleLine = true,
            modifier = Modifier,
            label = { Text(text = "Fecha de entrega") },
            placeholder = { Text(text = "Fecha") },
            leadingIcon = {
                Icon(
                    imageVector = calendarSVG,
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
            onValueChange = {txtDueDate=it}
        )

        Row(horizontalArrangement = Arrangement.spacedBy(20.dp)){
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(Color(0xFF4CAF50)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier,
                onClick = {
                    nav.popBackStack()
                }
            ) {
                Icon(
                    painter = rememberVectorPainter(image = saveSVG),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Guardar", fontSize = 20.sp,
                    color = Color.White, fontWeight = FontWeight.Bold
                )

            }
            ElevatedButton(
                colors = ButtonDefaults.buttonColors(Color(0xFFF44336)),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier,
                onClick = {
                    nav.popBackStack()
                }
            ) {
                Icon(
                    painter = rememberVectorPainter(image = logoutSVG),
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Cancelar", fontSize = 20.sp,
                    color = Color.White, fontWeight = FontWeight.Bold
                )

            }
        }
    }
}