package com.abdulmateen.composeassesment.data.datastore

interface AppDataStore {

    suspend fun setBoolValue(
        key: String,
        value: Boolean
    )

    suspend fun readValue(
        key: String,
    ): Boolean?

}