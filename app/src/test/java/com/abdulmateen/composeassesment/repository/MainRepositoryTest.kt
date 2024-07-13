package com.abdulmateen.composeassesment.repository

import com.abdulmateen.composeassesment.AppDataStoreManagerFake
import com.abdulmateen.composeassesment.cache.AppDatabaseFake
import com.abdulmateen.composeassesment.cache.UserDaoFake
import com.abdulmateen.composeassesment.data.datastore.AppDataStore
import com.abdulmateen.composeassesment.data.datastore.PrefKeys
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MainRepositoryTest {
    private val dataStore: AppDataStore = AppDataStoreManagerFake()
    private val db = AppDatabaseFake()
    private val userDao = UserDaoFake(db)
    private val repository = MainRepositoryImpl(userDao, dataStore)
    
    @Test
    fun insertOrReplace() = runBlocking {
        val email = "test_user@gmail.com"
        val name = "TestUser"
        repository.insertOrReplace(email = email, name = name)
        val user = repository.getUserInfo().first()
        assert(user.email == email)
        assert(user.name == name)
        val isLoggedIn = dataStore.readValue(PrefKeys.IS_LOGGED_IN)
        assert(isLoggedIn == true)
    }

    @Test
    fun fetchMedicineList() = runBlocking {
        val medicationList = repository.fetchMedicineList().first()
        assert(medicationList.isNotEmpty())
    }

    @Test
    fun logout() = runBlocking {
        repository.logout()
        val isLoggedIn = dataStore.readValue(PrefKeys.IS_LOGGED_IN)
        assert(isLoggedIn == false)
    }

}