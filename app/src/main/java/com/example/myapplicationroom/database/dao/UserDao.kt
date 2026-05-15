package com.example.myapplicationroom.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplicationroom.database.entities.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("select * from users")
    fun getAllUsers(): Flow<List<User>>

    @Update
    fun saveUser(vararg users: User)

    @Insert(onConflict = OnConflictStrategy.NONE)
    fun saveNewUser(vararg users: User)

    @Delete
    fun deleteUser(user: User)

}