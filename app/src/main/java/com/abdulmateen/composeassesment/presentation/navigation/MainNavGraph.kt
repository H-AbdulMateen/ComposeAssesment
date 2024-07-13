package com.abdulmateen.composeassesment.presentation.navigation

import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.abdulmateen.composeassesment.presentation.screens.home.HomeScreen
import com.abdulmateen.composeassesment.presentation.screens.home.HomeViewModel
import com.abdulmateen.composeassesment.presentation.screens.login.LoginScreen
import com.abdulmateen.composeassesment.presentation.screens.login.LoginViewModel

fun NavGraphBuilder.mainNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = ScreenRoute.HOME.name,
        route = NavGraphRoute.MAIN_GRAPH.name
    ){
        composable(
            route = ScreenRoute.HOME.name
        ){
            val viewModel: HomeViewModel = hiltViewModel()
            HomeScreen(
                navController = navController,
                uiState = viewModel.uiState.collectAsState().value,
                logout = viewModel::logout
            )
        }
    }
}