package com.example.synhub.groups.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synhub.groups.viewmodel.GroupViewModel
import com.example.synhub.shared.components.TopBar

@Composable
fun Group(nav: NavHostController, groupViewModel: GroupViewModel = GroupViewModel()) {
    val group by groupViewModel.group.collectAsState()
    LaunchedEffect(Unit) {
        groupViewModel.fetchLeaderGroup()
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.navigate("Home")
                },
                group?.name ?: "Grupo",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> GroupScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@Composable
fun GroupScreen(modifier: Modifier, nav: NavHostController, groupViewModel: GroupViewModel = GroupViewModel()) {

    val group by groupViewModel.group.collectAsState()
    val haveGroup by groupViewModel.haveGroup.collectAsState()
    val members by groupViewModel.members.collectAsState()

    LaunchedEffect(Unit) {
        groupViewModel.fetchLeaderGroup()
        groupViewModel.fetchGroupMembers()
    }

    Box(modifier = Modifier.fillMaxSize()){
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .padding(horizontal = 15.dp)
        ) {
            if(!haveGroup){
                NoGroup(nav)
            } else {
                Column (
                    verticalArrangement = Arrangement.spacedBy(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        colors = cardColors(containerColor = Color(0xFF4A90E2)),
                        modifier = Modifier
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(10.dp),
                                clip = true
                            ),
                    ) {
                        Text(
                            text = ("#" + group?.code),
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(16.dp)
                        )
                    }

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 12.dp),
                        shape = RoundedCornerShape(10.dp),
                        colors = cardColors(containerColor = Color(0xFF1A4E85))
                    ) {
                        Text(
                            text = group?.description ?: "",
                            fontSize = 20.sp,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
                Column (
                    modifier = Modifier.padding(bottom = 26.dp),
                ){
                    Text(
                        "Integrantes del grupo",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1A4E85),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp),
                        color = Color(0xFF1A4E85),
                        thickness = 1.dp
                    )
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .shadow(
                                elevation = 5.dp,
                                shape = RoundedCornerShape(10.dp),
                                clip = true
                            ),
                        shape = RoundedCornerShape(10.dp),
                        colors = cardColors(containerColor = Color(0xFFF5F5F5))
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp),
                            shape = RoundedCornerShape(10.dp),
                            colors = cardColors(containerColor = Color.White)
                        ) {
                            LazyColumn(
                                contentPadding = PaddingValues(5.dp),
                                verticalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                items(members) { member ->
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(50.dp)
                                                .shadow(
                                                    elevation = 5.dp,
                                                    shape = CircleShape,
                                                    clip = true
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            AsyncImage(
                                                model = member.imgUrl,
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop,
                                                modifier = Modifier
                                                    .matchParentSize()
                                                    .clip(CircleShape)
                                            )
                                        }
                                        Column (
                                            modifier = Modifier.weight(1f)
                                        ){
                                            Text(
                                                text = "${member.name} ${member.surname}",
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Bold
                                            )
                                            Text(
                                                text = member.username,
                                                fontSize = 14.sp,
                                                color = Color.Gray
                                            )
                                        }
                                        IconButton(
                                            onClick = {
                                            }
                                        ) {
                                            Icon(Icons.Default.Delete, contentDescription = null)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun NoGroup(nav: NavHostController){

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