package com.abdulmateen.composeassesment.cache

import com.abdulmateen.composeassesment.data.cache.dao.UserDao
import com.abdulmateen.composeassesment.data.cache.entity.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class UserDaoFake(
    private val db: AppDatabaseFake
): UserDao {
    override suspend fun insertOrReplace(user: UserEntity): Long {
        db.users.add(user)
        return 1
    }

    override fun getUser(id: Int): Flow<UserEntity> {
        for (user in db.users) {
            if (user.id == id)
                return flowOf(user)
        }
        return flowOf(UserEntity(id = 0, name = "", email = ""))
        }

    override suspend fun deleteAllUsers() {
        db.users.clear()
    }
}