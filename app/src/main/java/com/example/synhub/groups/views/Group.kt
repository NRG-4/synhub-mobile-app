package com.example.synhub.groups.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.TopBar

// Clase para la tarea
data class Tarea(
    val titulo: String,
    val descripcion: String,
    val fechaCreacion: String,
    val fechaFinal: String
)

// Clase para el integrante
data class Integrante(
    val nombre: String,
    val tarea: Tarea
)

// Lista de ejemplo
public val integrantes = listOf(
    Integrante(
        nombre = "Ana",
        tarea = Tarea(
            titulo = "Diseñar logo",
            descripcion = "Crear el logo principal del grupo",
            fechaCreacion = "2024-06-10",
            fechaFinal = "2024-06-15"
        )
    ),
    Integrante(
        nombre = "Luis",
        tarea = Tarea(
            titulo = "Desarrollar app",
            descripcion = "Programar la funcionalidad básica",
            fechaCreacion = "2024-06-11",
            fechaFinal = "2024-06-20"
        )
    ),
    Integrante(
        nombre = "María",
        tarea = Tarea(
            titulo = "Redactar documentación",
            descripcion = "Escribir la documentación del proyecto",
            fechaCreacion = "2024-06-12",
            fechaFinal = "2024-06-18"
        )
    )
)

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
    var group = true
    var members = true

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 90.dp)
            .padding(horizontal = 20.dp)
    ){
        if(!group) {
            noGroup(nav)
        }else{
            if(!members){
                noMembers(nav)
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
                                            color = Color(0xFF000000)
                                        )
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .background(Color(0xFF1A4E85),
                                                    shape = RoundedCornerShape(10.dp)),
                                        ){
                                            Column (
                                                modifier = Modifier
                                                    .padding(10.dp),
                                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                            ){
                                                Text(
                                                    text="${integrante.tarea.titulo}",
                                                    fontSize = 15.sp,
                                                    color = Color.White
                                                )
                                                HorizontalDivider(color = Color.White)
                                                Text(
                                                    text="${integrante.tarea.descripcion}",
                                                    fontSize = 15.sp,
                                                    color = Color.White
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

                                    }
                                }
                                )
                    }
                }
            }
        }

    }
}

@Composable
fun noMembers(nav: NavHostController){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 25.dp)
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
                    text = "Tu grupo no tiene miembros",
                    fontSize = 25.sp,
                    color = Color(0xFFFFFFFF)
                )
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(Color(0xFF4A90E2)),
                    shape = RoundedCornerShape(10.dp),
                    onClick = {
                        nav.navigate("Group/Invite")
                    }
                ) {
                    Text(
                        text = "Invitar Miembros", fontSize = 20.sp,
                        color = Color.White, fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun noGroup(nav: NavHostController){

    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 25.dp)
                .padding(top = 10.dp)
                .background(
                    Color(0xFF1A4E85),
                    shape = RoundedCornerShape(10.dp)
                ),
            contentAlignment = Alignment.Center
        ) {
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