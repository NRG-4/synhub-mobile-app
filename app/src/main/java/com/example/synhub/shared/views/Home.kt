package com.example.synhub.shared.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.SlideMenu
import com.example.synhub.shared.components.TopBar
import com.example.synhub.shared.viewmodel.HomeViewModel
import kotlinx.coroutines.launch

@Composable
fun Home(nav: NavHostController, homeViewModel: HomeViewModel = HomeViewModel()) {

    LaunchedEffect(Unit) {
        homeViewModel.fetchLeaderDetails()
    }

    val leader by homeViewModel.leader.collectAsState()

    val slideMenuState =rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = slideMenuState,
        drawerContent = {
            ModalDrawerSheet {
                SlideMenu(nav, leader?.name ?: "", leader?.surname ?: "", leader?.imgUrl ?: "")
            }
        }
    ) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFFFFFFF),
            topBar = {
                TopBar(
                    function = {
                        scope.launch {
                            slideMenuState.apply {
                                if(isClosed)
                                    open()
                                else
                                    close()
                            }
                        }
                    },
                    "Principal",
                    Icons.Default.Menu
                )
            }
        ){
                innerPadding -> HomeScreen(modifier = Modifier.padding(innerPadding),
            nav)
        }
    }
}

@Composable
fun HomeScreen(modifier: Modifier, nav: NavHostController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 90.dp),
    ){
        Text(text = "hola")
    }
}