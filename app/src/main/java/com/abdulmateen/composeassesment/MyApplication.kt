package com.abdulmateen.composeassesment

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.composeassesment.data.datastore.AppDataStoreManager
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MyApplication: Application() {
    @Inject
    lateinit var dataStore: AppDataStoreManager
    @Inject
    lateinit var coreDataStore: DataStore<Preferences>
    override fun onCreate() {
        super.onCreate()
    }
}