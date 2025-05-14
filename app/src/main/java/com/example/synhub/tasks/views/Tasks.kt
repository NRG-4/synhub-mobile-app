package com.example.synhub.tasks.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.groups.views.GroupScreen
import com.example.synhub.groups.views.integrantes
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.icons.abcSVG
import com.example.synhub.shared.icons.editSVG
import com.example.synhub.shared.icons.trashSVG


@Composable
fun Tasks(nav: NavHostController) {
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "Tareas",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> TaskScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@Composable
fun TaskScreen(modifier: Modifier, nav: NavHostController) {

    var tareas = true

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .padding(horizontal = 20.dp)
    ){
        if(!tareas)
        {
            NoTasks(nav)
        }else{
            Text(
                text = "Integrantes",
                fontSize = 25.sp,
                color = Color(0xFF1A4E85),
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ){
                items(integrantes){
                        integrante ->(
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(10.dp),
                            colors = cardColors(
                                containerColor = Color(0xFFF5F5F5)
                            ),
                        ){
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(10.dp)
                                    .background(Color(0xFFF5F5F5))
                            ) {
                                Text(
                                    text = integrante.nombre,
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )

                                Text(
                                    text="${integrante.tarea.titulo}",
                                    fontSize = 15.sp,
                                    color = Color.Black
                                )
                                HorizontalDivider(color = Color.Black)
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(Color.White,
                                            shape = RoundedCornerShape(10.dp)),
                                ){
                                    Column (
                                        modifier = Modifier
                                            .padding(10.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                    ){
                                        Text(
                                            text="${integrante.tarea.descripcion}",
                                            fontSize = 15.sp,
                                            color = Color.Black
                                        )
                                    }
                                }
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ){
                                    Text(
                                        text = "${integrante.tarea.fechaCreacion} - ${integrante.tarea.fechaFinal}",
                                        fontSize = 15.sp,
                                        color = Color.Black
                                    )
                                }
                                Column (
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                ){
                                    Row (
                                        horizontalArrangement = Arrangement.spacedBy(20.dp)
                                    ){
                                        ElevatedButton(
                                            colors = ButtonDefaults.buttonColors(Color(0xFFFF9800)),
                                            shape = RoundedCornerShape(10.dp),
                                            modifier = Modifier,
                                            onClick = {
                                                nav.navigate("")
                                            }
                                        ) {
                                            Icon(
                                                painter = rememberVectorPainter(image = editSVG),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Editar", fontSize = 20.sp,
                                                color = Color.White, fontWeight = FontWeight.Bold
                                            )

                                        }
                                        ElevatedButton(
                                            colors = ButtonDefaults.buttonColors(Color(0xFFF44336)),
                                            shape = RoundedCornerShape(10.dp),
                                            modifier = Modifier,
                                            onClick = {
                                                nav.navigate("")
                                            }
                                        ) {
                                            Icon(
                                                painter = rememberVectorPainter(image = trashSVG),
                                                contentDescription = null,
                                                tint = Color.White
                                            )
                                            Spacer(modifier = Modifier.width(8.dp))
                                            Text(
                                                text = "Borrar", fontSize = 20.sp,
                                                color = Color.White, fontWeight = FontWeight.Bold
                                            )
                                        }
                                    }
                                }
                            }
                        }
                        )
                }
            }
        }
    }
}

@Composable
fun NoTasks(nav: NavHostController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
            .padding(top = 10.dp)
            .background(Color(0xFF1A4E85),
                shape = RoundedCornerShape(10.dp)
            ),
            contentAlignment = Alignment.Center){
            Column(
                modifier = Modifier
                    .padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "No hay tareas programadas",
                    fontSize = 25.sp,
                    color = Color(0xFFFFFFFF)
                )
            }
        }
    }
}