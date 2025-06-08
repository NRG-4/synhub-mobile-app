package com.example.synhub.groups.views

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.synhub.groups.viewmodel.GroupViewModel
import com.example.synhub.groups.viewmodel.MemberViewModel
import com.example.synhub.shared.components.TopBar
import com.example.synhub.tasks.views.getDividerColor
import androidx.lifecycle.viewmodel.compose.viewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Members(nav: NavHostController){
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        containerColor = Color(0xFFFFFFFF),
        topBar = {
            TopBar(
                function = {
                    nav.popBackStack()
                },
                "Miembros del grupo",
                Icons.AutoMirrored.Filled.ArrowBack
            )
        }
    ){
            innerPadding -> MembersScreen(modifier = Modifier.padding(innerPadding),
        nav)
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MembersScreen(modifier: Modifier, nav: NavHostController,
                  memberViewModel: MemberViewModel = viewModel(), groupViewModel: GroupViewModel = viewModel()) {

    val haveGroup by groupViewModel.haveGroup.collectAsState()
    val group by groupViewModel.group.collectAsState()
    val members by memberViewModel.members.collectAsState()
    val haveMembers by memberViewModel.haveMembers.collectAsState()
    val nextTaskMap by memberViewModel.nextTaskMap.collectAsState()

    LaunchedEffect(Unit) {
        groupViewModel.fetchLeaderGroup()
        memberViewModel.fetchGroupMembers()
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 120.dp)
            .padding(horizontal = 20.dp)
    ){
        if(!haveGroup) {
            NoGroup(nav)
        }else{
            if(!haveMembers){
                NoMembers(nav, group?.code ?: "")
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
                    items(members) { member ->

                        val nextTask = nextTaskMap[member.id]
                        LaunchedEffect(member.id) {
                            memberViewModel.fetchNextTask(member.id)
                        }
                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .shadow(
                                    elevation = 5.dp,
                                    shape = RoundedCornerShape(10.dp),
                                    clip = true
                                ),
                            shape = RoundedCornerShape(10.dp),
                            colors = cardColors(
                                containerColor = Color(0xFFF5F5F5)
                            ),
                            onClick = {
                                nav.navigate("Group/Member/${member.id}")
                            }
                        ) {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                                modifier = Modifier.padding(10.dp)
                                    .background(Color(0xFFF5F5F5))
                            ) {
                                Row (
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(40.dp)
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
                                    Text(
                                        text = member.name + " " + member.surname,
                                        fontSize = 20.sp,
                                        color = Color.Black,
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                                Card(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .shadow(
                                            elevation = 5.dp,
                                            shape = RoundedCornerShape(10.dp),
                                            clip = true
                                        ),
                                    shape = RoundedCornerShape(10.dp),
                                    colors = cardColors(containerColor = Color(0xFF1A4E85))
                                ) {
                                    Column (
                                        modifier = Modifier
                                            .padding(10.dp),
                                        verticalArrangement = Arrangement.spacedBy(10.dp),
                                    ) {
                                        Text(
                                            text = nextTask?.title ?: "Sin próxima tarea",
                                            fontSize = 15.sp,
                                            color = Color.White
                                        )
                                        HorizontalDivider(color = Color.White)
                                        Text(
                                            text = nextTask?.description ?: "",
                                            fontSize = 15.sp,
                                            color = Color.White
                                        )
                                    }
                                }

                                if(nextTask?.createdAt != null && nextTask?.dueDate != null){
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(10.dp)
                                            .background(
                                                color = getDividerColor(
                                                    nextTask!!.createdAt.toString(),
                                                    nextTask!!.dueDate.toString()
                                                ),
                                                shape = RoundedCornerShape(10.dp)
                                            )
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(10.dp)
                                )
                                Column(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    val createdDate = nextTask?.createdAt?.substring(0, 10) ?: ""
                                    val dueDate = nextTask?.dueDate?.substring(0, 10) ?: ""
                                    Text(
                                        text = "$createdDate - $dueDate",
                                        fontSize = 15.sp,
                                        color = Color.Black
                                    )
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
fun NoMembers(nav: NavHostController, code:String){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp))
    {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            shape = RoundedCornerShape(10.dp),
            colors = cardColors(containerColor = Color(0xFF1A4E85)),
        ) {
            Column(
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Tu grupo no tiene miembros, invita anuevos integrantes a traves de este código",
                    fontSize = 20.sp,
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center
                )
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = cardColors(containerColor = Color(0xFF4A90E2))
                ) {
                    Text(
                        text = "#${code}",
                        fontSize = 20.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}