package com.abdulmateen.composeassesment.di
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.abdulmateen.composeassesment.data.cache.dao.UserDao
import com.abdulmateen.composeassesment.data.datastore.AppDataStore
import com.abdulmateen.composeassesment.data.remote.RetroService
import com.abdulmateen.composeassesment.repository.MainRepository
import com.abdulmateen.composeassesment.repository.MainRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {


    @ViewModelScoped
    @Provides
    fun provideMainRepository(
        userDao: UserDao,
        dataStore: AppDataStore
    ): MainRepository = MainRepositoryImpl(
        userDao = userDao,
        dataStore = dataStore
    )
}