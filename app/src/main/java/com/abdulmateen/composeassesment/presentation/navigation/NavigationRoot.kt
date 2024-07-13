package com.abdulmateen.composeassesment.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.NavHostController

@Composable
fun NavigationRoot(
    navController: NavHostController,
    isLoggedIn: Boolean = false,
) {
    NavHost(
        navController = navController,
        route = "root",
        startDestination = if (isLoggedIn) NavGraphRoute.MAIN_GRAPH.name else NavGraphRoute.AUTH_GRAPH.name,
    ){
            mainNavGraph(navController = navController)
            authNavGraph(navController = navController)
    }
}