package com.example.synhub.shared.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.synhub.analytics.views.AnalyticsAndReports
import com.example.synhub.groups.views.CreateGroup
import com.example.synhub.groups.views.Group
import com.example.synhub.groups.views.MemberDetails
import com.example.synhub.groups.views.Members
import com.example.synhub.requests.views.RequestAndValidationList
import com.example.synhub.requests.views.RequestView
import com.example.synhub.requests.views.ValidationView
import com.example.synhub.shared.views.Home
import com.example.synhub.shared.views.Login
import com.example.synhub.shared.views.Register
import com.example.synhub.tasks.views.CreateTask
import com.example.synhub.tasks.views.EditTask
import com.example.synhub.tasks.views.TaskDetail
import com.example.synhub.tasks.views.Tasks

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigator(){
    val rememberScreen = rememberNavController()
    NavHost(navController = rememberScreen,
        startDestination = "Login"){
        composable("Login") { Login(rememberScreen) }
        composable("Home") { Home(rememberScreen) }
        composable("AnalyticsAndReports") { AnalyticsAndReports(rememberScreen) }
        composable("Group") { Group(rememberScreen) }

        composable("Group/Members") { Members(rememberScreen) }
        composable("Group/Member/{memberId}") { backStackEntry ->
            val memberId = backStackEntry.arguments?.getString("memberId")
            MemberDetails(rememberScreen, memberId)
        }

        // Request and validation screens
        composable("RequestsAndValidations") { RequestAndValidationList(rememberScreen) }
        composable("Request/{requestId}") { backStackEntry ->
            val requestId = backStackEntry.arguments?.getString("requestId")
            RequestView(rememberScreen, requestId) }
        composable("Validation") { ValidationView(rememberScreen) }

        composable("Tasks") { Tasks(rememberScreen) }
        composable("Tasks/Detail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            TaskDetail(rememberScreen, taskId)
        }
        composable("Register") { Register(rememberScreen) }
        composable("Group/CreateGroup") { CreateGroup(rememberScreen) }
        composable("Tasks/Create") { CreateTask(rememberScreen) }
        composable("Tasks/Edit/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")
            EditTask(rememberScreen, taskId)
        }
    }
}