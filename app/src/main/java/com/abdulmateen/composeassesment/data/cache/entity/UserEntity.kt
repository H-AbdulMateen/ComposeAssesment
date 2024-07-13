package com.abdulmateen.composeassesment.data.cache.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.abdulmateen.composeassesment.data.models.User

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val email: String
)

fun UserEntity.toUser(): User {
    return User(
        id = id,
        name = name,
        email = email
    )
}

