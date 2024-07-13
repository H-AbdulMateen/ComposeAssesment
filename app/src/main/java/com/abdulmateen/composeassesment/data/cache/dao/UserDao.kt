package com.abdulmateen.composeassesment.data.cache.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.abdulmateen.composeassesment.data.cache.entity.UserEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Upsert
    suspend fun insertOrReplace(user: UserEntity): Long

    @Query("SELECT * FROM user_table WHERE id = :id")
    fun getUser(id: Int): Flow<UserEntity>


    @Query("DELETE FROM user_table")
    suspend fun deleteAllUsers()

}