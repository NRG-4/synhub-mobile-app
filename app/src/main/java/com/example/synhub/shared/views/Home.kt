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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.synhub.shared.components.SlideMenu
import com.example.synhub.shared.components.TopBar
import kotlinx.coroutines.launch

@Composable
fun Home(nav: NavHostController){
    val slideMenuState =rememberDrawerState(
        initialValue = DrawerValue.Closed
    )

    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = slideMenuState,
        drawerContent = {
            ModalDrawerSheet {
                SlideMenu(nav)
            }
        }
    ) {
        Scaffold (
            modifier = Modifier.fillMaxSize(),
            containerColor = Color(0xFFFFFFFF),
            topBar = {
                TopBar(
                    onOpenDrawer = {
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
            .padding(top = 80.dp),
    ){
        Text(text = "hola")
    }
}