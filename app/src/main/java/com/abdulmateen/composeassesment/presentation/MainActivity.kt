package com.abdulmateen.composeassesment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.composeassesment.presentation.navigation.NavigationRoot
import com.abdulmateen.composeassesment.presentation.ui.theme.ComposeAssesmentTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                viewModel.uiState.isCheckingAuth
            }
        }
//        enableEdgeToEdge()
        setContent {
            ComposeAssesmentTheme {
                val navController = rememberNavController()
                if (!viewModel.uiState.isCheckingAuth){
                    NavigationRoot(navController = navController, isLoggedIn = viewModel.uiState.isLoggedIn)
                }

            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeAssesmentTheme {
        Greeting("Android")
    }
}