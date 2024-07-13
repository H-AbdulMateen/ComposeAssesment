package com.abdulmateen.composeassesment.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdulmateen.composeassesment.data.datastore.PrefKeys.IS_LOGGED_IN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ViewModel() {

    var uiState by mutableStateOf(MainUIState())
        private set

    init {
        viewModelScope.launch {
            dataStore.data.collect { preferences ->
                uiState = if (preferences[booleanPreferencesKey(IS_LOGGED_IN)] == true) {
                    uiState.copy(
                        isLoggedIn = true,
                        isCheckingAuth = false
                    )
                } else {
                    uiState.copy(
                        isLoggedIn = false,
                        isCheckingAuth = false
                    )
                }
            }
        }
    }

}

data class MainUIState(
    val isCheckingAuth: Boolean = true,
    val isLoggedIn: Boolean = false
)