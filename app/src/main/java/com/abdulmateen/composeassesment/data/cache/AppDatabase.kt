package com.abdulmateen.composeassesment.data.cache
import androidx.room.Database
import androidx.room.RoomDatabase
import com.abdulmateen.composeassesment.data.cache.dao.UserDao
import com.abdulmateen.composeassesment.data.cache.entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object{
        const val DATABASE_NAME = "my_gen_room_database"
    }
}