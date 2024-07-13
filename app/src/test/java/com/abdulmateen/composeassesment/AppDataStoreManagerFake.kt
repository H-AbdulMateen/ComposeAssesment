package com.abdulmateen.composeassesment

import com.abdulmateen.composeassesment.data.datastore.AppDataStore

class AppDataStoreManagerFake: AppDataStore {

    private val datastore: MutableMap<String, Boolean> = mutableMapOf()

    override suspend fun setBoolValue(key: String, value: Boolean) {
        datastore[key] = value
    }

    override suspend fun readValue(key: String): Boolean? {
        return datastore[key]
    }
}