package com.abdulmateen.composeassesment.presentation.screens.home

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.ThreePaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.composeassesment.R
import com.abdulmateen.composeassesment.data.remote.dto.Medication
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    uiState: HomeUIState,
    logout: () -> Unit
) {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.medication)
                    )
                },
                actions = {

                    ClickableText(
                        text = AnnotatedString(stringResource(id = R.string.logout)),
                        style = MaterialTheme.typography.labelMedium.copy(color = MaterialTheme.colorScheme.onBackground),
                        onClick = { logout() }
                    )
                }
            )
        }
    ) { innerPadding ->
        ListDetailLayout(
            modifier = Modifier.padding(innerPadding),
            uiState = uiState
        )
//        Column(modifier = Modifier
//            .padding(innerPadding)
//            .padding(16.dp)) {
//            UserDetailCard(uiState = uiState)
//            Spacer(modifier = Modifier.height(16.dp))
//            MedicineListLayout(uiState = uiState)
//        }
    }
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun ListDetailLayout(modifier: Modifier, uiState: HomeUIState) {
    val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
    NavigableListDetailPaneScaffold(
        modifier = modifier,
        navigator = navigator, listPane = {
            Column {
                UserDetailCard(uiState = uiState)
                Spacer(modifier = Modifier.height(16.dp))
                MedicineListLayout(
                    uiState = uiState,
                    navigator = navigator
                )
            }
        }, detailPane = {
            val content = navigator.currentDestination?.content ?: Medication(
                name = "",
                dose = "",
                strength = ""
            )
            AnimatedPane {
                Column {
                    MedicineListItem(item = content as Medication)
                }
            }
        })
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun MedicineListLayout(
    uiState: HomeUIState,
    navigator: ThreePaneScaffoldNavigator<Any>
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        )
    ) {
        LazyColumn {
            items(items = uiState.medicineList) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable(onClick = {
                            navigator.navigateTo(
                                pane = ListDetailPaneScaffoldRole.Detail,
                                content = it
                            )
                        })
                ) {
                    MedicineListItem(item = it)
                    Spacer(modifier = Modifier.height(4.dp))
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

            }
        }
    }
}

@Composable
fun MedicineListItem(item: Medication) {
    Text(
        text = stringResource(id = R.string.name).plus(": ${item.name}"),
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
    )
    Text(
        text = stringResource(id = R.string.dose).plus(": ${item.dose}"),
        style = MaterialTheme.typography.labelLarge
    )
    Text(
        text = stringResource(id = R.string.strength).plus(": ${item.strength}"),
        style = MaterialTheme.typography.labelMedium
    )
}

@Composable
fun UserDetailCard(uiState: HomeUIState) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 0.dp
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")
            Text(text = "Email: ${uiState.email}")
            Text(text = "Name: ${uiState.username}")
            Text(text = "Recent Activity: ${current.format(formatter)}")
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreen(
        navController = rememberNavController(),
        uiState = HomeUIState(
            email = "abdulmateen6.ch@gmail.com",
            username = "Abdulmateen",
            medicineList = listOf(
                Medication(
                    name = "name",
                    dose = "dose",
                    strength = "strength"
                ),
                Medication(
                    name = "name",
                    dose = "dose",
                    strength = "strength"
                ),
                Medication(
                    name = "name",
                    dose = "dose",
                    strength = "strength"
                )
            )
        ),
        logout = {}
    )
}