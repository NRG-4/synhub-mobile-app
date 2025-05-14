package com.example.synhub.shared.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.synhub.analytics.views.AnalyticsAndReports
import com.example.synhub.groups.views.CreateGroup
import com.example.synhub.groups.views.Group
import com.example.synhub.groups.views.InviteMembers
import com.example.synhub.groups.views.Members
import com.example.synhub.requests.views.RequestAndValidationList
import com.example.synhub.requests.views.RequestView
import com.example.synhub.requests.views.ValidationView
import com.example.synhub.shared.views.Home
import com.example.synhub.shared.views.Login
import com.example.synhub.shared.views.Register
import com.example.synhub.tasks.views.CreateTask
import com.example.synhub.tasks.views.EditTask
import com.example.synhub.tasks.views.Tasks

@Composable
fun Navigator(){
    val rememberScreen = rememberNavController()
    NavHost(navController = rememberScreen,
        startDestination = "Login"){
        composable("Login") { Login(rememberScreen) }
        composable("Home") { Home(rememberScreen) }
        composable("AnalyticsAndReports") { AnalyticsAndReports(rememberScreen) }
        composable("Group") { Group(rememberScreen) }
        composable("Members") { Members(rememberScreen) }

        // Request and validation screens
        composable("RequestsAndValidations") { RequestAndValidationList(rememberScreen) }
        composable("Request") { RequestView(rememberScreen) }
        composable("Validation") { ValidationView(rememberScreen) }

        composable("Tasks") { Tasks(rememberScreen) }
        composable("Register") { Register(rememberScreen) }
        composable("Group/CreateGroup") { CreateGroup(rememberScreen) }
        composable("Group/Invite") { InviteMembers(rememberScreen) }
        composable("Tasks/Create") { CreateTask(rememberScreen) }
        composable("Tasks/Edit") { EditTask(rememberScreen) }
    }
}