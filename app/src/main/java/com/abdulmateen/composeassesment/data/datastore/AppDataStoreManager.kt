package com.abdulmateen.composeassesment.data.datastore

import androidx.compose.runtime.mutableStateOf
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.abdulmateen.composeassesment.data.datastore.PrefKeys.IS_LOGGED_IN
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

object PrefKeys{
    val IS_LOGGED_IN = "is_logged_in"
}

class AppDataStoreManager @Inject constructor(
    private val dataStore: DataStore<Preferences>
): AppDataStore {

    override suspend fun setBoolValue(key: String, value: Boolean) {
        dataStore.edit { preference ->
            preference[booleanPreferencesKey(key)] = value
        }
    }

    override suspend fun readValue(key: String): Boolean? {
        return dataStore.data.first()[booleanPreferencesKey(key)]
    }

}