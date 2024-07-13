package com.abdulmateen.composeassesment.presentation.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulmateen.composeassesment.presentation.screens.login.LoginScreen
import com.abdulmateen.composeassesment.presentation.screens.login.LoginViewModel

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){

    navigation(
        startDestination = ScreenRoute.LOGIN.name,
        route = NavGraphRoute.AUTH_GRAPH.name
    ){
        composable(
            route = ScreenRoute.LOGIN.name
        ){
            val viewModel: LoginViewModel = hiltViewModel()
            LoginScreen(
                navController = navController,
                uiState = viewModel.uiState.value,
                uiEvents = viewModel::onUIEvent
            )
        }
    }
}