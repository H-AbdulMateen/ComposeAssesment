package com.abdulmateen.composeassesment.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.abdulmateen.composeassesment.data.datastore.PrefKeys.IS_LOGGED_IN
import com.abdulmateen.composeassesment.data.cache.dao.UserDao
import com.abdulmateen.composeassesment.data.cache.entity.UserEntity
import com.abdulmateen.composeassesment.data.cache.entity.toUser
import com.abdulmateen.composeassesment.data.datastore.AppDataStore
import com.abdulmateen.composeassesment.data.models.User
import com.abdulmateen.composeassesment.data.remote.RetroService
import com.abdulmateen.composeassesment.data.remote.dto.Medication
import com.abdulmateen.composeassesment.data.remote.dto.ProblemsWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

class MainRepositoryImpl(
    private val userDao: UserDao,
    private val dataStore: AppDataStore
): MainRepository {
    override suspend fun insertOrReplace(email: String, name: String) {
        val user = UserEntity(id = 123, name = name, email = email)
        userDao.insertOrReplace(user)
        dataStore.setBoolValue(key = IS_LOGGED_IN, value = true)
    }

    override fun getUserInfo(): Flow<User> = userDao
        .getUser(123)
        .map { it.toUser() }

    override fun fetchMedicineList(): Flow<List<Medication>> = flow {
        val jsonString = """
    {
        "problems": [{
            "Diabetes":[{
                "medications":[{
                    "medicationsClasses":[{
                        "className":[{
                            "associatedDrug":[{
                                "name":"asprin",
                                "dose":"Half",
                                "strength":"500 mg"
                            }],
                            "associatedDrug#2":[{
                                "name":"somethingElse",
                                "dose":"Full",
                                "strength":"500 mg"
                            }]
                        }],
                        "className2":[{
                            "associatedDrug":[{
                                "name":"asprin",
                                "dose":"Half",
                                "strength":"500 mg"
                            }],
                            "associatedDrug#2":[{
                                "name":"somethingElse",
                                "dose":"Full",
                                "strength":"500 mg"
                            }]
                        }]
                    }]
                }],
                "labs":[{
                    "missing_field": "missing_value"
                }]
            }],
            "Asthma":[{}]
        }]
    }"""
        val problemsWrapper = Json.decodeFromString<ProblemsWrapper>(jsonString)
        val medicationList = problemsWrapper.problems.flatMap { problem ->
            problem.Diabetes.flatMap { diabetes ->
                diabetes.medications.flatMap { medications ->
                    medications.medicationsClasses.flatMap { medicationsClass ->
                        medicationsClass.className.flatMap { className ->
                            className.associatedDrug + className.associatedDrug2
                        } + medicationsClass.className2.flatMap { className2 ->
                            className2.associatedDrug + className2.associatedDrug2
                        }
                    }
                }
            }
        }
        emit(medicationList)
    }

    override suspend fun logout() {
        dataStore.setBoolValue(IS_LOGGED_IN, false)
    }

}