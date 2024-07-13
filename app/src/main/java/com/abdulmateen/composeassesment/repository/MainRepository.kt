package com.abdulmateen.composeassesment.repository

import com.abdulmateen.composeassesment.data.models.User
import com.abdulmateen.composeassesment.data.remote.dto.Medication
import kotlinx.coroutines.flow.Flow

interface MainRepository {
    suspend fun insertOrReplace(email: String, name: String)
    fun getUserInfo(): Flow<User>
    fun fetchMedicineList(): Flow<List<Medication>>
    suspend fun logout()
}