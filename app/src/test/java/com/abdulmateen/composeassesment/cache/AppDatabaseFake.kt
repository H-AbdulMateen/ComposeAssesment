package com.abdulmateen.composeassesment.cache

import com.abdulmateen.composeassesment.data.cache.entity.UserEntity

class AppDatabaseFake {
    val users = mutableListOf<UserEntity>()
}