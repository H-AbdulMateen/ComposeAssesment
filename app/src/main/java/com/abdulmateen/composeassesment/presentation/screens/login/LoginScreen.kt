package com.abdulmateen.composeassesment.presentation.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.composeassesment.R
import com.abdulmateen.composeassesment.presentation.components.ButtonRectangle
import com.abdulmateen.composeassesment.presentation.components.TextFieldUnderline
import com.abdulmateen.composeassesment.presentation.components.TextFieldUnderlineEmail
import com.abdulmateen.composeassesment.presentation.components.TextFieldUnderlinePassword
import com.abdulmateen.composeassesment.presentation.navigation.NavGraphRoute
import java.util.Locale

@Composable
fun LoginScreen(
    navController: NavController,
    uiState: LoginUIState,
    uiEvents: (LoginUIEvents) -> Unit
) {
    val context = LocalContext.current
    Scaffold { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .scrollable(
                        state = rememberScrollState(),
                        orientation = Orientation.Vertical
                    ),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    painter = painterResource(id = R.drawable.app_logo),
                    contentDescription = "SplashBgImage",
                    modifier = Modifier
                        .width(200.dp)
                        .height(75.dp),
                    contentScale = ContentScale.FillWidth
                )

                Spacer(modifier = Modifier.height(18.dp))

                //Login Credential Card
                LoginCredentialsCard(
                    uiState = uiState,
                    uiEvents = uiEvents,
                    navController = navController
                )
                Spacer(modifier = Modifier.height(16.dp))

            }
        }
    }
}

@Composable
fun LoginCredentialsCard(
    uiState: LoginUIState,
    uiEvents: (LoginUIEvents) -> Unit,
    navController: NavController
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = stringResource(id = R.string.login).uppercase(Locale.ROOT),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = stringResource(id = R.string.login_to_your_account),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            //Email TextField
            TextFieldUnderlineEmail(
                text = uiState.email,
                onTextChange = { uiEvents(LoginUIEvents.UpdateEmail(it)) },
                placeholder = "someone@mail.com",
                label = stringResource(id = R.string.email),
                modifier = Modifier.fillMaxWidth(),
                hasError = uiState.hasEmailError,
                errorMessage = uiState.emailError
            )

            Spacer(modifier = Modifier.height(4.dp))
            //Name TextField
            TextFieldUnderline(
                text = uiState.name,
                onTextChange = {
                    uiEvents(LoginUIEvents.UpdateName(it))
                },
                placeholder = stringResource(id = R.string.name),
                label = stringResource(
                    id = R.string.name
                ),
                modifier = Modifier.fillMaxWidth(),
                hasError = uiState.hasNameError,
                errorMessage = uiState.nameError
            )

            Spacer(modifier = Modifier.height(8.dp))

            //Password Text Field
            TextFieldUnderlinePassword(
                text = uiState.password,
                onTextChange = { uiEvents(LoginUIEvents.UpdatePassword(it)) },
                placeholder = "*******",
                label = stringResource(id = R.string.password),
                passwordVisible = uiState.passwordVisibility,
                onClickPasswordVisibility = { uiEvents(LoginUIEvents.UpdatePasswordVisibilityStatus) },
                modifier = Modifier.fillMaxWidth(),
                hasError = uiState.hasPasswordError,
                errorMessage = uiState.passwordError
            )
            Spacer(modifier = Modifier.height(12.dp))
            //Login Button
            ButtonRectangle(
                text = stringResource(id = R.string.login),
                onClick = {
                    uiEvents(LoginUIEvents.OnLoginClick)
                },
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
fun LoginScreenPrev() {
    val navController = rememberNavController()
    LoginScreen(
        navController = navController,
        uiState = LoginUIState(),
        uiEvents = {}
    )
}